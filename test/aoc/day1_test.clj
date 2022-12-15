(ns aoc.day1-test
  (:require [clojure.test :as t]
            [aoc.day1 :as d1]))

(def input "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000")

(t/deftest biggest-calories
  (t/testing "Getting the biggest one"
    (let [result (d1/biggest-callories input)]
      (t/is (= 24000
               result)))))

(t/deftest total-calories-top-3
  (t/testing "Getting total calories of the top three biggest ones"
    (let [result (d1/total-calories-top-3 input)]
      (t/is (= 45000
               result)))))