(ns aoc.day1
  (:require [clojure.string :as string]
            [utils]))

(defn biggest-callories [input]
  (->> (string/split input #"\n\n")
       (map (fn [v]
              (->> (string/split-lines v)
                   (map #(Integer/parseInt %))
                   (reduce + 0))))
       (apply max)))

(defn total-calories-top-3 [input]
  (->> (string/split input #"\n\n")
       (map (fn [v]
              (->> (string/split-lines v)
                   (map #(Integer/parseInt %))
                   (reduce + 0))))
       sort
       reverse
       (take 3)
       (apply +)))

;; https://adventofcode.com/2022/day/1/input
(def execute
  (utils/execute-fn {:first  {:input-path "resources/inputs/day1.txt"
                              :fn         biggest-callories}
                     :second {:input-path "resources/inputs/day1.txt"
                              :fn         total-calories-top-3}}))

(comment
  (execute :first)
  (execute :second)

  )