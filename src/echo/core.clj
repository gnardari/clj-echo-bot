(ns echo.core
  (:gen-class)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer :all]
            [ring.util.response :refer [response]]
            [facebook.api :as fb]))

(defroutes bot-routes
  (POST "/webhook" request
        (fb/route-request request)
            {:status 200})
   (GET "/webhook" request
        (fb/webhook-is-valid? request))
(route/not-found "<h1>Could not solve request</h1>"))


(def bot
  (-> (wrap-defaults bot-routes api-defaults)
      (middleware/wrap-json-body {:keywords? true})
       middleware/wrap-json-response))
