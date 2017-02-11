(ns rpn.core
  (:require [clojure.string :as str]))

(defn- operation->tokens [operation]
  (str/split (str/trim operation) #"\s+"))

(defn- tokens->symbols [tokens]
  (map read-string tokens))

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
    (eval (list (resolve (first branch))
                (walk (nth branch 1))
                (walk (nth branch 2))))
    branch))

(defn- format [tree]
  (str/join " " tree))

(defn calculate [operation]
  (format (map walk (reduce store nil (tokens->symbols (operation->tokens operation))))))
