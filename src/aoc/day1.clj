(ns aoc.day1
  (:require [clojure.string :as string]))

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
(defn execute [input-path part]
  (let [input (slurp input-path)]
    (condp = part
      :first (biggest-callories input)
      :second (total-calories-top-3 input))))

(comment
  (execute "resources/inputs/day1.txt" :first)
  (execute "resources/inputs/day1.txt" :second)

  )