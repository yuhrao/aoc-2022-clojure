(ns aoc.day2-test
  (:require [clojure.test :as t]
            [aoc.day2 :as d2]))

(def input "A Y\nB X\nC Z")

(t/deftest calculate-theoretical-score
  (let [result (d2/calculate-theoretical-score input)]
    (t/is (= 15 result))))

(t/deftest calculate-real-score
  (let [result (d2/calculate-real-score input)]
    (t/is (= 12 result))))
