(ns facebook.api
  (:gen-class)
  (:require
      [org.httpkit.client :as http]
      [clojure.pprint :as pprint]
      [clojure.data.json :as json]))

(defn webhook-is-valid? [request]
        (def params (:params request))
        (if (= true (= (params "hub.mode") "subscribe")
            (= (params "hub.verify_token") (System/getenv "VALIDATION_TOKEN")))
          {:status 200 :body (params "hub.challenge")}
          {:status 403}
        ))

(defn sendAPI [messageData]
    (try
        (def response (http/request {:url "https://graph.facebook.com/v2.6/me/messages"
                       :query-params {"access_token" (System/getenv "PAGE_ACCESS_TOKEN")}
                       :headers {"Content-Type" "application/json"}
                       :method :post
                       :body (json/write-str messageData)
                       :insecure? true
                       }))
        (println @response)
    (catch Exception e (str "caught exception: " (.getMessage e)))))

(defn sendTextMessage [[recipientId messageText]]
    (def messageData {:recipient {:id recipientId} :message {:text messageText}})
    (println messageData)
    (sendAPI messageData))

(defn onMessage [event]
  (def senderID (get-in event [:sender :id]))
  (def recipientID (get-in event [:recipient :id]))
  (def timeOfMessage (get-in event [:timestamp]))
  (def message (get-in event [:message]))
  (println (str "Received message for user " senderID " and page "
              recipientID " at " timeOfMessage " with message:"))
  (println message)
  (sendTextMessage [senderID (:text message)]))

(defn route-request [request]
  (def data (get-in request [:body]))
  (if (= (:object data) "page")
    (doseq [pageEntry (:entry data)]
        (doseq [messagingEvent (:messaging pageEntry)]
            (cond
                (contains? messagingEvent :message) (onMessage messagingEvent)
                :else (println (str "Webhook received unknown messagingEvent: " messagingEvent)))))))
