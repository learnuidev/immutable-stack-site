(ns app.layout
  (:require
   [app.routes :refer [routes-state]]
   ;; components
   [app.components.navbar :refer [navbar]]
   ["/components/Button" :refer [Button]]
   ["/react_query/index" :refer [QueryClientProvider]]
   ["/react_query/student/student.queries" :refer [useFetchStudents]]))

(comment
  @routes-state)

;;
(defn splash-page []
  (let [data (useFetchStudents)]
    [:div.m-8
     [:h1.uppercase.text-5xl "Learning is an adventure"]
     #_[:h1.uppercase.text-5xl "Where developers"
        [:span.block " become "]
        [:strong.block.font-strong "problem solvers"]]
     [:div.my-8
      [:h3.text-2xl.font-light "novus challenges and pushes curious learners through "
       [:strong.font-medium "real life "
        [:span.block " startup simulations and complex games"]]]
      [:> Button {:title "Get Started"
                  :className "px-8"}]]]))


(defn app []
  [:> QueryClientProvider
   [:div
    [navbar]
    (let [current-view (-> @routes-state :data :view)]
      [current-view @routes-state])]])
