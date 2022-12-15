(ns aoc.day3
  (:require [clojure.string :as string]
            [clojure.set :as c.set]))

(defn- generate-priority-map [first-unicode priority-offset]
  (->> (range 0 26)
       (map (juxt (partial + first-unicode) (partial + priority-offset)))
       (map (fn [[letter-unicode priority]]
              [(char letter-unicode) priority]))
       (into {})))

(def priority-map
  ^{:private true}
  (merge
    (generate-priority-map 65 27)
    (generate-priority-map 97 1)))

(defn summarize-duplicated-items-priority [input]
  (->> (string/split-lines input)
       (map seq)
       (map #(partition (-> % count (/ 2)) %))
       (map (partial map set))
       (map (partial apply c.set/intersection))
       (map first)
       (map priority-map)
       (apply +)))


(defn summarize-group-badges-priority [input]
  (->> (string/split-lines input)
       (map seq)
       (partition 3)
       (map (partial map set))
       (map (partial apply c.set/intersection))
       (map first)
       (map priority-map)
       (apply +)))

(defn execute [input-path part]
  (let [input (slurp input-path)]
    (condp = part
      :first (summarize-duplicated-items-priority input)
      :second (summarize-group-badges-priority input))))

(comment

  (execute "resources/inputs/day3.txt" :first)
  (execute "resources/inputs/day3.txt" :second)

  )