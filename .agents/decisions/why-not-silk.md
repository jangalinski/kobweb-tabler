# Decision: Do Not Depend on Silk

## Status

Accepted.

## Context

`kobweb-tabler` recreates the combinations of components and styles used by the
Tabler reference site. Tabler's own CSS is the source of truth for styling,
including class names, variants, responsive behavior, and layout conventions.

Silk is a separate design-system layer. It provides useful abstractions for
projects that define their styling through Silk, but it is not the styling
system used by the Tabler reference site. Using both systems would make the
rendered markup and styling harder to reason about and harder to test.

## Decision

The library and the example site do not declare or use Silk directly. They use
Kobweb core, `kobweb-compose`, Compose HTML where appropriate, and the imported
Tabler CSS.

Tabler components expose composable APIs and typed Kotlin options that map to
existing Tabler classes. Structural wrappers should use the smallest suitable
Compose/Kobweb primitive so the generated markup remains close to the Tabler
reference markup.

This decision does not prohibit `Box`, `Column`, or `Row` from Kobweb's Compose
layer when they are useful for a component API or behavior. Those primitives
are not Silk. They may remain where replacing them would break a public API or
would not improve the rendered result.

## Why

- Tabler CSS remains the single styling source and does not compete with a
  second component/theme system.
- The generated HTML avoids Silk-specific classes and wrappers that do not
  contribute to the Tabler layout.
- DOM assertions and full static exports can verify the actual Tabler markup
  and classes without having to account for Silk-generated styling state.
- The public API models Tabler concepts directly instead of translating them
  through a different design-system vocabulary.

## Consequences

We do not get Silk's ready-made widgets, theme helpers, or styling utilities.
When a Tabler feature needs such behavior, `kobweb-tabler` provides a focused
composable, modifier, or data/configuration API and maps it to Tabler CSS.
Document-level attributes and other page-shell behavior are handled by the
site/layout integration directly.

The decision is tracked by [issue #101](https://github.com/jangalinski/kobweb-tabler/issues/101).
