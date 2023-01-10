(ns aoc.day-4-test
  (:require [clojure.test :as t]
            [aoc.day4 :as d4]))

(def input "2-4,6-8\n2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8")

(t/deftest count-fully-contained-overlaps
  (let [result (d4/count-fully-contained-overlaps input)]
    (t/is (= 2 result))))

(t/deftest count-overlaps
  (let [result (d4/count-overlaps input)]
    (t/is (= 4 result))))