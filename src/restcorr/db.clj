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

(defn get-authors []
  (select authors))

(defn get-author [id]
  (first (select authors
                 (where {:id id}))))

(defn delete-author [id]
  (delete authors
          (where {:id id})))

(defn add-author [author]
  (insert authors
          (values author)))
