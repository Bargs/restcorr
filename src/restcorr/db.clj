(ns restcorr.db
  (:require [korma.db :refer :all]
            [korma.core :refer :all]))

(def postgres-db {:subprotocol "postgresql"
                  :subname "//192.168.40.2:5432/booktown"
                  :user "docker"
                  :password "docker"})

(defdb dev postgres-db)

(declare authors)

(defentity authors
  (entity-fields :last_name :first_name))

(select authors)

(select authors
        (fields [:first_name :first] [:last_name :last])
        (where {:first_name "Ariel"}))
