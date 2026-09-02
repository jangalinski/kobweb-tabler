# Kobweb/Tabler

`kobweb-tabler` is a reusable Kobweb/Silk support library for building Tabler-styled pages with Kotlin.

Its goal is to let a Kobweb app render either:

- a dynamic page that runs in the browser, or
- a statically generated page that is exported during build time

while still using the Tabler layout system and charting primitives.

## Mission statement:

> Build a Kobweb/Silk page that is either dynamic or static generated using Tabler layout and charts.

### References:

- Kobweb: https://kobweb.varabyte.com/docs/concepts/foundation/
- Tabler docs: https://tabler.io/docs
- ApexCharts docs: https://apexcharts.com/docs/
- Tabler icons: https://tabler.io/icons


## Build Locally

### Project layout

```
kobweb-tabler/          ← the public library (this repo root)
├── src/jsMain/…        ← library source
├── build.gradle.kts    ← publishes com.github.jangalinski:kobweb-tabler
└── _examples/          ← standalone Kobweb apps that consume the library
    ├── settings.gradle.kts
    └── tagessieg/      ← example Kobweb app
```

`_examples` is a **separate Gradle build** — it has its own `settings.gradle.kts` and its own Gradle wrapper.
It is not a sub-project of the root build; it is a sibling that references the library via a
[composite build](https://docs.gradle.org/current/userguide/composite_builds.html):

```kotlin
// _examples/settings.gradle.kts
includeBuild("../")   // ← substitutes the library dependency from source
```

### How the two builds relate

Because `_examples` uses `includeBuild("../")`, Gradle **substitutes** the published Maven artifact
(`com.github.jangalinski:kobweb-tabler`) with the local source automatically.
You do **not** need to run `publishToMavenLocal` first — any change you make in the library source is
picked up immediately when you build inside `_examples`.

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

---

## Kobweb Library

This is a [Kobweb](https://github.com/varabyte/kobweb) project bootstrapped with the `library` template.

This template is useful if you want to create a re-usable library that can be consumed by other Kobweb projects. The
biggest difference between a Kobweb library and a Kobweb application is that the library applies the
`com.varabyte.kobweb.library` Gradle plugin instead in its build script.

## JitPack

This public library is published on demand by [JitPack](https://jitpack.io/) from Git tags. Its first release is
`0.0.1`; consumers use the following repository and dependency configuration:

```kotlin
dependencyResolutionManagement {
  repositories {
    mavenCentral()
    maven {
      url = uri("https://jitpack.io")
      content { includeGroup("com.github.jangalinski") }
    }
  }
}

dependencies {
  implementation("com.github.jangalinski:kobweb-tabler:0.0.1")
}
```

No JitPack credentials are required for this public repository. Before tagging a release, verify the generated
publications locally:

```bash
./gradlew publishToMavenLocal
```

JitPack runs the same command using Java 17, as configured in [`jitpack.yml`](jitpack.yml).
