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
├── build.gradle.kts    ← publishes io.github.jangalinski.kotlin.kobweb:kobweb-tabler
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
(`io.github.jangalinski.kotlin.kobweb:kobweb-tabler`) with the local source automatically.
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

A very easy way to share your Kobweb library with the world is to use [JitPack](https://jitpack.io/). You can read more
about that approach on their site, but essentially, your steps will be:

* Edit your library's build script, adding the `maven-publish` plugin.
* Make sure the build script group and versions are set to what you want (or, optionally, configure a publishing block).
* Double check that this is working by running `publishToMavenLocal` from the command line.
* Commit your changes and push them to GitHub.
* In a different project, which will consume your library, add the `jitpack.io` repository and then add a dependency
  to your library's group and artifact:
  ```kotlin
  repositories {
      maven(url = "https://jitpack.io")
  }
  dependencies {
      implementation("group.path.here:project-name-here:<version-here>")
  }
  ```

For a concrete example, you can refer to
this [Kotlin Boostrap library build script](https://github.com/stevdza-san/KotlinBootstrap/blob/master/bootstrap/build.gradle.kts)
which results in the following [JitPack artifact entry](https://jitpack.io/#stevdza-san/KotlinBootstrap).

This above project opts to provide its own publishing block for more control over the artifact name and version, but if
you omit it, it will try to use reasonable defaults from your project's build script settings instead. The group and
version will come directly from the build script values themselves, and the artifact name will be the name of the
project (usually the folder name, but whatever you set in `settings.gradle.kts`).
