(ns aoc2020.day1
  (:require [clojure.math.combinatorics :as combo]))

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

(defn solution [input v1 v2] (:product (into {} (results input v1 v2))))
