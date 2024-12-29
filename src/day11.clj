(ns day11
    (:require
   [utils :as u]
   [hashp.core]
   [clojure.string :as str]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.set :as set]
   [clojure.math :as m]
   ))

;; (u/load-data 2023 11)

(def data (slurp "src/day11.dat"))

(def d(map keyword (str/split data #"," )))

(def deltas {:n  [0 1 -1]
             :s [0 -1 1]
             :ne [1 0 -1]
             :sw [-1 0 1]
             :nw [-1 1 0]
             :se [1 -1 0]})

(defn move [ pt dir ]
  (mapv + pt (deltas dir)))


(-> (move [0 0] :ne)
    (move :ne)
    (move :sw)
    (move :sw)
     )

(defn follow-path [start path]
  (reduce (fn [loc next]
            (move loc next)) start path))

(defn part1 [path]
  (/ (->> (follow-path [0 0 0] path)
       (map abs)
       (apply +)
       ) 2))

(part1 d)



*(def pts (reduce (fn [locs next]
          (let [nextloc (move (last locs) next)]
            (conj locs nextloc))) [[0 0 0]] d)
   )

(defn distance [ pt ]
  (/ (->> (map abs pt) (apply +)) 2))

(apply max (map distance pts))
