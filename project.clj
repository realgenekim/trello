(defproject trello "0.1.2-SNAPSHOT"
  :description "Clojure wrapper for the Trello API"
  :url "https://github.com/bsima/trello"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http "2.0.0"]
                 [clj-oauth "1.5.2"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/tools.cli "0.3.3"]]
  :scm {:name "git"
        :url "https://github.com/bsima/trello"}
  :plugins [[refactor-nrepl "1.2.0-SNAPSHOT"]
            [cider/cider-nrepl "0.10.0-SNAPSHOT"]])
