# Decision: Concept-Oriented Package Structure

## Status

Accepted.

## Context

The default Kobweb package split (`components`, `styles`, `layouts`, and
`models`) groups code by implementation technique. Tabler, however, documents
and composes its UI by concepts such as navbar, card, button, icon, and badge.

A technical split scatters one concept across several packages. In particular,
Tabler CSS class references are usually meaningful only inside their owning
concept; publishing a global `ClassNames` catalogue invites accidental use and
makes ownership unclear.

## Decision

Packages are organized primarily by Tabler UI concept. A concept package owns
its complete public and internal implementation: its renderer, data model,
builders, concept-specific CSS-class references, and focused helpers.

Examples:

```text
navbar/  TablerNavbar, TablerNavbarData, TablerNavbarItem, navbar CSS
button/  button API, variants, sizes, button CSS
card/    card API, card data, card CSS and builders
icon/    Icon, TablerIcon, icon-specific variants
badge/   badge API, badge data and badge CSS
```

Two narrowly scoped technical packages support every concept:

- `_compose` is the **internal DOM adapter**. It wraps Compose HTML and
  Kobweb modifier/attribute mechanics in helpers such as `KDiv` and `KNav`.
  It contains no Tabler component model or component-specific CSS catalogue.
- `foundation` is the **semantic base**. It contains the small cross-concept
  vocabulary (`Component`, `Style`, `Behavior`, `Size`) and only other
  genuinely cross-concept abstractions. It does not contain DOM helpers or a
  catch-all CSS registry.

There is no general `util` package. A helper belongs in its owning concept; a
helper that only adapts Compose/Kobweb DOM belongs in `_compose`; a stable,
cross-concept semantic primitive belongs in `foundation`.

## Consequences

- New UI work starts in its concept package, not in `components`, `styles`,
  `layouts`, or `models`.
- Concept-specific CSS names remain package-local implementation details (for
  example `navbar.TablerNavbarCss`), rather than additions to a global
  `ClassNames` API.
- Existing technical packages are legacy migration sources. Do not move or
  delete their contents merely to satisfy the target structure; migrate a
  concept only when changing that concept and preserve public compatibility.
- `foundation.Component` is the common rendering contract; component packages
  define their own concrete types such as `Icon` and `Badge`.
- `@Composable` slots are deliberate escape hatches, not the default model for
  known Tabler roles. Prefer typed concept properties such as `Icon?` and
  `Badge?`.

