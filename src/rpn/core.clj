(ns rpn.core
  (:require [clojure.string :as str]))

(defn- store-number [n tree]
  (cons (read-string n) tree))

(defn calculate [operation]
  (let [tokens (str/split (str/trim operation) #"\s+")]
    (str/join
    " "
    (reduce store-number tokens))))
