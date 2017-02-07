(ns rpn.core-test
  (:use midje.sweet)
  (:use [rpn.core]))

(facts "about calculations in rpn"
       (fact "it konws how to calculate a number"
             (calculate "1") => 1
             (calculate "9") => 9))
