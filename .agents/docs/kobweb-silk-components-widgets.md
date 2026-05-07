# Kobweb / Silk Building Blocks

This note collects the concrete building blocks that Kobweb exposes on top of Compose HTML and Kotlin/JS, with a focus on the layout primitives that are useful when building `kobweb-tabler`.

Most of the reusable UI surface is in `kobweb-compose-js`; `kobweb-silk-js` mainly provides the `SilkApp` entry point and the color-mode aware startup wiring.

| Name | Description | Deep Link |
| --- | --- | --- |
| `SilkApp` | Top-level Silk entry point. Wraps `KobwebApp`, installs compose styles, Silk foundation styles, and color mode support. | [SilkApp.kt](https://github.com/varabyte/kobweb/blob/main/frontend/silk-foundation/src/jsMain/kotlin/com/varabyte/kobweb/silk/SilkApp.kt) |
| `KobwebComposeStyles` | Installs the shared Kobweb compose stylesheet. | [KobwebComposeStyles.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/KobwebComposeStyles.kt) |
| `Box` | Basic layout primitive for stacking children in a single container. Useful for page shells, wrappers, and card containers. | [Box.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Box.kt) |
| `Row` | Horizontal layout primitive built on CSS flexbox. | [Row.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Row.kt) |
| `Column` | Vertical layout primitive built on CSS flexbox. | [Column.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Column.kt) |
| `Spacer` | Empty layout primitive used to create flexible gaps inside rows and columns. | [Spacer.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Spacer.kt) |
| `Modifier` | Core builder for attaching class names, style properties, attributes, and layout behavior to Kobweb composables. | [Modifier.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/ui/Modifier.kt) |
| `BoxScope` | Scope receiver for `Box` content. Adds alignment helpers for child placement. | [Box.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Box.kt) |
| `RowScope` | Scope receiver for `Row` content. Adds vertical alignment helpers for children. | [Row.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Row.kt) |
| `ColumnScope` | Scope receiver for `Column` content. Adds horizontal alignment helpers for children. | [Column.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Column.kt) |
| `Alignment` | Alignment model used by the layout primitives and their scope helpers. | [Alignment.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/ui/Alignment.kt) |
| `Arrangement` | Arrangement model for spacing and distribution inside `Row` and `Column`. | [Arrangement.kt](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/src/jsMain/kotlin/com/varabyte/kobweb/compose/foundation/layout/Arrangement.kt) |

## Notes

- `SilkApp` is the only Silk-specific composable in the exported source surface that is immediately visible in the `kobweb-silk-js` source jar.
- The layout primitives are in `kobweb-compose-js`, and they are the pieces you generally want to use when building Silk-first components.
- `kobweb-tabler` should prefer these primitives over raw HTML wrappers wherever possible.
