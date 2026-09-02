# <img src=".idea/icon.svg" alt="" height="32" style="vertical-align: -0.18em;"> kobweb-tabler

[![JitPack](https://jitpack.io/v/jangalinski/kobweb-tabler.svg)](https://jitpack.io/#jangalinski/kobweb-tabler)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Kobweb](https://img.shields.io/badge/Kobweb-0.25.1-3B82F6)](https://kobweb.varabyte.com/)

> Build a Kobweb/Silk page that is either dynamic or static generated using Tabler layout and charts.


- a dynamic page that runs in the browser, or
- a statically generated page that is exported during build time

while still using the Tabler layout system and charting primitives.

## Use the library

This library is published from Git tags through [JitPack](https://jitpack.io/). Add JitPack and the released
library to a consuming Kotlin project:

```kotlin
dependencyResolutionManagement {
  repositories {
    mavenCentral()
    maven {
      url = uri("https://jitpack.io")
    }
  }
}

dependencies {
  implementation("com.github.jangalinski.kobweb-tabler:kobweb-tabler:0.0.1")
}
```

The repository is public: consumers and GitHub Actions do not need JitPack credentials.

### Development snapshots

JitPack can build the latest commit on a branch. To use the current `main` branch instead of a tagged release:

```kotlin
implementation("com.github.jangalinski.kobweb-tabler:kobweb-tabler:main-SNAPSHOT")
```

Snapshots are changing dependencies, so refresh Gradle's dependency cache when checking a new commit:

```bash
./gradlew compileKotlinJs --refresh-dependencies
```

For a reproducible pre-release check, depend on a specific commit hash instead. See [JitPack's snapshot
documentation](https://docs.jitpack.io/intro/#snapshots) for both forms.


## Build Locally

### Project layout

```
kobweb-tabler/          ← the public library (this repo root)
├── src/jsMain/…        ← library source
├── build.gradle.kts    ← Maven publication configuration
└── _examples/          ← standalone Kobweb apps that consume the library
    ├── settings.gradle.kts
    └── tagessieg/      ← example Kobweb app
```

`_examples` is a **separate Gradle build** — it has its own `settings.gradle.kts` and its own Gradle wrapper.
It is not a sub-project of the root build; it is a sibling that references the library via a
[composite build](https://docs.gradle.org/current/userguide/composite_builds.html).

### How the examples use the local library

The examples' shared version catalog declares a local development coordinate:

```kotlin
implementation("com.github.jangalinski.kobweb-tabler:kobweb-tabler:0.0.1-SNAPSHOT")
```

Its `settings.gradle.kts` uses `includeBuild("../")` with an explicit dependency substitution to replace that
coordinate with the root project's source. Therefore, `_examples` never downloads the snapshot and does not need
`publishToMavenLocal`; library changes are compiled directly when you build an example.

This is intentional for development, but it also means `_examples` cannot prove that JitPack serves a release.
Use a separate project without this `includeBuild` substitution for that check.

### How to run

The repo uses [just](https://just.systems/) for task automation. All recipes are defined in `.justfile`.

```
just --list          # show all available recipes
```

#### Working with examples

```bash
just run tagessieg        # run dev server (static layout, dev env)
just export tagessieg     # export static site
just preview tagessieg    # export + mirror + serve at http://localhost:10102/tagessieg/
just stop                 # stop all local preview/dev servers
```

Backwards-compatible single-example aliases also exist:

```bash
just run-tagessieg
just export-tagessieg
just preview-tagessieg
just stop-tagessieg
```

> **Note:** Because `_examples` wires the library directly from source via `includeBuild("../")`,
> a `clean build` is only needed to force-recompile everything (e.g., after switching branches).
> Incremental compilation works normally — Gradle rebuilds only what changed.

### Produce and test a release

Before creating a Git tag, test the exact release version locally. Using a temporary Maven repository keeps the
test publication out of your normal `~/.m2` cache:

```bash
VERSION=0.0.1 ./gradlew publishToMavenLocal \
  -Dmaven.repo.local=/tmp/kobweb-tabler-m2
```

When that succeeds, create and push the matching release tag (replace the version for later releases):

```bash
git tag -a 0.0.1 -m "Release 0.0.1"
git push origin 0.0.1
```

Also compile the source-backed example to verify normal development usage:

```bash
./gradlew -p _examples :tagessieg:compileKotlinJs
```

After pushing a release tag, JitPack runs the same publication command with Java 17 (see
[`jitpack.yml`](jitpack.yml)). For a quick local verification using Tagessieg:

1. In `gradle/libs.versions.toml`, change `kobweb-tabler` from `0.0.1-SNAPSHOT` to the release version, for example
   `0.0.1`.
2. Temporarily comment out the `includeBuild("../") { ... }` block in `_examples/settings.gradle.kts`.
3. Run:

   ```bash
   ./gradlew -p _examples :tagessieg:compileKotlinJs --refresh-dependencies
   ```

4. Restore the snapshot version and the `includeBuild` block when finished.

`_examples` already declares the JitPack repository, so disabling the composite substitution makes Gradle download the
release. This check verifies JitPack resolution; with `includeBuild` enabled, the same build only verifies the local
source replacement.

---

## References:

- Kobweb: https://kobweb.varabyte.com/docs/concepts/foundation/
  - [Kobweb](https://github.com/varabyte/kobweb) `library` template. This template is useful if you want to create a re-usable library that can be consumed by other Kobweb projects. The
    biggest difference between a Kobweb library and a Kobweb application is that the library applies the
    `com.varabyte.kobweb.library` Gradle plugin instead in its build script.

- Tabler docs: https://tabler.io/docs
  - Tabler Demo: https://tabler.io/admin-template/preview 
  - Tabler icons: https://tabler.io/icons
- ApexCharts docs: https://apexcharts.com/docs/
