(ns modern-cljs.core
  (:use compojure.core)
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [resources not-found]]
            [modern-cljs.login :refer [authenticate-user]]))

; defroutes macro defines a function that chains individual route
; functions together. The request map is passed to each function in
; turn, until a non-nil response is returned.
(defroutes app-routes
  ; to serve document root addresss
  (GET "/" [] "<p>Hello from compojure</p>")
  (POST "/login" [email password] (authenticate-user email password))
  (POST "/shopping" [quantity price tax discount]
        (str "You enter: "quantity " " tax " and " discount "."))
  ; to serve static pages saved in resources/public director
  (resources "/")
  ; if page not found
  (not-found "Page not found"))

; site function creates a handler suitable for a standard website,
; adding a bunch of standard ring middleware to app-route:
(def handler
  (site app-routes))
