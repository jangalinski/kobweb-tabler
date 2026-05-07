---
id: github-gh-issue-body-format
title: GitHub Issue Body Formatting

tags:
  - github
  - gh
  - issue
  - format

agents:
  - all

prio: SHOULD

status: active
---

# GitHub Issue Body Formatting

When creating or updating GitHub issues, write the body as real Markdown, not as escaped newline text.

## Summary

- Use actual blank lines between paragraphs.
- Do not embed literal `\n` or `\n\n` sequences in issue text.
- Keep the first paragraph short and descriptive.
- Put links on their own paragraph when they help readability.
- Prefer a consistent shape: short description, blank line, docs or reference link.

## Example

```md
A line chart depicts trends and behavior over time by connecting data points with a line.

Docs: https://apexcharts.com/docs/chart-types/line-chart/
```

## Notes

- This keeps issues readable in the GitHub UI and in API payloads.
- It also avoids accidental literal escape sequences when shell quoting is used.
