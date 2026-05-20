# Local Task Automation

Decision: whenever we need local task automation for this repo, we use a combination of Kotlin-based Gradle tasks in `gradle/build-logic` and thin `just` recipes that call them.

Rationale:
- Kotlin keeps the automation close to the rest of the build and avoids a second scripting language.
- `gradle/build-logic` is the right place for reusable build behavior and task implementations.
- `just` stays as the user-facing entrypoint for short commands like `run-tagessieg`, `export-tagessieg`, `preview-tagessieg`, and `stop-tagessieg`.

Constraint:
- Do not add Python helper scripts for local build or preview automation.
- If a new local automation step is needed, prefer a Kotlin Gradle task first, then expose it through `just` if it should be user-facing.
