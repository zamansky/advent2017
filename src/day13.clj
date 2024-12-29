(ns day13
    (:require
   [utils :as u]
   [hashp.core]
   [clojure.string :as str]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.set :as set]
   [clojure.math :as m]
   ))

;; (u/load-data 2023 13)

(def data (slurp "src/day13.dat"))
(def sample (slurp "src/sample13.dat"))
sample

(def s(->> (str/split-lines sample)
     (map #(re-seq #"\d+" %))
     (map #(map edn/read-string %)))
  )
(def d(->> (str/split-lines data)
     (map #(re-seq #"\d+" %))
     (map #(map edn/read-string %)))
  )



  
(apply +(map (partial apply *)(filter (fn [ [a b] ]
          (= 0 (mod a (* 2 (dec b)) ) )) (rest d))))


;; 3878062

(defn hit [depth time]
  (let [h (mod time (* 2 (dec depth)))

        ]
    (= h 0)))

(hit 4 6)


(defn part2 []
(loop [i 0]
  (if (every? (fn [[pos depth]] (not (hit depth (+ pos i))))  d)
    i
    (recur (inc i))))
    )

(part2)
