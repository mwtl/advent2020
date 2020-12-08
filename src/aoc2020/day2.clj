(ns aoc2020.day2
  (:require [clojure.string :as str]))

(defn parser [input] (
  let [splitted (str/split input #" ")
       n (str/split (get splitted 0) #"-")
       min-v (get n 0)
       max-v (get n 1)
       c (get (get splitted 1) 0)
       v (get splitted 2)]
       {:min (read-string min-v)
        :max (read-string max-v)
        :character c
        :value v
       }))

(defn part1-valid? [input]
  (let [occurences (count (re-seq (re-pattern (str (:character input))) (:value input)))]
    (and (<= occurences (:max input))
    (>= occurences (:min input)))))

(defn part2-valid? [input]
  (let [pos1-valid? (= (:character input) (get (:value input) (- (:min input) 1)))
        pos2-valid? (= (:character input) (get (:value input) (- (:max input) 1)))
        valid-count (count (filter #(= true %1) (list pos1-valid? pos2-valid?)))]
        (= 1 valid-count)))

(defn result-map [input] (
  let [parsed (parser input)]
       (assoc parsed
         :part1-valid (part1-valid? parsed)
         :part2-valid (part2-valid? parsed))))

(defn p1-solution [input]
  (let [items (map #(result-map %1) input)]
  (count (filter #(= true (:part1-valid %)) items))))

(defn p2-solution [input]
  (let [items (map #(result-map %1) input)]
  (count (filter #(= true (:part2-valid %)) items))))
