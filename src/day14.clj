(ns day14
    (:require
     [utils :as u]
     [hashp.core]
     [clojure.string :as str]
     [clojure.java.io :as io]
     [clojure.edn :as edn]
     [clojure.set :as set]
     [clojure.math :as m]
     [day10 :refer [knot-hash]]
   ))

;; (u/load-data 2023 14)

;; (def data (slurp "src/day14.dat"))
(def data "jzgqcdpd-")
(def data "flqrgnkx-")
(defn hex-to-binary [hex-string]
    (Integer/toBinaryString (Integer/parseInt hex-string 16)))


(defn count-squares [hash]
  ((->> (str/split hash #"")
       (map hex-to-binary)
       (map #(format "%4s" %))
       str/join
       frequencies
       
       )
  \1)
  )
(defn get-line [hash]
  (->> (str/split hash #"")
       (map hex-to-binary)
       (map #(format "%4s" %))
       str/join
       (#(str/replace % " " "0") )
       (#(str/split % #""))
       ))

(defn make-grid [hash]
  (vec (for [i (range 128)]
  (get-line(knot-hash (concat data (str i))))
  )))

(def grid (make-grid data))

(def deltas [ [0 1] [0 -1] [1 0] [-1 0]])


(defn get-neighbors [graph [r c] ch]
  (filter #(= (get-in graph %) "1") (map #(map + % [r c]) deltas)))

  


(defn bfs [graph start]
  (loop [solset #{} q (list start)]
    (if (empty? q)
      solset
      (let [current (first q)
            nq (rest q)
            solset (conj solset current)
            neighbors  (get-neighbors graph  current (get-in graph current))
            neighbors (filter #(not (solset %)) neighbors)
            neighbors (filter #(not ((set nq) %)) neighbors)
            nq (concat nq neighbors)
            ]
        (recur solset nq)))))

(bfs grid [0 1])

(defn get-groups [grid]
  (loop [r 0 c 0 groups 0 solset #{}]
      (cond
        (and (> r 127) (> c 127))
        groups

        (> c 127)
        (recur (inc r)  0 groups solset)

        (and (= (get-in grid [r c]) "1")
             (nil? (solset [r c])))
        (let [newnodes (bfs grid [ r  c])
              solset (conj solset [r c])]
          (recur r c  (inc groups) (set/union solset newnodes)))

        
        :else
        (recur r (inc c) groups solset))))


(get-groups grid)

