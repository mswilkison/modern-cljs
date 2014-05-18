(ns modern-cljs.templates.shopping
  (:require [net.cgrand.enlive-html :refer [deftemplate content do-> add-class set-attr attr=]]
            [modern-cljs.remotes :refer [calculate]]
            [modern-cljs.shopping.validators :refer [validate-shopping-form]]))

(defmacro maybe-error [expr]
  `(if-let [x# ~expr]
     (do-> (add-class "error")
           (content x#))
     identity))

(deftemplate update-shopping-form "public/shopping.html"
  [q p t d e]

  [[:label (attr= :for "quantity")]] (maybe-error (first (:quantity e)))
  [[:label (attr= :for "price")]] (maybe-error (first (:price e)))
  [[:label (attr= :for "tax")]] (maybe-error (first (:tax e)))
  [[:label (attr= :for "discount")]] (maybe-error (first (:discount e)))
  
  [:#quantity] (set-attr :value q)
  [:#price] (set-attr :value p)
  [:#tax] (set-attr :value t)
  [:#discount] (set-attr :value d)
  [:#total] (if e
              (set-attr :value "0.00")
              (set-attr :value
                         (format "%.2f" (double (calculate q p t d))))))

(defn shopping [q p t d]
  (update-shopping-form q p t d (validate-shopping-form q p t d)))
