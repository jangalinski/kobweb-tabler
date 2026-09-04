
set shell := ["zsh", "-lc"]

_list:
    just --list

# List examples discovered from `_examples/*/build.gradle.kts`.
examples:
    @find _examples -mindepth 2 -maxdepth 2 -name build.gradle.kts -print | sed 's#^_examples/##; s#/build.gradle.kts$##' | sort

# Remove stray origin-named preview directories created by local browser or server sessions.
clean-preview-artifacts:
    @find . -maxdepth 1 -type d \( -name '127.0.0.1:*' -o -name 'localhost:*' \) -prune -exec rm -rf {} +

# Reset all ignored local state while preserving tracked files and unignored files.
clean mode="":
    @if test "{{mode}}" = "-n"; then git clean -ndX -- .; elif test -z "{{mode}}"; then ./gradlew --stop; git clean -fdX -- .; else echo "Usage: just clean [-n]" >&2; exit 2; fi

# Run an example via kobweb dev server in static layout (hot-reload, no export needed), for example: `just run tagessieg`
[group("examples")]
run example:
    kobweb run -p _examples/{{ example }} -l static --env=dev

# Run an example using the direct kobweb server (fullstack mode), for example: `just serve tagessieg`
[group("examples")]
serve example:
    kobweb run -p _examples/{{ example }}

# Export an example, for example: `just export tagessieg`
[group("examples")]
export example:
    just clean-preview-artifacts
    @GRADLE_USER_HOME=/private/tmp/{{ example }}-gradle ./gradlew --no-daemon --no-watch-fs -p _examples :{{ example }}:kobwebExport --console=plain

# Export and preview an example, for example: `just preview tagessieg`
[group("examples")]
preview example:
    just clean-preview-artifacts
    just export {{ example }}
    @GRADLE_USER_HOME=/private/tmp/{{ example }}-gradle ./gradlew --no-daemon --no-watch-fs -p _examples :{{ example }}:mirrorExportForPlainStaticServer --console=plain
    echo "Preview at http://localhost:10102/{{ example }}/"
    python3 -m http.server 10102 --directory ./_examples/{{ example }}/.kobweb/site

# Stop local example servers listening on the preview ports.
[group("examples")]
stop:
    @for port in 10101 10102; do pids="$(lsof -tiTCP:$port -sTCP:LISTEN 2>/dev/null || true)"; if [ -n "$pids" ]; then echo "Stopping listeners on port $port: $pids"; kill $pids; fi; done; sleep 1; for port in 10101 10102; do pids="$(lsof -tiTCP:$port -sTCP:LISTEN 2>/dev/null || true)"; if [ -n "$pids" ]; then echo "Force-stopping listeners on port $port: $pids"; kill -9 $pids; fi; done; just clean-preview-artifacts

# (*) Run tagessieg via kobweb in hot-reload
[group("tagessieg")]
run-tagessieg:
    just run tagessieg

# create tagessieg static files
[group("tagessieg")]
export-tagessieg:
    just export tagessieg

# create tagessieg static files and serve via python3 http.server
[group("tagessieg")]
preview-tagessieg:
    just preview tagessieg

# stop servers
[group("tagessieg")]
stop-tagessieg:
    just stop

# Serve tagessieg via kobweb server (fullstack mode)
[group("tagessieg")]
serve-tagessieg:
    just serve tagessieg
