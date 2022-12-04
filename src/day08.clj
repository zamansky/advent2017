(ns day08
  (:require
   [utils :as u]
   [hashp.core]
   [clojure.edn :as edn]
   [clojure.string :as str]
   [clojure.set :as set]))

(def sample
  "b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10")

(def registers {})

(defn parse [line]
  (let [[_ reg op amt reg2 comp amt2]
        (re-find #"([a-z]+) (inc|dec) (-?[0-9]+) if ([a-z]+) (.+) (-?[0-9]+)" line)
        amt (read-string amt)
        amt2 (read-string amt2)]
    [reg op amt reg2 comp amt2]))




(def comps {"<=" <=
            "<" <
            "==" =
            ">" >
            ">=" >=
            "!=" not=
            })
(def ops {"inc" + "dec" -})

(defn do-op [[reg op amt reg2 comp amt2] registers]
  (if ((get comps comp) (get registers reg2 0) amt2)
    (let [newval ((get ops op) (get registers reg 0) amt)]
      (do
        (reset! largest (max  @largest newval))
        (assoc registers reg newval)))
    registers))

(do-op (parse "a inc 1 if b < 5") registers)

(def data (slurp "data/day08.dat"))
(def largest (atom 0))

(defn part1 [data]
 (->> (str/split data #"\n")
     (map parse)
     (reduce (fn [acc next] (do-op next acc)) registers  )
     vals
     (apply max)))

;; for part 2 we track the largest overall (so far) in the atom
