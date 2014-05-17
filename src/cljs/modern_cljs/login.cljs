(ns modern-cljs.login
  (:require [domina :as dom]
            [domina.events :as ev]))

; define the function to be attached to form submission event
(defn validate-form []
  ; get email and password element from their ids in the HTML form
  (let [email (dom/by-id "email")
        password (dom/by-id "password")]
    (if (and (> (count (dom/value email)) 0)
             (> (count (dom/value password)) 0))
      true
      (do (js/alert "Please, complete the form!")
          false))))

; define the function to attached validate-form to onsubmit event of
; the form
(defn ^:export init []
  ; verify that js/document exists and that it has a getElementById
  ; property
  (if (and js/document
           (aget js/document "getElementById"))
    ; get loginForm by element id and set its onsubmit property to
    ; our validate-form function
    (let [login-form (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form) validate-form))))
