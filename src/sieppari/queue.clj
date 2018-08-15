(ns sieppari.queue
  (:require [sieppari.interceptor :as i])
  (:import (clojure.lang PersistentQueue)))

(defprotocol IntoQueue
  (into-queue [t]))

(extend-protocol IntoQueue
  PersistentQueue
  (into-queue [t]
    t)

  Object
  (into-queue [t]
    (into PersistentQueue/EMPTY
          (keep i/into-interceptor)
          t)))