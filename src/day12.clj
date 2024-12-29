(ns day12
  (:require
   [utils :as u]
   [hashp.core]
   [clojure.string :as str]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.set :as set]
   [clojure.math :as m]
   ))

;; (u/load-data 2023 12)
 

(def data (slurp "src/day12.dat"))
(def sample (slurp "src/sample12.dat"))

(defn data->grid [data]
  (reduce (fn [graph [node & neighbors]]
            (update graph node concat neighbors)
            ) {} data))

(def graph (data->grid (->> (str/split-lines data)
                            (map #(re-seq #"\d+" %))
                            (map #(map edn/read-string %))
                            )))
(def sgraph (data->grid (->> (str/split-lines sample)
                            (map #(re-seq #"\d+" %))
                            (map #(map edn/read-string %))
                            )))


(defn bfs [graph start]
  (loop [solset #{} q (list start)]
    (if (empty? q)
      solset
      (let [current (first q)
            nq (rest q)
            solset (conj solset current)
            neighbors (graph current)
            neighbors (filter #(not (solset %)) neighbors)
            nq (concat nq neighbors)
            ]
          (recur solset nq)))))

(defn find-groups [graph]
  (let [nodes (set (keys graph))
        ]
    (loop [groups 0 nodes nodes]
      (if (empty? nodes)
        groups
        (let [g (bfs graph (first nodes))
              nodes (set/difference nodes g)
              ]
          (recur (inc groups) nodes))))))

(find-groups sgraph)
(find-groups graph)
