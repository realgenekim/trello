(ns trello.util
  (:require
   [clojure.core.typed :refer [ann cf check-ns U Any Nothing Vec Keyword Map HMap]]))

(ann normalize-http-request [String -> String])
(defn normalize-http-request
  "Given a request that starts with a forward slash, strip the
   slash to normalize the request string"
  [^String request-string]
  (if (.startsWith request-string "/")
    (subs request-string 1)
    request-string))

(ann get-gravatar-image [String -> String])
(defn get-gravatar-image
  "Given a gravatar hash, return the users avatar.
   If it can't be found a placeholder is returned"
  [hash]
  (let [gravatar-hash hash
        base-url "http://www.gravatar.com/avatar"]
    (format "%s/%s?d=mm" base-url gravatar-hash)))

(ann ^:no-check filter-by-param [Keyword Vec -> Nothing])
(defn filter-by-param
  "Given a result map, filter out and return only the values for
   the key specified. Utility function for inspecting collections"
  [key, results]
  (when-let [vector? results]
    (doseq [item (map #(get % key) results)]
      (prn item))))

