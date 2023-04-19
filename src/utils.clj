(ns utils)

(defn execute-fn [opts]
  (fn [part]
    (let [input (slurp (-> opts part :input-path))
          part-fn (-> opts part :fn)]
      (part-fn input))))