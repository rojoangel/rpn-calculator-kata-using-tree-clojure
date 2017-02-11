(ns rpn.core
  (:require [clojure.string :as str]))

(defn- store-number [tree n]
  (reverse (cons n (reverse tree))))

(defn- store-operation [tree op]
  (reverse
    (cons
      (cons op (take-last 2 tree))
      (reverse
        (drop-last 2 tree)))))

(defn- store [tree symbol]
  (if (number? symbol)
    (store-number tree symbol)
    (store-operation tree symbol)))

(defn- walk [branch]
  (if (seq? branch)
    (eval (list (resolve (first branch)) (nth branch 1) (nth branch 2)))
    branch))

(defn- format [tree]
  (str/join " " tree))

(defn calculate [operation]
  (let [tokens (str/split (str/trim operation) #"\s+")
        symbols (map read-string tokens)]
    (format (map walk (reduce store nil symbols)))))
