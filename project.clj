(defproject
  td
  "0.1.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [lib-noir "0.4.9"]
   [compojure "1.1.5"]
   [ring-server "0.2.7"]
   [http-kit "2.0.0"]
   [clabango "0.5"]
   [com.taoensso/timbre "1.5.2"]
   [com.taoensso/tower "1.4.0"]
   [markdown-clj "0.9.19"]
   [org.clojure/java.jdbc "0.2.3"]
   [com.h2database/h2 "1.3.170"]
   [korma "0.3.0-RC5"]
   [com.novemberain/monger "1.5.0"]
   [log4j
    "1.2.15"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]]
  :ring
  {:handler td.handler/war-handler,
   :init td.handler/init,
   :destroy td.handler/destroy}
  :main td.handler/-main
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies [[ring-mock "0.1.3"] [ring/ring-devel "1.1.8"]]}}
  :url
  "http://example.com/FIXME"
  :plugins
  [[lein-ring "0.8.3"]]
  :description
  "FIXME: write description"
  :min-lein-version "2.0.0")
