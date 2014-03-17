(ns restcorr.core-test
  (:require [clojure.test :refer :all]
            [restcorr.core :refer :all]
            [ring.mock.request :refer :all]
            [cheshire.core :refer :all]))

(deftest get-author-test
  (is (= (handler (request :get "/authors/1212"))
         {:status 200
          :headers {"Vary" "Accept"
                    "Content-Type" "application/json;charset=UTF-8"}
          :body "{\"first_name\":\"John\",\"last_name\":\"Worsley\",\"id\":1212}"})))

(deftest get-authors-test
  (let [response (handler (request :get "/authors"))
        body (parse-string (:body response) true)]
    (is (= (:status response) 200))
    (is (= (count body) 19))))
