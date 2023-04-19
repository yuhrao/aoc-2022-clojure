(ns aoc.day2
  (:require [clojure.string :as string]))

;; For Part 1
;; A | X => Rock
;; B | Y => Paper
;; C | Z => Scissors

;; For Part 2
;X => Lose
;Y => Draw
;Z => Win

(def
  ^{:private true}
  theoretical-choice-map {"A" :rock
                   "X"        :rock
                   "B"        :paper
                   "Y"        :paper
                   "C"        :scissors
                   "Z"        :scissors})

(def
  ^{:private true}
  real-choice-map {"A" :rock
                   "B" :paper
                   "C" :scissors
                   "X" :lose
                   "Y" :draw
                   "Z" :win})

(def
  ^{:private true}
  score-map {:rock     1
             :paper    2
             :scissors 3
             :lose     0
             :draw     3
             :win      6})

(defn- prepare-input [input]
  (->> (string/split-lines input)
       (map #(string/split % #"\s"))))

(defn- match-result [opponent-choice my-choice]
  (if (= opponent-choice my-choice)
    :draw
    (condp = [opponent-choice my-choice]
      [:rock :paper] :win
      [:paper :scissors] :win
      [:scissors :rock] :win
      ;; else
      :lose)))

(defn calculate-theoretical-score [input]
  (let [matches (map (fn [choices]
                      (map theoretical-choice-map choices))
                    (prepare-input input))]
    (reduce (fn [total [opponent me]]
              (let [choice-point (score-map me)
                    result (match-result opponent me)
                    result-points (score-map result)]
                (+ total choice-point result-points)))
            0 matches)))

(defn- find-out-choice [opponent-choice expected-result]
  (condp = expected-result
    :draw opponent-choice
    :win (condp = opponent-choice
           :rock :paper
           :paper :scissors
           :scissors :rock)
    :lose (condp = opponent-choice
           :paper :rock
           :scissors :paper
           :rock :scissors)))

(defn calculate-real-score [input]
  (let [matches (map (fn [choices]
                      (map real-choice-map choices))
                    (prepare-input input))]
    (reduce (fn [score [opponent-choice expected-result]]
              (let [result-points (score-map expected-result)
                    expected-choice (find-out-choice opponent-choice expected-result)
                    choice-points (score-map expected-choice)]
                (+ score result-points choice-points)))
            0 matches)))

(def execute
  (utils/execute-fn {:first  {:input-path "resources/inputs/day2.txt"
                              :fn         calculate-theoretical-score}
                     :second {:input-path "resources/inputs/day2.txt"
                              :fn         calculate-real-score}}))

(comment
  (execute :first)
  (execute :second)

  )