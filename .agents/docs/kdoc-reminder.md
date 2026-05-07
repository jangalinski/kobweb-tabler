# KDoc Reminder for `kobweb-tabler`

This note exists so the shared library stays readable while the Kobweb POC is still evolving.

## Reminder

- Add KDocs to new public declarations in `kobweb-tabler`.
- Explain the reason for layout and navigation helpers, not just their mechanics.
- Keep the comments explicit for now, even if they feel verbose.
- It is fine to remove or shorten them later once the API has stabilized.

## Why this matters here

- `kobweb-tabler` is an opinionated support library, so consumers need to understand the intended shape quickly.
- The library currently has a layout POC and shared navigation helpers that are easier to use when the intent is documented inline.
