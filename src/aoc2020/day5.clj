(ns aoc2020.day5
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

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

(def colls (take 8 (range)))
(def rows (take 128 (range)))

(defn seat-id [row coll]
  (+ (* row 8) coll))

(defn parse-pass [boardingpass]
  (let [row (reducer 0 127 (nth (split-at 7 boardingpass) 0))
        coll (reducer 0 7 (nth (split-at 7 boardingpass) 1))]
        (hash-map :row row :column coll :seat-id (seat-id row coll) )))

(def passes
  (map #(parse-pass %) input))

(defn p1-solution [data]
  (apply max (map #(get % :seat-id) (map #(parse-pass %) data))))

(def all-seats
  (let [positions (for [x rows, y colls] (list x y))
        seats (map #(hash-map :row (nth % 0) :column (nth % 1) :seat-id (seat-id (nth % 0) (nth % 1))) positions)]
    (remove #(or (= (:row %) (apply min rows)) (= (:row %) (apply max rows))) seats)))

(defn pass-exists? [row coll data]
  (not (empty? (filter #(and (= (% :column) coll) (= (% :row) row)) data))))

(def empty-seats
  (remove #(pass-exists? (:row %) (:column %) passes) all-seats))

(def p2-solution (let [empty-seat-ids (map #(:seat-id %) empty-seats)
                        prv (butlast (conj empty-seat-ids (last empty-seat-ids)))
                        nxt (next empty-seat-ids)]
(remove nil? (map #(if (or (= (+ %1 1) %2) (= (- %1 1) %3)) nil %1) empty-seat-ids nxt prv))))
