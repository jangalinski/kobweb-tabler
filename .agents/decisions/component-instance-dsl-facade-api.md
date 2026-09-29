# Decision: Component Instance, DSL, and Facade API

## Status

Accepted.

Supersedes the primary-surface guidance in
[Composable Component API](composable-component-api.md). It complements the
concept-package, semantic-marker, table-cell, and top-level-entry-point
decisions.

## Context

The library needs two equally first-class ways to compose Tabler UI:

1. code, generators, or view models should be able to construct a typed
   component tree before a page is composed; and
2. a page should be able to declare the same UI fluently at the point of use.

The APIs must not grow two DOM implementations for those two paths. Repeating
the root element in both a component interface and a builder is particularly
easy to do for simple concepts such as avatars, and becomes increasingly
costly for cards, tables, and overlays.

## Decision

Each Tabler concept owns a package and has the following layers when that
concept is rendered by this library:

```text
avatar/
  Avatar.kt             # typed renderable component contract and factories
  AvatarList.kt         # composite component contract and factories
  AvatarListScope.kt    # list builder that creates component instances
  AvatarScope.kt        # nested avatar-content builder
  AvatarComposable.kt   # page-level composable entry points
  AvatarDsl.kt          # AvatarComposable implementation
  AvatarSize.kt         # typed concept option
  AvatarCss.kt          # internal CSS mapping
```

### 1. Renderable component instances

The canonical renderable value is a concept-specific interface extending
`Tabler.Component`. It owns `operator fun invoke(modifier: Modifier)` and is
the only owner of its Tabler DOM root and structure.

Each concept supplies factories, normally in its companion object, that create
anonymous implementations when a named implementation adds no value:

```kotlin
interface Avatar : Tabler.Component {
  companion object {
    operator fun invoke(
      content: Initials,
      size: AvatarSize = AvatarSize.DEFAULT,
      style: AvatarStyle = AvatarStyle.DEFAULT,
    ): Avatar = object : Avatar { /* properties and the one renderer */ }
  }
}
```

Enums and other stable value types may implement semantic option interfaces
such as `Tabler.Style`, `Tabler.Size`, or `Tabler.Behavior`. This is preferred
when the Tabler option is a finite variant, rather than replacing the variant
with an untyped string or a data class solely for storage.

Component instances may be nested as typed properties or collections and are
rendered by invoking them. A composite invokes its child components; it does
not duplicate their markup.

### 2. Fluent component functions

Every concept with a useful page-level builder defines a small `*Composable`
interface and exactly one implementing `*Dsl` data object:

```kotlin
interface AvatarComposable {
  @Composable
  fun avatars(
    stacked: Boolean = false,
    content: AvatarListScope.() -> Unit,
  )
}

data object AvatarDsl : AvatarComposable {
  @Composable
  override fun avatars(
    stacked: Boolean,
    content: AvatarListScope.() -> Unit,
  ) {
    val scope = AvatarListScope().apply(content)
    AvatarList(stacked = stacked, avatars = scope.avatars)()
  }
}
```

The builder creates the same component instances as the factory path and then
invokes the component. It must not render a second `KDiv`, `KSpan`, or other
part of the component tree. A builder may be a regular receiver lambda when
it only collects models; it is not made `@Composable` merely by convention.
It is `@Composable` only when it intentionally accepts arbitrary composable
content.

Scoped child verbs belong to the owning component scope, not the global
facade. For example, `avatar { initials(...) }`, `card { ... }`, and
`row { cell { ... } }` remain contextual. This prevents generic names such as
`item`, `header`, and `content` from colliding across every concept.

### 3. One stable facade

`KobwebTabler` is the single convenience entry point. It implements component
function interfaces by delegation:

```kotlin
data object KobwebTabler :
  AvatarComposable by AvatarDsl,
  CardComposable by CardDsl,
  TableComposable by TableDsl
```

Kotlin does not permit an on-demand (`.*`) import from an object. Consumers
can import the specific facade entry point they use and call it unqualified:

```kotlin
import com.github.jangalinski.tabweb.KobwebTabler.avatars

avatars {
  avatar {
    initials(Initials("AB"))
  }
}
```

Alternatively, import `KobwebTabler` once and use qualified calls, or make it
the receiver of a local DSL section:

```kotlin
with(KobwebTabler) {
  avatars { avatar { initials(Initials("AB")) } }
}
```

The concept package remains authoritative: `AvatarDsl` and `AvatarComposable`
are not duplicate component models. The facade delegates only page-level entry
points and application helpers; it does not expose every internal part,
variant, CSS class, or builder verb globally.

### 4. Data specifications and runtime state are separate concerns

`Tabler.Component` gives a stable render contract; it does not make an
anonymous implementation serializable, immutable, or suitable for transport.
When code generation or a remote data source needs such a representation, the
concept additionally defines a pure `*Spec` / `*Data` model with only stable
values. Its renderer converts the specification to the canonical component
tree. Generated Kotlin may also construct components directly through the
factories.

Runtime state, callbacks, DOM references, and third-party JavaScript handles
do not belong in a `*Spec`. They are supplied by the composable entry point or
a focused controller/state adapter.

### 5. Type-Safe Tabler Components

The instance contract and scoped DSL are also the boundary that makes the
library type-safe. A component exposes only the Tabler variants, parts, and
child roles that are supported by its concept package. For example, an
`Avatar` accepts `AvatarSize`, `AvatarStyle`, and its typed image, icon, or
initials content; a caller cannot accidentally apply `card-stacked`,
`table-hover`, or an unrelated card-header part to it through the normal API.

This is preferable to publishing an unrestricted catalogue of Tabler CSS class
names or accepting an arbitrary Kobweb `Modifier` on every property. Such an
API would allow any Tabler class and any modifier to be attached to any
element, which loses semantic guidance, permits invalid DOM/class
combinations, and makes generated component trees harder to validate.

