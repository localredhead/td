(ns td.handler
  (:use td.routes.auth
        td.routes.home
        org.httpkit.server
        compojure.core)
  (:require [noir.util.middleware :as middleware]
            [noir.session :as session]
            [compojure.route :as route]
            [td.models.schema :as schema]
            [org.httpkit.server :as httpkit]))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn init
  "runs when the application starts and checks if the database
   schema exists, calls schema/create-tables if not."
  []
  (if-not (schema/initialized?)
    (schema/create-tables)))

(defn destroy [] (println "shutting down..."))

;;append your application routes to the all-routes vector
(def all-routes [auth-routes home-routes app-routes])
(def app (middleware/app-handler all-routes))
(def war-handler (middleware/war-handler app))

(defn -main []
  (httpkit/run-server war-handler {:port 8080}))
