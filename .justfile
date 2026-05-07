
_list:
  just --list

# Run taggesieg example
[group("examples")]
run-taggesieg:
  kobweb run -p _examples/tagessieg -l static --env=dev

# Export taggesieg example
[group("examples")]
export-taggesieg:
  kobweb export -p _examples/tagessieg -l static
