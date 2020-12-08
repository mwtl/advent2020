(ns day1.core
  (:require [clojure.math.combinatorics :as combo]))

(def test-data '(1721
979
366
299
675
1456))

(def assignment-data (map #(read-string %1) (clojure.string/split-lines (slurp "resources/numbers.txt"))))

(defn combo-data [numbers]
 (let [s (apply + numbers)
       p (apply * numbers)]
   {:sum s
    :product p}))

(defn combo-data-map [data times]
  (map #(combo-data %1) (combo/combinations data times)))

(defn distinct-by [f coll]
  (let [groups (group-by f coll)]
    (map #(first (groups %)) (distinct (map f coll)))))

(defn results [data checksum times]
  (let [r (distinct (filter #(= checksum (:sum %1)) (combo-data-map data times)))]
    (distinct-by :sum r)))

(def part1 (:product (into {} (results assignment-data 2020 2))))

(def part2 (:product (into {} (results assignment-data 2020 3))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
