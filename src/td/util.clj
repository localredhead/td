(ns td.util
  (:require [noir.io :as io]
            [markdown.core :as md]))

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


(def my-machjine 
     (vmm/instance my-server "my-vmfest-vm" 
        {:session-type "headless"}
        {:uuid "/Users/localredhead/VirtualBox VMs/Darchness/Darchness-disk1.vmdk"}  
        {:memory-size 1024
         :cpu-count 1
         :network [{:attachment-type :host-only
                    :host-only-interface "vboxnet0"}
                   {:attachment-type :nat}]
         :storage [{:name "IDE Controller"
                    :bus :ide
                    :devices [nil nil {:device-type :dvd} nil]}]
         :boot-mount-point ["IDE Controller" 0]}))
