# Decision: Internal Compose/Kobweb DOM DSL

## Status

Accepted.

## Context

The library needs to reproduce Tabler's HTML structure while keeping
Compose/Kobweb details contained. Calling Compose HTML primitives directly
throughout components spreads attribute conversion, modifier handling, and
DOM-specific implementation choices across the public component code.

## Decision

The `_compose` package is the internal boundary for Compose/Kobweb DOM code.
It provides the library's small DOM DSL, including helpers such as `KDiv`,
`KSpan`, `KHeader`, and `KFooter`, and accepts Kobweb `Modifier` instances
directly.

Composable code in this library MUST use the `_compose` DSL whenever it
covers the required DOM element. New reusable DOM helpers belong in
`_compose`; they should encapsulate direct use of Compose HTML primitives,
`toAttrs`, and related Kobweb implementation details.

Direct Compose HTML primitives remain allowed only inside `_compose` or where
an exact element or attribute cannot yet be expressed by the DSL. In that
case, add the smallest appropriate `_compose` helper before repeating the
primitive in more than one place.

The DSL is internal implementation infrastructure. Public component APIs
remain composable functions and data/configuration models; they must not
expose Compose HTML element types as part of their contract.

## Consequences

- Component renderers use `KDiv(modifier) { ... }` rather than a raw `Div`
  plus manual modifier-to-attribute conversion.
- Modifier and DOM-attribute conventions have one implementation point.
- The generated DOM can still match Tabler reference markup exactly; a raw
  primitive is retained only where it is structurally necessary.
- New DSL helpers are intentionally narrow and semantic. The package is not a
  general replacement for every Compose HTML element.
