(ns app.components.navbar)

(defn navbar []
  [:nav.m-8.flex.justify-between.items-stretch
   [:p.font-600 [:a {:href "/"} "immutable stack"]]
   [:div.flex.grid-gap-2
    [:p.mx-4 [:a {:href "/principles"} "principles"]]
    [:p.mx-4 [:a {:href "/mental-model"} "mental models"]]
    [:p.mx-4 [:a {:href "/book"} "guide"]]
    [:p.mx-4 [:a {:href "/login"} "login"]]]])
