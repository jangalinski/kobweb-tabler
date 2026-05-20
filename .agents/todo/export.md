# Static Export TODO

Goal: make navigation and image links work in Kobweb static export without fragile path hacks.

Current known issues:
- Navbar and breadcrumb navigation must resolve correctly when the exported site is opened from a plain static file server.
- Brand/logo image URLs must resolve correctly in static export.
- Prefer a clean Kobweb-native approach if one exists instead of custom export-time path rewriting.

Notes:
- The exported example is `_examples/tagessieg`.
- The issue appears only in exported/static mode, not in normal Kobweb dev mode.
- Reproduce with `kobweb export -p _examples/tagessieg -l static` and open the generated `index.html` through a plain localhost file server.
