(ns aoc.day-3-test
  (:require [clojure.test :as t]
            [aoc.day3 :as d3]))

(def input "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw")

(t/deftest summarize-duplicated-items-priority
  (let [result (d3/summarize-duplicated-items-priority input)]
    (t/is (= 157 result))))

(t/deftest summarize-group-badges-priority
  (let [result (d3/summarize-group-badges-priority input)]
    (t/is (= 70 result))))