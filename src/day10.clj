(ns day10
    (:require
   [utils :as u]
   [hashp.core]
   [clojure.string :as str]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.set :as set]
   [clojure.math :as m]
      
   ))

;; (u/load-data 2023 10)

(def data (slurp "data/day10.dat"))
(def data  [197 97 204 108 1 29 5 71 0 50 2 255 248 78 254 63])
(def sample [3 4 1 5 ])

(defn data->part2data [data] (vec
                              (concat
                               (mapv int
                                    (apply str (interpose "," data)))[17 31 73  47  23])))

(data->part2data [1 2 3])
(data->part2data data)

(defn next-cycle [[l cur seqlen skip]]
  (if (= seqlen 0)
    [l (mod (+ cur skip) 256) seqlen (inc skip)]
  (let [stop (mod (+ cur seqlen) 256)
        newpart (if (> stop cur)
                  (subvec l cur (+ cur seqlen))
                  (concat (subvec l cur)
                          (subvec l 0 (- seqlen (count (subvec l cur))))))
        newpart (reverse newpart)
        newl (loop [si 0 di cur res l]
               (if (>= si (count newpart))
                 res
                 (recur (inc si) (inc di)
                        (assoc res (mod di 256)  (nth newpart si)))))]
    [newl (mod (+ cur seqlen skip) 256) seqlen (inc skip)])))

(defn run-cycle [[l cur skip data]]
  (loop [l l cur cur skip skip seqlens data]
    (if (empty? seqlens)
      [ l cur skip]
      (let [ [nl nc d_ nskip] (next-cycle [l cur (first seqlens) skip])]
        (recur nl nc nskip (rest seqlens))))))


(def p2data (data->part2data data))

(defn s->knot-input [s]
  (vec (concat (mapv int s) [17 31 73 47 23])
  ))

(s->knot-input "AOC")
(defn knot-hash [s]
  (let [data (s->knot-input s)
        z (loop [i 0 l (vec (range 256)) cur 0 skip 0 data data]
            (if (= i 64)
              l
              (let [ [nl nc nskip] (run-cycle [l cur skip data]) ]
                (recur (inc i) nl  nc nskip data))))
        ]
    (str/join "" (map #(format "%02x" %) (map (partial apply bit-xor) (partition 16  z))))))

(knot-hash "AoC 2017")

;; 3efbe78a8d82f29979031a4aa0b16a9d.

