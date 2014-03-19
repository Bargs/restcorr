(ns restcorr.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.format-params :refer [wrap-restful-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes ANY]]
            [restcorr [db :as db]]))

(defresource author-list-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :handle-ok (fn [ctx] (db/get-authors))
  :handle-not-acceptable "Uh, Oh, I cannot speak those languages!"
  :post! (fn [ctx]
           (print (get-in ctx [:request :params]))))

(defresource author-resource [id]
  :available-media-types ["application/json"]
  :allowed-methods [:get :delete]
  :handle-ok (fn [ctx] (db/get-author id))
  :handle-not-acceptable "Uh, Oh, I cannot speak those languages!"
  :delete! (fn [_] (db/delete-author id)))

(defroutes app
  (ANY "/" [] (resource :available-media-types ["text/html"]
                           :handle-ok (fn [ctx] "Hello, Clojure")))
  (ANY "/authors" [] author-list-resource)
  (ANY ["/authors/:id", :id #"[0-9]+"] [id] (author-resource (read-string id))))

(def handler
  (-> app
      (wrap-restful-params :formats [:json-kw])))
