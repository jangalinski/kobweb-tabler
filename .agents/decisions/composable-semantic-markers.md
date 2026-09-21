# Decision: Semantic Compose Markers for Tabler Components

## Status

Accepted.

## Context

Tabler documents UI through named concepts such as components, icons, styles,
behaviors, and sizes. Those concepts are more useful to library consumers than
an unbounded `@Composable () -> Unit` slot: a navigation item can say that it
accepts an optional icon and badge, rather than accepting any arbitrary DOM
content.

The Tabler icon reference demonstrates this distinction: icons are a named
component with documented sizing and visual variants, not generic markup
inserted into a page. See the [Tabler Icon documentation](https://docs.tabler.io/ui/components/icon).

## Decision

The `_foundation` package defines the semantic marker vocabulary used by the
library:

- `Component` identifies a renderable Tabler UI concept.
- `Style`, `Behavior`, and `Size` identify the corresponding semantic facets.

`Component` is the rendering boundary. Its canonical call-site syntax is
`component()` or `component(modifier)` through an `invoke` operator. The
explicit `compose` function remains the implementation and compatibility
boundary; it is not removed merely because `invoke` is more concise.

Public component models express permitted content through these semantic types.
For example, a navigation item exposes `Icon?` and `TablerNavbarBadge?`, not a
generic composable-content receiver. This keeps the API discoverable,
serializable where appropriate, and consistently renderable by the owning
component.

## Consequences

- Prefer a property with a concrete semantic type (`Icon`, `Badge`, `Size`,
  etc.) when Tabler defines that role.
- Add a new marker type or a focused sealed model when the role has known,
  reusable variants.
- Use an arbitrary composable slot only when the component genuinely owns an
  intentional customization seam that cannot be expressed by the semantic
  model. Document that seam explicitly.
- Marker interfaces classify concepts; styling and DOM attributes continue to
  be supplied through the internal Kobweb `Modifier` DSL.
- New component APIs should mirror a documented Tabler concept and retain the
  relevant Tabler DOM and class structure in their renderer.
