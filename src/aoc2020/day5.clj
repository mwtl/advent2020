(ns aoc2020.day5
  (:require [clojure.string :as str]))

(def input-test '("FBFBBFFRLR"))
(def input (clojure.string/split-lines (slurp "resources/day5.txt")))

(defn reducer [lower upper value]
  (let [next (rest value)
        up? (case (nth value 0)
                (\B \R) true
                false)
        next-n (int (Math/floor (/ (- upper lower) 2)))
        next-lower (if (true? up?) (- upper next-n) lower)
        next-upper (if (true? up?) upper (+ lower next-n))]
        (if (= next-upper next-lower) next-upper (reducer next-lower next-upper next))))

(defn parse-pass [boardingpass]
  (let [row (reducer 0 127 (nth (split-at 7 boardingpass) 0))
        column (reducer 0 7 (nth (split-at 7 boardingpass) 1))]
        (hash-map :row row :column column :seat-id (+ (* row 8) column))))

(defn p1-solution [data]
  (apply max (filter #(:seat-id %) (map #(parse-pass %) data))))
