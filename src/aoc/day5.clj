(ns aoc.day5
  (:require [clojure.string :as str]
            [utils]
            [clojure.string :as string]))

;; Cartes data structure:
;    [D]
;[N] [C]
;[Z] [M] [P]
; 1   2   3
;; => [["Z" "N"] ["M" "C" "D"] ["P"]]
(defn- get-stacks [input]
  (let [stack-lines (-> input
                        (string/split #"\n\n")
                        first
                        string/split-lines)
        stacks      (->> stack-lines
                         (map #(string/split % #""))
                         (map (partial partition 3 4))
                         (map (partial map second))
                         drop-last
                         (apply mapv vector)
                         (map reverse)
                         (map (partial remove (comp string/blank? string/trim))))
        ]
    stacks))

(defn- get-movements [input]
  (let [movement-lines (-> input
                           (string/split #"\n\n")
                           second
                           string/trim
                           string/split-lines)
        movements      (->> movement-lines
                            (remove empty?)
                            (map #(string/split % #"\s"))
                            (map (partial filter (partial re-find #"\d")))
                            (map (partial map #(Integer/parseInt %)))
                            (map vec)
                            vec)]
    movements))

(defn- rearrange-cratemover-9000 [current-stack [how-many from to]]
  (let [current-pile   (nth current-stack (- from 1))
        blocks-to-move (->> current-pile
                            reverse
                            (take how-many))
        updated-pile   (->> current-pile
                            (take (- (count current-pile) how-many))
                            vec)
        new-stack      (if (empty? blocks-to-move)
                         current-stack
                         (loop [idx           0
                                current-stack current-stack
                                new-stack     []]
                           (if (empty? current-stack)
                             new-stack
                             (let [pile (cond-> current-stack
                                                true first
                                                (= idx (- to 1)) (concat blocks-to-move)
                                                (= idx (- from 1)) ((constantly updated-pile))
                                                true vec)]
                               (recur (inc idx)
                                      (rest current-stack)
                                      (conj (vec new-stack) pile))))))]
    new-stack))

(defn- rearrange-cratemover-9001 [current-stack [how-many from to]]
  (let [current-pile   (nth current-stack (- from 1))
        blocks-to-move (->> current-pile
                            (take-last how-many))
        updated-pile   (->> current-pile
                            (take (- (count current-pile) how-many))
                            vec)
        new-stack      (if (empty? blocks-to-move)
                         current-stack
                         (loop [idx           0
                                current-stack current-stack
                                new-stack     []]
                           (if (empty? current-stack)
                             new-stack
                             (let [pile (cond-> current-stack
                                                true first
                                                (= idx (- to 1)) (concat blocks-to-move)
                                                (= idx (- from 1)) ((constantly updated-pile))
                                                true vec)]
                               (recur (inc idx)
                                      (rest current-stack)
                                      (conj (vec new-stack) pile))))))]
    new-stack))

(defn get-crates-on-top-cratemover-9000 [input]
  (let [stack     (get-stacks input)
        movements (get-movements input)]
    (->> movements
         (reduce rearrange-cratemover-9000 stack)
         (map last)
         (apply str))))

(defn get-crates-on-top-cratemover-9001 [input]
  (let [stack     (get-stacks input)
        movements (get-movements input)]
    (->> movements
         (reduce rearrange-cratemover-9001 stack)
         (map last)
         (apply str))))

(def execute (utils/execute-fn {:first {:input-path "resources/inputs/day5.txt"
                                        :fn         get-crates-on-top-cratemover-9000}
                                :second {:input-path "resources/inputs/day5.txt"
                                         :fn         get-crates-on-top-cratemover-9001}}))

(comment
  (execute :first)
  (execute :second)
  )