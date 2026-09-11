# Decision: Kobweb Local and Deployment Modes

## Status

Accepted.

## Decision

Kobweb projects use two explicit local modes:

- **Live development:** run the Kobweb development server with hot reload,
  using `basePath: "/"` and the project's `...0` port.
- **Static preview:** perform the complete static HTML5 export, retaining
  `basePath: "/"`, then serve the exported files with a plain static server
  such as Python's HTTP server on the project's adjacent `...1` port.

The `...` port prefix is project-specific. For example, a project using the
`1313` prefix serves live development on `13130` and static preview on `13131`.

Deployment workflows may override `basePath` to match the target hosting path,
such as a repository subpath on GitHub Pages. Deployment-specific base paths
belong in the relevant workflow configuration and must not alter the local
development configuration.

## Rationale

Keeping local development at `/` makes routing, links, and hot reload behave
the same across projects and avoids mutating configuration when switching
between live and static preview modes. Serving the full static export locally
provides a faithful check of the HTML generated for deployment while keeping
the deployment path concern isolated to the target workflow.
