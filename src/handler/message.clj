(ns handler.event)

(defn onMessage [event]
  (let [senderID (get-in event [:sender :id])
   recipientID (get-in event [:recipient :id])
   timeOfMessage (get-in event [:timestamp])
   message (get-in event [:message])]
  (println (str "Received message for user " senderID " and page "
              recipientID " at " timeOfMessage " with message:"))
  (println message)
  [senderID (:text message)]))