Use the following boundaries:

- Model documented options as concept-specific enums, sealed types, and typed
  component properties. A finite Tabler CSS family becomes a finite Kotlin
  type, not a caller-provided string.
- Keep component parts and nesting in their owning scope. `AvatarListScope`
  can add avatars; it cannot add a table row or a card header. The same rule
  applies to composite components such as cards, tables, and modals.
- Accept a root `Modifier` only where the component explicitly supports
  integration with surrounding Kobweb layout. Apply it to the documented root
  element after the component's required classes. Do not make a raw modifier a
  substitute for typed Tabler options.
- Keep raw CSS-class access and arbitrary composable content as deliberate,
  documented escape hatches for combinations outside the supported model. They
  are not part of the generated/configuration path and must not redefine the
  component's required structure.

The result is a constrained authoring surface: code generation and page DSLs
can create only supported component trees by default, while expert consumers
still have an explicit path when a reference-supported customization has not
yet been modelled.

The current foundation option types still implement `Modifier`; the stricter
option-to-CSS boundary is tracked as pending proof in
[Type-Safe Component Options Without `Modifier`](type-safe-options-without-modifier.md).

## Applicability to the Tabler Component Inventory

The component analysis supports this design, but the shape of the model varies
by component family.

| Family | Examples | Instance path | DSL path | Boundary |
| --- | --- | --- | --- | --- |
| Leaf / fixed structure | avatar, badge, status, spinner, tag, trending | One interface plus enum/value options. | Optional concise factory-like function. | Usually fully typed and generator-friendly. |
| Composite / structural | card, accordion, list group, chat, timeline, progress, carousel | Root plus typed child components or a typed child list. | Nested scopes build the same child components. | The root renderer owns the wrapper and ordered parts. |
| Structured data | datagrid, tracking, table | Pure `*Data` / sealed cells may implement or map to the root component. | A builder may construct the same data model. | Preserve a separate arbitrary-content escape hatch where required. |
| Stateful Bootstrap UI | dropdown, tab, modal, offcanvas, toast, popover, tooltip | A structural component describes markup and stable options. | A composable function accepts state, callbacks, IDs, and triggers. | Bootstrap lifecycle and ARIA state are runtime concerns, not component data. |
| Imperative plugin integration | ApexCharts charts, Mapbox maps | A pure chart/map spec captures options and data. | A composable host creates, updates, and disposes the JavaScript instance. | Do not store external handles or Compose state in the component instance. |

### Cards

Cards fit well. A `Card` component can own the card root while typed parts
represent header, body, footer, image, actions, and table/list-group slots.
The card DSL builds the same part list and invokes `Card`. Arbitrary page body
content remains a documented composable escape hatch: it cannot be included in
a serializable `CardSpec`, but the root-card renderer is still shared.

### Tables

Tables fit only with the existing two-mode boundary retained. `TablerTableData`
and its sealed `TablerTableCell` hierarchy are the generator-friendly,
serializable configuration path. A table DSL with arbitrary `@Composable`
cells is intentionally not serializable and cannot be losslessly converted to
that data model.

Both modes must converge before markup is emitted: share a table-shell
renderer and typed row/cell renderers where possible, while permitting a
composable-cell leaf only in the DSL mode. Do not force arbitrary composable
cells into the data hierarchy or reduce the data model to opaque lambdas.

### Charts and maps

Charts and maps validate the facade and specification portions of the pattern,
but not the assumption that every renderer is pure markup. A `ChartSpec` can
be generated and nested in a card; its composable host needs `remember` plus
an effect that creates, updates, and disposes the ApexCharts or Mapbox
instance. Recomposition must update or replace that external instance without
leaking it. The specification is still shared, whereas runtime lifecycle code
is necessarily compositor-specific.

### Bootstrap-driven overlays and navigation

Modals, offcanvas panels, tabs, dropdowns, popovers, and toasts need stable
IDs, triggers, ARIA relationships, and possibly Bootstrap JavaScript. The
component instance owns the supported structural tree; a controller or
explicit `isOpen`/event API owns runtime behavior. A DSL must not silently
invent unstable IDs on every recomposition.

## Rules

- A concept has one renderer for each structural node. Factory, data, and DSL
  paths delegate to that renderer rather than reproducing DOM markup.
- Use typed component properties and sealed variants for known Tabler roles.
  Use arbitrary composable slots only as explicit, documented escape hatches.
- Keep a `*Spec` / `*Data` model distinct from runtime state whenever data must
  be generated, serialized, diffed, or tested without a browser.
- Put concept-specific scopes in that concept package, in files named after
  their scope type; keep them internal where they are not part of the intended
  DSL surface.
- Delegate only non-conflicting page-level `*Composable` methods through
  `KobwebTabler`. Keep generic child verbs scoped to avoid facade ambiguity.
- Do not promise `import KobwebTabler.*`: Kotlin prohibits star imports from
  objects. Import a specific member such as `KobwebTabler.avatars`, or import
  `KobwebTabler` for qualified calls or a local `with(KobwebTabler)` receiver.
- The supported Tabler preview structures remain the compatibility boundary;
  this pattern does not authorize arbitrary combinations of Tabler classes.

## Consequences

- Consumers can choose model-first composition or fluent page composition
  without choosing a different visual implementation.
- Generators have a typed target, while runtime-heavy concepts retain proper
  Kotlin/Compose lifecycle management.
- Component packages own their data, scopes, functions, rendering, and CSS
  options together; `_foundation` remains a small shared vocabulary.
- Existing APIs migrate incrementally. Public functions and data classes are
  retained or forwarded until a separately approved breaking change.
