(ns aoc2020.day4
  (:require [clojure.string :as str]
            [malli.core :as m]))

(def input-test (let [res (slurp "resources/day4-test.txt")]
  (map #(clojure.string/replace % #"\n" " ") (str/split res #"\n\n"))))

(def input (let [res (slurp "resources/day4.txt")]
  (map #(clojure.string/replace % #"\n" " ") (str/split res #"\n\n"))))

(def required-fields '(:byr :iyr :eyr :hgt :hcl :ecl :pid))

(defn transform-hgt [v]
  (let [find-uv (re-find #"^\d{2,3}(cm|in)$" v)
        unit (nth find-uv 1)
        height (if (nil? find-uv) nil (read-string (clojure.string/replace v #"(cm|in)" "")))]
        (hash-map :unit unit :value height)))

(defn transform-int-values [v]
  (if (every? #(Character/isDigit %) v) (read-string v) v))

(defn create-kv [input]
  (let [values (str/split input #":")
        k (keyword (nth values 0))
        v (nth values 1)
        transformed-v (case k
          :hgt (transform-hgt v)
          (:byr :iyr :eyr) (transform-int-values v)
          v)]
  (hash-map k transformed-v)))

(defn create-passport [input]
  (let [passport-fields (map #(create-kv %) (str/split input #" "))]
  (into {} passport-fields)))

(defn passports [data]
  (map #(create-passport %) data))

(defn p1-valid? [passport]
  (every? #(contains? passport %) required-fields))

(def p2-valid? (m/validator
    [:map
       [:byr [:int {:min 1920, :max 2002}]]
       [:iyr [:int {:min 2010, :max 2020}]]
       [:eyr [:int {:min 2020, :max 2030}]]
       [:hcl [:re #"^\#+[\da-fA-F]{6}$"]]
       [:ecl [:enum "amb" "blu" "brn" "gry" "grn" "hzl" "oth"]]
       [:pid [:re #"^\d{9}$"]]
       [:hgt [:or [:map
                    [:unit [:enum "cm"]]
                    [:value [:int {:min 150 :max 193}]]]
                  [:map
                     [:unit [:enum "in"]]
                     [:value [:int {:min 59 :max 76}]]]]]]))

(defn p1-solution [data]
  (count (filter true? (map #(p1-valid? %) (passports data)))))

(defn p2-solution [data]
  (count (filter true? (map #(p2-valid? %) (passports data)))))
