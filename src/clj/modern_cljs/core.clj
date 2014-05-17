(ns modern-cljs.core
  (:use compojure.core)
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [modern-cljs.login :refer [authenticate-user]]
            [modern-cljs.templates.shopping :refer [shopping]]
            [shoreleave.middleware.rpc :refer [wrap-rpc]]))

; defroutes macro defines a function that chains individual route
; functions together. The request map is passed to each function in
; turn, until a non-nil response is returned.
(defroutes app-routes
  ; to serve document root addresss
  (GET "/" [] "<p>Hello from compojure</p>")
  (POST "/login" [email password] (authenticate-user email password))
  (POST "/shopping" [quantity price tax discount]
        (shopping quantity price tax discount))
  ; to serve static pages saved in resources/public director
  (resources "/")
  ; if page not found
  (not-found "Page not found"))

; site function creates a handler suitable for a standard website,
; adding a bunch of standard ring middleware to app-route:
(def handler
  (site app-routes))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))
