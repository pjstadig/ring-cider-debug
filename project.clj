(defproject ring-cider-debug "0.1.2-SNAPSHOT"
  :description "Establishes---in your development ring handler---thread local
 bindings necessary for CIDER debugging."
  :url "http://github.com/pjstadig/ring-cider-debug/"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :deploy-repositories [["releases" :clojars]]
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :profiles
  {:dev {:dependencies [[compojure "1.6.1"]
                        [javax.servlet/servlet-api "2.5"]
                        [ring "1.7.1"]
                        [ring/ring-defaults "0.3.2"]
                        [ring/ring-jetty-adapter "1.7.1"]]}}
  :repl-options {:init-ns ring.middleware.cider.debug.handler})
