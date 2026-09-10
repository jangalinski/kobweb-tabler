From this directory, run:

  httrack 'https://preview.tabler.io/' \
    -O './tabler-preview-static' \
    -r99 \
    -H0 \
    '-https://*/*' \
    '+https://preview.tabler.io/*'

  This will:

  - Save the copy to ./tabler-preview-static
  - Recursively capture same-origin demo pages and assets
  - Follow links deeply (-r99)
  - Prevent traversal to external sites (-H0 plus filters)
  - Rewrite links for local static browsing

  Open it with:

  open ./tabler-preview-static/preview.tabler.io/index.html

  If local file:// browsing has issues with JavaScript, serve it statically:

  cd ./tabler-preview-static/preview.tabler.io
  python3 -m http.server 8080

  Then visit http://localhost:8080/.

----

For local browsing, keep the entire:

  preview.tabler.io/

  That contains the actual mirrored site:

  - HTML pages
  - dist/ CSS and JavaScript
  - static/ images and illustrations
  - preview/ demo CSS
  - favicon and other assets

  You can remove the HTTrack-specific files:

  index.html       # HTTrack landing page; not the Tabler homepage
  backblue.gif
  fade.gif
  hts-log.txt
  hts-cache/

  Deleting hts-cache/ means you can no longer resume or update the mirror, which is fine for your use case. The nested generated 404 pages under paths such as src/, edge/, mobile/, and node_modules/ are also crawl artifacts and can be
  removed if you do not need those broken-link pages. Keep the real preview.tabler.io/error-404.html.

  For a future one-shot mirror without most overhead:

  httrack https://preview.tabler.io/ \
    -O ./tabler-preview-static \
    -I0 -%i0 -%I0 \
    -Q -C0 -o0

  Meaning:

  - -I0: no per-mirror index
  - -%i0: no top-level HTTrack index
  - -%I0: no search index
  - -Q: no log
  - -C0: no cache
  - -o0: do not generate local error pages

  HTTrack still rewrites links and downloads the required CSS, JavaScript, and images; those are part of the usable offline mirror, not HTTrack overhead. Its documentation confirms that the cache is only needed for resume/update
  operations, while the index and logging options are independently configurable. HTTrack command-line guide, HTTrack option reference

---