# Decision: Sealed Cell Model for `TablerTableCell`

## Status

Accepted — implemented in `TablerTableData.kt` and `TablerTable.kt`.  
Future refactoring is expected once badges, labels, and formatted text are needed across other components.

## Context

When implementing rich table cells (avatars, badges, tags, checkboxes) for `TablerTable`, we had to choose between two approaches for expressing cell content.

## Alternatives Considered

### A — Sealed interface (`TablerTableCell` subtypes)

Cell content is described as pure data: `Text`, `AvatarName`, `Badge`, `Tags`, `Checkbox`.  
The renderer dispatches on the sealed type and produces the HTML.

**Pros**

- Serialisable and diffable — a `Badge("Active", "bg-success")` can be stored in a DB, sent over a network, or reconstructed from JSON.
- Testable as plain data — assertions can be written against `TablerTableData` without a DOM or headless browser.
- Enforces a consistent visual language within the table.

**Cons**

- Closed hierarchy — every new visual pattern requires a new subtype and a library change.
- Cannot embed arbitrary composables in the data path.

### B — `@Composable () -> Unit` lambda per cell

Each cell accepts an arbitrary composable lambda.

**Pros**

- Fully open — any component can be embedded without library changes.

**Cons**

- Not serialisable or diffable — lambdas are opaque closures.
- Tests require a headless browser for every assertion.
- Loses the structured data contract.

## Decision

**Use the sealed interface (Alternative A) for `TablerTable(data: TablerTableData)`.**

The `TablerTableData` / `TablerTable(data)` path is explicitly a *configuration* API — suited for data coming from outside (API responses, view-models, serialised state).  
The composable path is covered separately by the DSL overload `TablerTable { row { cell { … } } }`, which accepts any `@Composable` content without restriction.

Both paths co-exist; consumers choose the one that fits their use case.

## Future Refactoring Note

`Badge`, `Tags`, and formatted-text patterns are likely to be needed in other components beyond tables (e.g., stat cards, list items, navigation badges).  
When that happens, extract a shared sealed model (e.g., `TablerContent` or `TablerBadgeSpec`) so the same data types can be reused across components rather than duplicating subtypes.  
This refactoring is **out of scope for now** — do it only when a second component actually needs the same variants.
