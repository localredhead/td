(ns td.util
  (:require [noir.io :as io]
            [vmfest.manager :as vmm]
            [markdown.core :as md])
  (:import (java.lang.Runtime)))

(defn format-time
  "formats the time using SimpleDateFormat, the default format is
   \"dd MMM, yyyy\" and a custom one can be passed in as the second argument"
  ([time] (format-time time "dd MMM, yyyy")) 
  ([time fmt]
    (.format (new java.text.SimpleDateFormat fmt) time)))

(defn md->html
  "reads a markdown file from public/md and returns an HTML string"
  [filename]
  (->>
    (io/slurp-resource filename)
    (md/md-to-html-string)))

(defn my-server [] (vmm/server "http://localhost:18083"))

(defn my-machine [server] 
     (vmm/instance server "my-vmfest-vm" 
        {:uuid "/Users/localredhead/VirtualBox VMs/Darchness/Darchness-disk1.vmdk"}  
        {:memory-size 512
         :cpu-count 1
         :network [{:attachment-type :host-only
                    :host-only-interface "vboxnet0"}
                   {:attachment-type :nat}]
         :storage [{:name "IDE Controller"
                    :bus :ide
                    :devices [nil nil {:device-type :dvd} nil]}]
         :boot-mount-point ["IDE Controller" 0]}))

(defn cmd [p] (.. Runtime getRuntime (exec (str p))))

(defn start-vboxweb [] (cmd "vboxwebsrv -t0"))

(defn start-vm [machine] 
  (vmm/start machine))
