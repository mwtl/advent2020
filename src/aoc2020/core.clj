(ns aoc2020.core
  (:require [aoc2020.day1 :as d1]
            [aoc2020.day2 :as d2]
            [aoc2020.day3 :as d3]
            [aoc2020.day4 :as d4]
            [aoc2020.day5 :as d5]
            [aoc2020.day6 :as d6]
            [aoc2020.day7 :as d7]
            [clojure.math.combinatorics :as combo]))

;(def d1-solution-part1 (d1/solution d1/input 2020 2))
;(def d1-solution-part2 (d1/solution d1/input 2020 3))

;(def d2-solution-part1 (d2/p1-solution d2/input))
;(def d2-solution-part2 (d2/p2-solution d2/input))

;(def d3-solution-part1 (d3/solution d3/input d3/p1-paths))
;(def d3-solution-part2 (d3/solution d3/input d3/p2-paths))

;(def d4-solution-part1 (d4/p1-solution d4/input))
;(def d4-solution-part2 (d4/p2-solution d4/input))

;(def d5-solution-part1 (d5/p1-solution d5/input))
;(def d5-solution-part2 d5/p2-solution)

;(def d6-solution-part1 d6/p1-solution)
;(def d6-solution-part2 (d6/p2-solution d6/input))

(def d7-solution-part1 (d7/p1-solution d7/input :shiny_gold_bag))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
