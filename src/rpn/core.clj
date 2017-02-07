(ns rpn.core
  (:require [clojure.string :as str]))

(defn calculate [operation]
  (let [tokens (str/split (str/trim operation) #"\s+")]
    (str/join
    " "
    (reduce
      #(cons (read-string %1) %2) tokens))))
