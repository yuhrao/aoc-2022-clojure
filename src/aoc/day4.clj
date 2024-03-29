(ns aoc.day4
  (:require
    [clojure.set :as set]
    [clojure.string :as string]))


(defn parse-boundries [raw-boundries]
  (let [[start end] (->> (string/split raw-boundries #"-")
                         (map #(Integer/parseInt %)))]
    {:start start :end end}))

(defn- partial-parse-input [input]
  (->> input
       (string/split-lines)
       (map #(string/split % #","))))

(defn count-fully-contained-overlaps
  "Count the number of fully contained overlaps in the input."
  [input]
  (->> input
       partial-parse-input
       (map (fn [elves]
              (map parse-boundries elves)))
       (map (fn [[elf1 elf2]]
              (or (and (>= (:start elf1) (:start elf2))
                       (<= (:end elf1) (:end elf2)))
                  (and (>= (:start elf2) (:start elf1))
                       (<= (:end elf2) (:end elf1))))))
       (filter true?)
       count))

(defn- boundries->set [raw-boundries]
  (let [[start end] (->> (string/split raw-boundries #"-")
                         (map #(Integer/parseInt %)))]
    (set (range start (inc end)))))

(defn count-overlaps [input]
  (->> input
       partial-parse-input
       (map (fn [elves]
              (map boundries->set elves)))
       (map (partial apply set/intersection))
       (filter #(not-empty %))
       count))

(def execute
  (utils/execute-fn {:first  {:input-path "resources/inputs/day4.txt"
                              :fn         count-fully-contained-overlaps}
                     :second {:input-path "resources/inputs/day4.txt"
                              :fn         count-overlaps}}))

(comment
  (execute :first)
  (execute :second)
  )