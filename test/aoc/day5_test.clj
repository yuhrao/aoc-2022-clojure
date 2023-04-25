(ns aoc.day5-test
  (:require [clojure.test :as t]
            [aoc.day5 :as d5]))

(def input "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2")

(t/deftest get-crates-on-top
  (let [result (d5/get-crates-on-top input)
        expected-result "CMZ"]
    (t/is (= expected-result result))))
