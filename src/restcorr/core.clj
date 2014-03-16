(ns restcorr.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes ANY]]
            [restcorr [db :as model]]))

(defresource author-list-resource
  :available-media-types ["application/json"]
  :handle-ok (fn [ctx] (model/get-authors))
  :handle-not-acceptable "Uh, Oh, I cannot speak those languages!")

(defresource author-resource [id]
  :available-media-types ["application/json"]
  :handle-ok (fn [ctx] (model/get-author id))
  :handle-not-acceptable "Uh, Oh, I cannot speak those languages!")


(defroutes app
  (ANY "/foo" [] (resource :available-media-types ["text/html"]
                           :handle-ok (fn [ctx]
                                        (format "<html>It's %d milliseconds since the beginning of the epoch."
                                                (System/currentTimeMillis)))))
  (ANY "/bar" [] (resource :available-media-types ["application/json"]
                           :handle-ok (fn [ctx]
                                        {:id "1234"
                                         :name "Matt"})
                           :handle-not-acceptable "Uh, Oh, I cannot speak those languages!"))
  (ANY "/authors" [] author-list-resource)
  (ANY ["/authors/:id", :id #"[0-9]+"] [id] (author-resource (read-string id))))

(def handler
  (-> app
      (wrap-params)))
