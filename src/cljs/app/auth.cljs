(ns ^{:doc "Auth Module"
      :author "Vishal Gautam"}
  app.auth
  (:require [reagent.core :as r]
            ["/aws/auth" :as auth]))


(defonce auth-state (r/atom nil))

;; whats methods does Auth expose
;; Auth
;; 1. Sign Up
(comment)
(defn sign-up! [{:keys [username password email]}]
  (-> (auth/signUp (clj->js {:username username
                             :password password
                             :email email}))
      (.then #(js/console.log %))))
(comment
  (sign-up! {:username "vishal.gautam"
             :password "Password1-"
             :email "learnuidev@gmail.com"}))


;; 2. Resend Code
(comment)
(defn resend-code [username]
  (-> (auth/resendCode username)
      (.then #(js/console.log %))))

(comment
  (resend-code "vishal.gautam"))

;; 3. Confirm Signup
(defn confirm-signup! [{:keys [username code]}]
  (-> (auth/confirmSignUp (clj->js {:username username
                                    :code (str code)}))
      (.then #(js/console.log %))))


(comment
 (confirm-signup! {:username "vishal.gautam"
                   :code "296605"}))

;; 4. Sign In
(comment)
(defn sign-in! [{:keys [username password]}]
  (-> (auth/signIn (clj->js {:username username :password password}))
      (.then #(reset! auth-state (js->clj % :keywordize-keys true)))))

(comment
  (:jwtToken @auth-state)
  (sign-in! {:username "vishal.gautam"
             :password "Password1-"}))

;;
(defonce root-url "https://oqdnm8f3y6.execute-api.us-east-1.amazonaws.com/api/v1")



(comment
  @auth-state
  (js/fetch (str root-url "/authed/me"))
  (js/fetch "/authed/students")
  (-> (js/fetch (str root-url "/authed/students")
                (clj->js {:headers {:Access-Control-Allow-Origin "*"
                                    :Authorization (:jwtToken @auth-state)}}))
      (.then #(js/console.log %))))
