(ns day09
  (:require
   [utils :as u]
   [hashp.core]
   [clojure.edn :as edn]
   [clojure.string :as str]
   [clojure.set :as set]))

(def data (slurp "data/day09.dat"))

(defn remove-bangs [line]
  (str/replace  line #"!." ""))

(defn remove-garbage [line]
  (str/replace  (str/replace line #"(<.*?>)" "")
                #"," ""))
     
  (def sample
    ["{}"
     "{{{}}}"
     "{{},{}}"
     "{{{},{},{{}}}}"
     "{<a>,<a>,<a>,<a>}"
     "{{<ab>},{<ab>},{<ab>},{<ab>}}"
     "{{<!!>},{<!!>},{<!!>},{<!!>}}"
     "{{<a!>},{<a!>},{<a!>},{<ab>}}"])

;; (score [[[[]]]] 1)
(def s (map
        #(-> %
             remove-bangs
             remove-garbage

             (str/replace  #"\{" "[")
             (str/replace  #"\}" "]")
             read-string)
        sample))

(defn score
  "Simple recursive search to calc the score"
  [item level]
  (cond
    (empty? item) level
    :else (+ level
             (apply + (map #(score % (inc level)) item)))
    ))

;; remove all the garbage and convert
;; the data into embedded lists
(def d (->
 data
 remove-bangs
 remove-garbage
 (str/replace #"\{" "[" )
 (str/replace  #"\}" "]")
 read-string
 )) 
;; score it
(score d 1)

;; for part 2 we need to pull out the garbage
(defn keep-garbage [line]
  (re-seq  #"<.*?>" line))
  
;;(map keep-garbage (map remove-bangs sample))

;; remove the bangs then pull out the garbage
(def d (keep-garbage (remove-bangs data)))

;; we want to subtract the < and > for each garbage from
;; the char counts
(def brackets (* 2 (count d)))

;; calculate the # of characters in the garbage and subtract
;; out the brackets 
(-  (reduce + (map count d)) brackets)
 
