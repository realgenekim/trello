(defproject me.bsima/trello "0.3.1"
  :description "Clojure wrapper for the Trello API"
  :url "https://github.com/realgenekim/trello"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}
  ;:dependencies [[org.clojure/clojure "1.7.0"]
  ;               [clj-http "3.10.0"]
  ;               [clj-oauth "1.5.2"]
  ;               [org.clojure/core.typed "0.3.11"]
  ;               [org.clojure/data.json "0.2.5"]
  ;               [org.clojure/tools.cli "0.3.3"]]
  :scm {:name "git"
        :url "https://github.com/realgenekim/trello"})
