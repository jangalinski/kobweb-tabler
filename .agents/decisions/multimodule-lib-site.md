# Decision: Multimodule `lib` / `site` Repository Layout

## Status

Accepted for issue #92 implementation after user approval.

## Context

`kobweb-tabler` started as a single Kobweb library project at the repository root. The repository now needs a
dedicated documentation/demo site that can be exported to GitHub Pages while keeping the published library stable for
JitPack consumers.

The older `_tmp/KotlinBootstrap` project is useful as a structural blueprint because it separates the published library
from a Kobweb site module. It is not a dependency or version blueprint; this repository keeps its current Kotlin,
Kobweb, Compose, and Tabler versions.

## Decision

Split the root build into two Gradle subprojects:

- `:lib` contains the published `kobweb-tabler` Kobweb component library.
- `:site` contains the repository documentation/demo Kobweb application.

The repository root Gradle build becomes the common container for shared metadata and build wiring. `_examples` remains
a separate Gradle build and continues to consume the library through composite build substitution during development.

## Publishing

JitPack must publish only `:lib`.

The published artifact name must remain `kobweb-tabler`, even though the Gradle subproject is named `lib`. Do not let
the artifact coordinate drift to `lib`.

Use the KotlinBootstrap JitPack shape, adapted for this repository:

```yaml
jdk:
  - openjdk17
install:
  - echo "Building only the kobweb-tabler library"
  - chmod +x gradlew
  - ./gradlew :lib:clean :lib:publishToMavenLocal
```

## Documentation Site

GitHub Pages publishes the `:site` export at the repository Pages root:

```text
/kobweb-tabler/
```

The Pages workflow may also export selected examples from `_examples` and assemble them below:

```text
/kobweb-tabler/examples/<example-name>/
```

This lets documentation pages link to live examples without making `_examples` part of the root Gradle build.

## Constraints

- Keep existing public APIs unless a breaking change is explicitly requested.
- Keep dependency versions current; do not downgrade to match `_tmp/KotlinBootstrap`.
- Keep `commonMain` model code free of Compose/Kobweb runtime dependencies where already established.
- Follow package-aligned source layout under each source set.
- Do not add Python helper scripts for build automation.
- Prefer Gradle tasks and thin `just` recipes for local automation.

## Verification

The implementation should prove all three consumers:

```bash
./gradlew :lib:compileKotlinJs --console=plain
./gradlew :lib:jsBrowserTest --console=plain
./gradlew :site:compileKotlinJs --console=plain
./gradlew :site:kobwebExport --console=plain
GRADLE_USER_HOME=/private/tmp/kobweb-tabler-gradle ./gradlew --no-daemon -p _examples :tagessieg:compileKotlinJs --console=plain
git diff --check
```
