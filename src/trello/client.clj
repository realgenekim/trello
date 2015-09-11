(ns trello.client
  (:require
   [clj-http.client :as client]
   [trello.core :refer [consumer *oauth-token* *oauth-secret*]]
   [oauth.client :as oauth]
   [clojure.data.json :as json]))

(def base-url "https://api.trello.com/1/")
(def authorize-url "https://trello.com/1/authorize")

(defn full-url 
  "Get the full api url given an endpoint"
  [endpoint]
  (apply str [base-url endpoint]))

(defn request-builder 
  "Builds a request to be executed by a HTTP client"
  [auth method url & params]
  (let [query-params (merge auth (into {} params))]
    (assert (keyword? method))
    {:method method
     :as :json
     :query-params query-params
     :url url}))

(defn sign [method uri params]
  {:author "Daniel Szmulewicz <https://github.com/danielsz>"}
  (if (bound? #'*oauth-token* #'*oauth-secret*)
    (let [consumer (oauth/make-consumer 
                    (:key @consumer) 
                    (:secret @consumer)
                    "https://trello.com/1/OAuthGetRequestToken"
                    "https://trello.com/1/OAuthGetAccessToken"
                    "https://trello.com/1/OAuthAuthorizeToken"
                    :hmac-sha1)]
      (oauth/credentials consumer *oauth-token* *oauth-secret* method uri params))
    {:api_key (:key @consumer)}))

(defn api-call
  "Calls the Trello API with the provided endpoint and params. Returns
  the response as a Clojure data structure (automatically parses the
  JSON response with clojure.data.json)" 
  {:author "Daniel Szmulewicz <https://github.com/danielsz>"}
  [method path & {:keys [params payload]}]
  (when-not (seq @consumer) 
    (throw (Throwable. "You must create a consumer first (Trello API key + secret).")))
  (let [uri (str base-url path)
        options {:query-params (merge params (sign method uri params))}]
    (case method
      :GET (-> (client/get uri options)
               (:body)
               (json/read-str :key-fn keyword))
      :POST
      :PUT
      :DELETE)))
