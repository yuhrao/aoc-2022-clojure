# Advent of Code 2022

Advent of code challenges written in clojure :heart:

## Running tests

Using clojure CLI
```shell
# Run once
clojure -X:test
# Watching files
clojure -X:test ':watch?' true ':fail-fas?' true
```

Using babashka tasks

```shell
# Run once
bb test
# Watching files
bb test-watch
```