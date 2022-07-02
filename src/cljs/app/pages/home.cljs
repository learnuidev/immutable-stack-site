(ns app.pages.home
  (:require [reagent.core :as r]
            [cljs.core.async.interop :refer-macros [<p!]]
            [cljs.core.async :refer [go]]
            ["/components/Link" :refer [Link]]))


(defn splash-page []
  [:div.my-16.mx-8.md:mx-16
   [:h1.uppercase.text-5xl.break-words.md:text-8xl.lg:8xl "Immutable" [:div [:strong " Stack"]]]
   [:div.my-8.sm:my-4
    [:h3.text-xl.font-light.md:text-2xl "Learn how to "
     [:span.font-medium "build, test, maintain "]
     [:span "and "]
     [:strong.font-medium "deploy "]
     [:span " highly scalable applications to "]
     [:strong "aws cloud "]
     [:span " using "]
     [:strong "the immutable stack"]]
    [:div.my-8
     [:> Link {:title "Read the Guide"
               :href "/book/01"
               :className "px-8"}]]]])


(defn landing-page []
  [:<>
   [splash-page]])




(defn home-page []
  [landing-page])
