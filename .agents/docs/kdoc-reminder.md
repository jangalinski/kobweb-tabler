# KDoc Reminder for `tabweb`

This note exists so the shared library stays readable while the Kobweb POC is still evolving.

## Reminder

- Add KDocs to new public declarations in `tabweb`.
- Use a multi-line KDoc block for public functions, factory overloads, and DSL
  verbs. Describe the behavior, document every parameter with `@param`, and
  document the result with `@return` (including `Unit` for composable and DSL
  functions).
- Explain the reason for layout and navigation helpers, not just their mechanics.
- Keep the comments explicit for now, even if they feel verbose.
- It is fine to remove or shorten them later once the API has stabilized.

## Why this matters here

- `tabweb` is an opinionated support library, so consumers need to understand the intended shape quickly.
- The library currently has a layout POC and shared navigation helpers that are easier to use when the intent is documented inline.
