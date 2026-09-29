# Decision: Type-Safe Component Options Without `Modifier`

## Status

Pending — needs proof.

Tracked by [issue #113](https://github.com/jangalinski/kobweb-tabler/issues/113).

## Context

The component-instance and scoped-DSL design is intended to prevent an avatar
option from being applied to a card, or an unrelated Tabler CSS class from
being attached to a component through its normal API.

Today, `Tabler.Size` and `Tabler.Style` extend Kobweb `Modifier`. As a result,
an enum such as `AvatarSize` is both a typed avatar option and a general
modifier that callers can apply to any element. The lazy modifier construction
inside the enum avoids repeated CSS modifier allocation, but the public type
still leaks that modifier capability across component boundaries.

`BackgroundColor` is generated with the same modifier-oriented foundation,
so this is a cross-cutting concern rather than an avatar-only refactoring.

## Proposal

Reshape the semantic option interfaces in `_foundation` so component options
do not publicly implement `Modifier`. Preserve lazy construction, but move the
CSS conversion behind the owning concept renderer:

```kotlin
enum class AvatarSize : Tabler.Size { XS, S, M, L }

internal fun AvatarSize.toAvatarModifier(): Modifier = lazyAvatarModifiers.get(this)
```

The public component API accepts `AvatarSize`; only the avatar renderer can
turn it into `avatar-sm`, `avatar-lg`, and related CSS. The equivalent
mechanism must support styles, colors, directions, behaviors, and generated
`BackgroundColor` values without creating a global raw-class catalogue.

Root `Modifier` parameters remain an explicit integration escape hatch for
Kobweb layout. This proposal concerns semantic Tabler options, not the normal
ability to position a component in a page.

## Proof Required

- Establish a foundation contract that lets every current concept render its
  typed options without publicly exposing `Modifier`.
- Preserve lazy CSS modifier construction and verify that no repeated mapping
  allocations occur on normal recomposition paths.
- Regenerate or adapt `BackgroundColor` and compile every generated source
  consumer.
- Migrate at least avatar plus one structurally different component (for
  example card or table) to prove the mapping is not avatar-specific.
- Verify that public APIs no longer accept a component-specific option wherever
  a general `Modifier` is expected, except for documented conversion APIs if
  one is deliberately retained.
- Run library browser tests, the site compile/export, and the Tagessieg example
  compile after the foundation migration.

## Current Direction

The avatar package may move its public option types to the concept package and
centralize its CSS names in an internal `AvatarCss` holder now. It retains the
existing lazy `Modifier` implementation until this proposal is proven and
implemented across `_foundation` and generated colors.
