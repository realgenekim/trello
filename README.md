# Trello

Clojure wrapper for the Trello API.

[Official Trello Docs](https://trello.com/docs/index.html) | [Official Trello API Reference](https://trello.com/docs/api/index.html)

## Usage

Trello is available from Clojars.org. To use it, add the following as a dependency in Leiningen or Boot.

```clojure
[trello "0.1.2-SNAPSHOT"]
```

# Usage

First, generate your api key and api token: [trello.com/1/appKey/generate](https://trello.com/1/appKey/generate)

From there you can obtain a key and secret from the Trello developer dashboard. Now you can make a client like so:

```clojure
user> (def auth {:key "..." :secret "..."})
nil
user> (require '[trello.core :refer [make-client]])
#<Var@a17a546: {:key "...", :secret "..."}>
user> (def client (make-client (:key auth) (:secret auth)))
#<Var@7d383c5d: #object[trello.core$callfn 0x6823295a "trello.core$callfn@6823295a"]>
```

With this client, you can make any request to a public Trello board that you want. Until I get around to building the API docs, look through the source code to see how each Trello resource is accessed. This library is self-descriptive, meaning that each namespace refers to a resource, and each function in a namespace refers to an endpoint.

Once I reach v1.0.0, then all of the Trello API will be implemented. Until then, you can call specific API endpoints with `trello.client/api-call`

```clojure
user> (doc trello.client/api-call)
-------------------------
trello.client/api-call
([method path & {:keys [params payload]}])
  Calls the Trello API with the provided endpoint and params. Returns
  the response as a Clojure data structure (automatically parses the
  JSON response with clojure.data.json)
nil
user> (client trello.client/api-call :GET "boards/my-board-id")
{:closed false,
 :desc "",
 :pinned false,
 :name "My Trello Board",
...}

```

If you want to access private Trello boards, you'll need to get an access token from Trello. To do this you can use the following URL

https://trello.com/1/authorize?key=YOURKEY&name=My+Application&expiration=never&response_type=token&scope=read,write

## Things Built with Trello and Clojure

The main reason I'm building this library is because I'm using it at [nebulabio/www](https://github.com/nebulabio/www), which is a Clojure web app hosted on Heroku meant for crowdfunding my open source biomedical engineering projects.

If you use this library to make something cool with Clojure and Trello, share it! Message me on Twitter [@bensima](https://twitter.com/bensima) or submit a PR or an issue here on GitHub with a link back to your project.

## Acknowledgements

This is a fork and rewrite of [boxuk/trello](https://github.com/boxuk/trello). Largely inspired by [@danielsz's clojure-etsy-api](https://github.com/danielsz/etsy-clojure-api) and [@ianbarber's clj-gapi](https://github.com/ianbarber/clj-gapi). In fact, some of the functions are borrowed from @danielsz, and mentioned as such in the function meta data. Thanks Daniel!

## License

Copyright &copy; Ben Sima.

Distributed under the Eclipse Public License, the same as Clojure.
