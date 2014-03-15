(ns core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes ANY]]))

(defroutes app
  (ANY "/" [] (resource)))

(def handler
  (-> app
      (wrap-params)))

(run-jetty #'handler {:port 3000})
