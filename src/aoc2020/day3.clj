(ns aoc2020.day3
  (:require [clojure.string :as str]))

(def it (clojure.string/split-lines (slurp "resources/d3tmp.txt")))
(def input-test (clojure.string/split-lines (slurp "resources/day3-test.txt")))
(def input (clojure.string/split-lines (slurp "resources/day3.txt")))

(def p1-paths [{:x 3 :y 1}])
(def p2-paths [{:x 1 :y 1} {:x 3 :y 1} {:x 5 :y 1} {:x 7 :y 1} {:x 1 :y 2}])

(defn line->char [line] (clojure.string/split line #""))

(defn x-values [line line-number] (map #(hash-map :v %1 :x %2 :y line-number) (line->char line) (iterate inc 0)))

(defn index-map [data]
  (flatten (map #(x-values %1 %2) data (iterate inc 0))))

(defn tree? [x y data]
  (let [element  (filter #(and (= (:x %) x) (= (:y %) y)) (index-map data))
        value ((nth element 0) :v)]
        (boolean (re-find #"#" value))))

(defn offset-x [length x]
  (let [repeated (int (Math/floor (/ x length)))]
  (- x (* repeated length))))

(defn walk-forest [data path]
  (let [width (count (line->char (nth data 0)))
        height (count data)
        checks (map #(hash-map :check %1 :y %2 :x (offset-x width %3))
          (take (/ height (path :y)) (iterate (partial + (path :y)) 0))
          (iterate (partial + (path :y)) 0)
          (iterate (partial + (path :x)) 0))]
        (map #(hash-map :check (%1 :check) :x (%1 :x) :y (%1 :y) :tree (tree? (%1 :x) (%1 :y) data)) checks)))

(defn trees-encountered [data path]
  (let [result (walk-forest data path)]
    (count (filter #(%1 :tree) result))))

(defn solution [data paths]
  (let [walks->trees (map #(trees-encountered data %) paths)]
        (reduce * walks->trees)))
