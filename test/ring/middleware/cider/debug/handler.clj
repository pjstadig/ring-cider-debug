(ns ring.middleware.cider.debug.handler
  (:require
   [clojure.string :as str]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.cider.debug :refer [wrap-cider-debug]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn hello-world []
  (let [hello "Hello"
        world "World"]
    (str/join " " [hello world])))

(defroutes app-routes
  (GET "/" [] (hello-world))
  (route/not-found "Not Found"))

(def app
  (wrap-cider-debug (wrap-defaults app-routes site-defaults)))

(defonce server (atom nil))

(defn start
  []
  (if (nil? @server)
    (reset! server (run-jetty app {:port 3000 :join? false}))
    (println "Already running"))
  @server)

(defn stop
  []
  (if (nil? @server)
    (println "Already stopped")
    (do (.stop @server)
        (.join @server)
        (reset! server nil)))
  nil)
