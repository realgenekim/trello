(ns trello.core
  "The client namespace contains the raw HTTP functions for accessing
  and parsing the responses from the Trello API."
  (:require [clojure.string :as string]
            [clojure.core.typed :refer [ann cf check-ns U Any Nothing]]))

(ann ^:no-check consumer Any)
(def consumer (atom {}))

(ann *oauth-token* (U String nil))
(def ^:dynamic *oauth-token*)

(ann *oauth-token* (U String nil))
(def ^:dynamic *oauth-secret*)

(ann ^:no-check callfn [Any Any * -> Any])
(defn callfn [f & args] (apply f args))

(ann ^:no-check make-consumer [String String -> Nothing])
(defn make-consumer [key secret]
  {:author "Daniel Szmulewicz <https://github.com/danielsz>"}
  (reset! consumer {:key key :secret secret}))

(ann ^:no-check make-client [String String -> Nothing])
(defn make-client [consumer-key consumer-secret]
  (make-consumer consumer-key consumer-secret)
  callfn)

(ann ^:no-check with-user [Any Any * -> Any])
(defmacro with-user
  "Sets the user OAuth access token for write access and for accessing private user data."
  {:author "Daniel Szmulewicz <https://github.com/danielsz>"}
  [oauth-token oauth-secret & body]
  `(binding [*oauth-token* ~oauth-token
             *oauth-secret* ~oauth-secret]
     (do
       ~@body)))

(ann ^:no-check with-user [Any * -> Any])
(defmacro with-exception-handling 
  "A helper macro for performing request with a try catch"
  [msg & forms]
  `(try (do ~@forms)
     (catch Exception ex# 
       {:error ~msg})))
