(ns modern-cljs.shopping
  (:require-macros [hiccups.core :as h])
  (:require [domina :refer [append! by-id by-class value set-value! destroy!]]
            [domina.events :refer [listen! prevent-default]]
            [hiccups.runtime :as hicupsrt]
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [cljs.reader :refer [read-string]]))

(defn calculate [evt]
  (let [quantity (read-string (value (by-id "quantity")))
        price (read-string (value (by-id "price")))
        tax (read-string (value (by-id "tax")))
        discount (read-string (value (by-id "discount")))]
    (remote-callback :calculate
                     [quantity price tax discount]
                     #(set-value! (by-id "total") (.toFixed % 2)))
    (prevent-default evt)))

(defn add-help! []
  (append! (by-id "shoppingForm")
               (h/html [:div.help "Click to calculate"])))

(defn remove-help! []
  (destroy! (by-class "help")))

(defn ^:export init []
  (when (and js/document
           (aget js/document "getElementById"))
    (listen! (by-id "calc") :click (fn [evt] (calculate evt)))
    (listen! (by-id "calc") :mouseover add-help!)
    (listen! (by-id "calc") :mouseout remove-help!)))
