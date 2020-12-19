(ns aoc2020.day7
  (:require [clojure.string :as str]))

(defn cleanup [data]
  (let [contains-bags? (nil? (re-find #"no other bags" data))
        no-dots (apply str (drop-last data))]
        (if contains-bags? no-dots nil)))

(defn parse-bag [data]
  (let [split-vals (re-seq #"[^0-9]+|[0-9]" data)
        bag-key (keyword (str/replace (subs (second split-vals) 1) #" " "_"))
        bag-data (hash-map :bag-count (read-string (first split-vals)))]
        (hash-map bag-key bag-data)))

(defn parse [data]
  (let [data (str/replace data #"bags" "bag")
        split-vals (str/split data #" contain ")
        bag-key (keyword (str/replace (first split-vals) #" " "_"))
        bags (str/split (second split-vals) #", ")
        bags-map (into {} (map #(parse-bag %) bags))]
        (hash-map bag-key bags-map)))

(def input-test
  (let [data (clojure.string/split-lines (slurp "resources/day7-test.txt"))
        cleaned-data (remove nil? (map #(cleanup %) data))]
        (map #(parse %) cleaned-data)))

(def input
  (let [data (clojure.string/split-lines (slurp "resources/day7.txt"))
        cleaned-data (remove nil? (map #(cleanup %) data))]
        (map #(parse %) cleaned-data)))

(defn bag-has-bag? [m k]
  "Returns the bag key if the bag contains a given a bag"
  (let [mk (first (keys m))
        has-key (some true? (map #(contains? % k) (vals m)))]
        (if has-key mk nil)))

(defn find-bags
  ([m ks]
    (let [found-bags (into [] (remove nil? (map #(bag-has-bag? % ks) m)))]
      (if (empty? found-bags) [] (find-bags m found-bags []))))
  ([m ks checked]
    (let [next-bag (first ks)
          checked-bags (conj checked next-bag)
          found-bags (into [] (remove nil? (map #(bag-has-bag? % next-bag) m)))
          remaining (into [] (flatten (conj (into [] (next ks)) found-bags)))]
      (if (empty? remaining) (into [] (distinct checked-bags)) (find-bags m remaining checked-bags)))))

(defn p1-solution [data k]
  (count (find-bags data k)))
