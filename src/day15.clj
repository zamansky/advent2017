(ns day15
  (:require
   [utils :as u]
   [hashp.core]
   [clojure.string :as str]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.set :as set]
   [clojure.math :as m]
   ))


;; 65535
;; seeds
;; A 883
;; B 879

;; Sample seeds
;; A 65
;; B 8921

;; Factors
;; A 16807
;; B 48271
(defn agen [seed]
 (mod (*  seed 16807) 2147483647))

(defn bgen [seed]
 (mod (*  seed 48271) 2147483647))

(take 5(iterate agen 65))
(agen 6 5)

(take 5 (iterate bgen 8921))

(map (fn [a b] [(bit-and a 65535) (bit-and b 65535)])
     (take 40000000 (iterate agen 883))
     (take 40000000(iterate bgen 879)))


(count (filter (fn [[a b]]
          (= a b))
        (map (fn [a b] [(bit-and a 65535) (bit-and b 65535)])
             (take 5000000 (filter #(= 0 (mod % 4)) (iterate agen 883)))
             (take 5000000 (filter #(= 0 (mod % 8)) (iterate bgen 879)))))
 )


  
  (take- odd? [1 2 3 4 5] )
