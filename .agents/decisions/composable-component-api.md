# Decision: Composable Component API

## Status

Accepted.

## Decision

Tabler layouts, components, styles, and elements expose a composable function
API as their primary public surface.

When a suitable high-level Kobweb or Silk composable exists, prefer it over a
lower-level Compose or raw HTML primitive. For example, prefer Kobweb/Silk
`Box`, `Column`, and `Row` over `Div` and `Span` when the resulting structure
and semantics are appropriate. Lower-level primitives remain valid when they
are needed for exact HTML semantics, attributes, or Tabler markup.

For selected components that are primarily data- or configuration-driven, also
provide a convenience function accepting a prefilled data class. That function
internally creates the composables according to the component's conventions.
The composable API remains the flexible path for custom content; the data-class
API provides a consistent path for common structured use cases.

Over time, the `components` package should contain one source file and primary
composable function for each Tabler component. Supporting models, builders,
and private implementation helpers may live alongside that component or in
their appropriate `models`, `styles`, or `elements` packages.

## API shape

The flexible composable form is the default:

```kotlin
@Composable
fun TablerCard(content: @Composable () -> Unit) {
  // custom composable content
}
```

Where a component benefits from structured data, add a data-driven form:

```kotlin
@Composable
fun TablerCard(data: TablerCardData) {
  TablerCard {
    // render data using the component conventions
  }
}
```

The exact overload shape may vary when Kotlin overload resolution or
type-safety requires a distinct name, but both forms must converge on the same
visual and semantic conventions.

## Rationale

Composable functions make the library natural to use from Kobweb and Silk,
allow consumers to customize content, and keep layout composition explicit.
High-level primitives reduce incidental DOM code while preserving the option
to use lower-level elements where Tabler's required markup demands them.
Data-class overloads make common configuration-driven components concise,
consistent, and straightforward to test without removing the composable escape
hatch.
