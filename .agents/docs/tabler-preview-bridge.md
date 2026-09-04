---
id: tabler-preview-bridge
title: Tabler Preview Bridge

tags:
  - project
  - kobweb
  - style

agents:
  - all

prio: MUST

refs:
  - title: Tabler preview reference
    url: ../../docs/tabler-preview.md
    type: local-doc
    check: always

status: active
---

# Tabler Preview Bridge

Use this convention when mapping a visual effect, component pattern, or layout detail from the Tabler demo into this Kobweb Tabler project.

## Summary

- Start with `docs/tabler-preview.md` before explaining or implementing Tabler-inspired UI.
- Use the linked HTTrack export for the concrete rendered HTML, CSS, assets, and class structure captured from the live preview.
- Use the linked local Tabler repository clone under `_tmp/tabler-repository` for source-level intent, templates, SCSS, and preview implementation details.
- Compare the Tabler source and rendered extract against this repository's Kotlin components before deciding whether to copy classes, wrap an existing component, or add a new abstraction.
- Prefer reproducing Tabler's actual semantic structure and utility class combinations over approximating from the screenshot alone.

## Workflow

- Read `docs/tabler-preview.md` first to find the current local reference paths.
- For dashboard/home-page effects, inspect `docs/tabler-example/preview.tabler.io/index.html` and the nearby extracted assets.
- For source behavior, inspect `_tmp/tabler-repository/preview` first, then follow imports into `_tmp/tabler-repository/core`, `_tmp/tabler-repository/shared`, or SCSS only as needed.
- Use `docs/tabler-preview-index.png` as a visual check, not as the only source of truth.
- When implementing in Kotlin/Kobweb, keep the resulting API idiomatic for this project while preserving the Tabler class names and DOM shape that make the design work.
