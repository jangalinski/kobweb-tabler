
set shell := ["zsh", "-lc"]

_list:
  just --list

# Run taggesieg example
[group("examples")]
run-tagessieg:
  kobweb run -p _examples/tagessieg -l static --env=dev

# Export taggesieg example
[group("examples")]
export-tagessieg:
  @GRADLE_USER_HOME=/private/tmp/tagessieg-gradle ./gradlew --no-daemon --no-watch-fs -p _examples :tagessieg:kobwebExport --console=plain

# export the site and preview it over python3 on port 10102
[group("examples")]
preview-tagessieg:
    just export-tagessieg
    @GRADLE_USER_HOME=/private/tmp/tagessieg-gradle ./gradlew --no-daemon --no-watch-fs -p _examples :tagessieg:mirrorExportForPlainStaticServer --console=plain
    echo "Preview at http://localhost:10102/tagessieg/"
    python3 -m http.server 10102 --directory ./_examples/tagessieg/.kobweb/site

# Stop local tagessieg servers listening on the preview ports.
[group("examples")]
stop-tagessieg:
    @for port in 10101 10102; do pids="$(lsof -tiTCP:$port -sTCP:LISTEN 2>/dev/null || true)"; if [ -n "$pids" ]; then echo "Stopping listeners on port $port: $pids"; kill $pids; fi; done; sleep 1; for port in 10101 10102; do pids="$(lsof -tiTCP:$port -sTCP:LISTEN 2>/dev/null || true)"; if [ -n "$pids" ]; then echo "Force-stopping listeners on port $port: $pids"; kill -9 $pids; fi; done
