(ns app.pages.chapter
 (:require [reagent.core :as r]
           [markdown.core :as m]))
           ; ["react-markdown" :as ReactMarkdown]
           ; ["/components/Markdown" :refer [Markdown]]))


(def chapters
  {:01 {:01 "https://raw.githubusercontent.com/Novus-School/novus/master/notes/novus_school/courses/immutable_stack_book_v1/00_Introduction/0_introduction.md"}})


(defonce chapter-state (r/atom {}))
(comment
  @chapter-state)
(comment)
(defn fetch-chapter []
  (-> (js/fetch "https://raw.githubusercontent.com/Novus-School/novus/master/notes/novus_school/courses/immutable_stack_book_v1/00_Introduction/0_introduction.md")
      (.then (fn [res] (.text res)))
      (.then (fn [res] (swap! chapter-state assoc :current-chapter res)))))


(defn chapter-page []
  (let [_ (fetch-chapter)
        html (m/md->html  (:current-chapter @chapter-state))]
   [:div.m-8
    [:div {:dangerouslySetInnerHTML {:__html html}}]]))


(comment
  (:html (m/md-to-html-string* "# Hello world" {})))
