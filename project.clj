(defproject restcorr "0.1.0-SNAPSHOT"
  :description "Experiments with Clojure and REST"
  :url "https://github.com/Bargs/restcorr"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [liberator "0.11.0"]
                 [compojure "1.1.6"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [korma "0.3.0-RC6"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql/postgresql "8.4-702.jdbc4"]]
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [cheshire "5.3.1"]]}}
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler restcorr.core/handler})
