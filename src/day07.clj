(ns day-2017-07
  (:require
   [utils :as u]
   [hashp.core]
   [clojure.edn :as edn]
   [clojure.string :as str]
   [clojure.set :as set]))




(def sample "pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)")



(defn parse [line]
  (let [ [_ name weight _ holds]
        (re-find #"(.+) \(([0-9]+)\)( -> )?(.*)" line)
        weight (read-string weight)
        holds (str/split holds #", ")
        holds (if (> (count  (get holds 0)) 0)
                holds
                nil)
        ]
    {:name name :weight weight :holds holds} 
    ))

(def data (mapv parse (str/split sample #"\n")))
(def data (mapv parse (str/split (slurp "data/day2017-07.dat") #"\n")))
(def names (map :name data))
(def ons (flatten (mapv :holds data)))

(set/difference (set names) (set ons))
data
(def graph (reduce (fn [acc next]
                     (assoc acc (:name next)
                            {:key (:name next)
                             :weight (:weight next)
                             :holds (:holds next)}
                            )
                            ) {} data))



(defn traverse [{:keys [key weight holds] :as node} graph]
  (if (nil? holds)
    weight
    ;; otherwise traverse
    (let [weights (map #(traverse (get graph %) graph) holds)
          f  (frequencies weights)]
      (if (= (count f) 1)
        (+ weight (reduce + weights))
        (do
          (println "Z" key weight f)
          [key weight f])))))

(traverse  ( get graph "eqgvf") graph)

(traverse  ( get graph "tknk") graph)
