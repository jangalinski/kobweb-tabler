
set shell := ["zsh", "-lc"]

_list:
    just --list

# Run the documentation site via kobweb in static layout.
[group("site")]
run-site:
    kobweb run -p site -l static --env=dev

# Export the documentation site using the same static layout as the deployment
# workflow. Local exports intentionally keep the local base path `/`; only the
# GitHub Pages workflow adds the repository prefix.
[group("site")]
export-site:
    @just stop-site
    .agents/bin/gradlew-agent --no-watch-fs :site:kobwebExport -PkobwebReuseServer=false -PkobwebEnv=DEV -PkobwebRunLayout=STATIC -PkobwebBuildTarget=RELEASE -PkobwebExportLayout=STATIC --console=plain

# Export and preview the documentation site. Pass `true` to serve
# the most recent existing export without cleaning or exporting again.
[group("site")]
preview-site skip_export="false":
    @if test "{{skip_export}}" = "true"; then if ! test -f build/site-preview/index.html; then echo "No previous static export found at build/site-preview. Run 'just export-site' first." >&2; exit 1; fi; else if test "{{skip_export}}" != "false"; then echo "Usage: just preview-site [true|false]" >&2; exit 2; fi; just clean-preview-artifacts; just export-site; rm -rf build/site-preview; mkdir -p build/site-preview; cp -R site/.kobweb/site/. build/site-preview/; fi
    echo "Preview at http://localhost:13131/"
    python3 -m http.server 13131 --directory ./build/site-preview

# Stop local documentation site servers.
[group("site")]
stop-site:
    just stop

# Stop local kobweb/python servers listening on the preview ports.
[group("kobweb")]
stop:
    @for port in 13130 13131; do pids="$(lsof -tiTCP:$port -sTCP:LISTEN 2>/dev/null || true)"; if [ -n "$pids" ]; then echo "Stopping listeners on port $port: $pids"; kill $pids; fi; done; sleep 1; for port in 13130 13131; do pids="$(lsof -tiTCP:$port -sTCP:LISTEN 2>/dev/null || true)"; if [ -n "$pids" ]; then echo "Force-stopping listeners on port $port: $pids"; kill -9 $pids; fi; done; just clean-preview-artifacts

# Remove stray origin-named preview directories created by local browser or server sessions.
[group("project")]
clean-preview-artifacts:
    @find . -maxdepth 1 -type d \( -name '127.0.0.1:*' -o -name 'localhost:*' \) -prune -exec rm -rf {} +

# Reset all ignored local state while preserving tracked files and unignored files.
[group("project")]
clean mode="":
    @if test "{{mode}}" = "-n"; then git clean -ndX -- .; elif test -z "{{mode}}"; then ./gradlew --stop; git clean -fdX -- .; else echo "Usage: just clean [-n]" >&2; exit 2; fi

# generate dokka html
[group("project")]
generate-dokka-html:
  @./gradlew --no-daemon --no-watch-fs --console=plain :lib:dokkaGeneratePublicationHtml

# tabler icon from css
[group("project")]
generate-tabler-icon:
  @./gradlew --no-daemon --no-watch-fs --console=plain :lib:generateTablerIcon
