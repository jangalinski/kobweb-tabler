# Issue #48 — Tabler Tables WIP

## Current progress

- Added `TablerTableData`, columns, rows, text cells, responsive breakpoints, no-wrap options, sticky headers, and semantic row variants.
- Added `TablerTable(data)` and a **Recent matches** example on the Tagessieg start page.
- The example currently demonstrates `SMALL` responsive behavior, no-wrap, sticky headers, muted cells, and success/warning/danger rows.
- The Tagessieg page renders the table successfully.

## Important implementation note

Using Compose-Web's native `Table`, `Thead`, `Tbody`, `Tr`, `Th`, and `Td` composables caused the whole client page to render blank at runtime, although it compiled successfully.

The current renderer therefore writes escaped, semantic table markup into a Compose-managed wrapper. It produces a real HTML `<table>` and retains the pure-data API, but it cannot place existing Compose components (for example `TablerAvatar`) inside table cells.

## Next steps

1. Investigate and fix or work around the Compose-Web native table-node runtime failure in a way that supports composable cell content.
2. Add a dedicated `TablerTableCard` instead of placing a table inside the regular `TablerCard` body:

   ```text
   .card
   ├── .card-header    title, subtitle, search, actions
   └── table.card-table
   ```

   This makes the table meet the card edges without the normal card-body gap.

3. Replace text-only cells with a pure-data sealed model, initially covering:

   - text (including muted and row-header text)
   - avatar plus name
   - badges/status
   - a collection of tags
   - checkbox state

4. Add header configuration for title, subtitle, search field, and simple action descriptors.
5. Treat interactive sorting, filtering, pagination, selection callbacks, and arbitrary custom composables as a later data-grid scope.

## Design direction

Use two API levels:

- `TablerTable(data)` remains the convenient text-only path for simple, pure-data tables.
- A composable table builder is the general path. Each cell accepts composable content, so tables can embed existing components such as `TablerAvatar`, badges, links, buttons, and tag groups.

Illustrative shape:

```kotlin
TablerTable {
  header { cell("Name"); cell("Status") }
  row {
    cell { TablerAvatar(...) }
    cell { TablerBadge(...) }
  }
}
```

This makes the data model a shortcut, not the universal representation. The native Compose table-node failure must be resolved before implementing the composable-cell API.
