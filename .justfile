
_list:
  just --list

# Run taggesieg example
[group("examples")]
run-tagessieg:
  kobweb run -p _examples/tagessieg -l static --env=dev

# Export taggesieg example
[group("examples")]
export-tagessieg:
  kobweb export -p _examples/tagessieg -l static

# export the site and preview it over python3 on port 10102
[group("examples")]
preview-tagessieg:
    #just export-taggesieg
    echo "Preview at http://localhost:10102"
    cd ./_examples/tagessieg/.kobweb/site && python3 -m http.server 10102
