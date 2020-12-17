(ns aoc2020.day6
  (:require [clojure.string :as str]
            [clojure.set :as cset]))


(def input-test (let [res (slurp "resources/day6-test.txt")]
  (str/split res #"\n\n")))

(def input (let [res (slurp "resources/day6.txt")]
  (str/split res #"\n\n")))

(def questions
  (let [group-strings (map #(str/replace % #"\n" "") input)]
    (map #(distinct %) group-strings)))

(def p1-solution
  (reduce + (map #(count %) questions)))


(defn question-groups [data]
  (map #(str/split-lines %) data))

(defn question-splits [question-group]
  (map (fn [qg]
    (map (fn [qr] (str/split qr #"")) qg)) question-group))

(defn count-anwsers
  ([remaining-anwsers]
    (let [current (distinct (flatten (take 1 remaining-anwsers)))
          remaining (next remaining-anwsers)]
          (if (nil? remaining) (count current) (count-anwsers remaining current))))
  ([remaining-anwsers prev-anwsers]
    (let [current (distinct (flatten (take 1 remaining-anwsers)))
          intersect (cset/intersection (set prev-anwsers) (set current))
          remaining (next remaining-anwsers)]
          (if (nil? remaining) (count intersect) (count-anwsers remaining intersect)))))

(defn p2-solution [data]
  (reduce + (map #(count-anwsers %) (question-splits (question-groups data)))))
