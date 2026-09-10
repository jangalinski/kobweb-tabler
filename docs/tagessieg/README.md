# kobweb-tabler examples

To verify the correct behavior of the kobweb features, we include a couple of examples here.
Each example is a Gradle module under this root build, and depends on the current version of the kobweb-tabler module via `includeBuild(..)`.

## Tagessieg

Demo of a simple dashboard application.

Use `just examples` to list available example modules, then run one with
`just run <example>`, `just export <example>`, or `just preview <example>`.
For zsh completion of the example argument, source
`.idea/scripts/just-example-completion.zsh` after the stock `just --completions zsh`
script.
