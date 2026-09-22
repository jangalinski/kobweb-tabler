# Tabler CSS reference

Generated from [Tabler Core 1.5.1](https://cdn.jsdelivr.net/npm/@tabler/core@1.5.1/dist/css/tabler.css). Do not edit generated class pages directly; run
`just generate-tabler-css-docs` after changing `cdn-tabler-core`.

This is a complete inventory of static CSS class selectors shipped by the pinned stylesheet. It is a planning
checklist, not a claim that every class is in the supported Kobweb Tabler API.

## Status vocabulary

- **unreviewed** — no library decision yet.
- **foundation candidate** — generic Bootstrap/Tabler utility suitable for `_foundation`.
- **concept-owned candidate** — belongs to its named Tabler concept package.

The generated category and suggested owner are starting points. Review them before exposing a public API or
enforcing ownership through Detekt. Store reviewed decisions in
[`../tabler-css-manifest.tsv`](../tabler-css-manifest.tsv), not in generated class pages.

## Scope

- Bootstrap-derived utilities are grouped below `foundation/`.
- Tabler component classes are grouped below their likely concept package.
- The entry for a responsive variant documents its exact emitted class; responsive families can subsequently be
  represented by one typed Kotlin API rather than one API per breakpoint.

3654 class selectors are currently indexed. See [_index.md](_index.md).
