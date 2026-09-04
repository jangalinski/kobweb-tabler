# Example-aware completion helper for `just`.
#
# Source this after the stock `just --completions zsh` script if you want
# tab-completion for the example argument accepted by `just run`,
# `just export`, and `just preview`.

_just_example_names() {
  local -a examples

  examples=("${(@f)$(find _examples -mindepth 2 -maxdepth 2 -name build.gradle.kts -print 2>/dev/null | sed 's#^_examples/##; s#/build.gradle.kts$##' | sort -u)}")
  compadd -a examples
}

if whence -v _just >/dev/null 2>&1; then
  _just_stock_completion_file=$(whence -v _just 2>/dev/null | awk '{print $NF}')

  if [[ -f $_just_stock_completion_file ]]; then
    source "$_just_stock_completion_file"
    functions -c _just _just_base
  fi
fi

_just() {
  if [[ $CURRENT -eq 3 && ${words[2]} == (run|export|preview) ]]; then
    _just_example_names
    return 0
  fi

  if (( $+functions[_just_base] )); then
    _just_base "$@"
    return $?
  fi

  local -a recipes
  recipes=("${(@f)$(just --summary 2>/dev/null)}")
  compadd -a recipes
}

compdef _just just
