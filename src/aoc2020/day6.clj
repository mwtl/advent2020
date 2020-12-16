(ns aoc2020.day6
  (:require [clojure.string :as str]))


(def input-test (let [res (slurp "resources/day6-test.txt")]
  (map #(str/replace % #"\n" "") (str/split res #"\n\n"))))

(def input (let [res (slurp "resources/day6.txt")]
  (map #(str/replace % #"\n" "") (str/split res #"\n\n"))))

(def p1-solution [data]
  (reduce + (map #(count (distinct %)) data)))
