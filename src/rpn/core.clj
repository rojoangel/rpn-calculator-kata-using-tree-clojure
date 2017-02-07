(ns rpn.core
  (:require [clojure.string :as str]))

(defn- store-number [tree n]
  (reverse (cons n (reverse tree))))

(defn- store [tree symbol ]
  (if (number? symbol)
    (store-number tree symbol)))

(defn calculate [operation]
  (let [tokens (str/split (str/trim operation) #"\s+")
        symbols (map read-string tokens)]
    (str/join
    " "
    (reduce store nil symbols))))
