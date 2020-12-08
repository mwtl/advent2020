(ns aoc2020.core
  (:require [aoc2020.day1 :as d1]
            [aoc2020.day2 :as d2]
            [aoc2020.day2 :as d2]))

(def d1-solution-part1 (d1/solution d1/input 2020 2))
(def d1-solution-part2 (d1/solution d1/input 2020 3))

(def d2-solution-part1 (d2/p1-solution d2/input))
(def d2-solution-part2 (d2/p2-solution d2/input))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
