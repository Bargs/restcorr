(ns restcorr.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes ANY]]))

(defroutes app
  (ANY "/foo" [] (resource :available-media-types ["text/html"]
                           :handle-ok (fn [ctx]
                                        (format "<html>It's %d milliseconds since the beginning of the epoch."
                                                (System/currentTimeMillis)))))
  (ANY "/bar" [] (resource :available-media-types ["application/json"]
                           :handle-ok (fn [ctx]
                                        {:id "1234"
                                         :name "Matt"})
                           :handle-not-acceptable "Uh, Oh, I cannot speak those languages!")))

(def handler
  (-> app
      (wrap-params)))

(run-jetty #'handler {:port 3000})
