# Module kobweb-tabler

`kobweb-tabler` provides Tabler-inspired components for Kotlin/JS Kobweb applications.

The library is published as a Kobweb library module and contributes its required Tabler CSS, Tabler Icons CSS, Tabler JavaScript, and ApexCharts script through Kobweb library metadata. Consumers can depend on the module and use its components without copying those external assets into each site.

The API is split into a few package families:

- `components` contains complete visual components and page building blocks.
- `models` contains data-first configuration objects used by those components.
- `layouts` contains Kobweb layout entry points for shared page chrome.
- `elements` contains smaller reusable HTML helpers.
- `charts` contains JavaScript-backed chart wrappers.
- `styles` contains shared Tabler class-name and layout constants.

For tables, prefer `TablerTable(data: TablerTableData)` when the table is driven by structured data. Use the `TablerTable { header { } row { cell { } } }` DSL when cells need arbitrary composable content.
