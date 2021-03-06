(defproject echo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [ [org.clojure/clojure "1.8.0"]
                  [org.clojure/data.json "0.2.6"]
                  [compojure "1.5.1"]
                  [ring/ring-json "0.4.0"]
                  [ring/ring-defaults "0.1.5"]
                  [http-kit "2.2.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler echo.core/bot :open-browser? false}
  :main ^:skip-aot echo.core
  :target-path "target/%s"
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                          [ring/ring-mock "0.3.0"]]}})
