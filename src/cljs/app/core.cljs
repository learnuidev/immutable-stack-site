(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            ;; aws
            ["/aws/amplify" :as amplify]
            [app.config-ex :as config]
            [app.auth]
            ;; Layout
            [app.layout :refer [app]]
            ;; router
            [app.routes :refer [router-start!]]))
            ; [app.view :refer [main-view]]))

;; configure aws
(comment
 (amplify/configure (clj->js config/env)))

;; whats inside env
(comment
  (identity config/env))

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [app] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (router-start!)
  (render))
