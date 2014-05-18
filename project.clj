(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "ClojureScript tutorial app"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ; clj and cljs source path code
  :source-paths ["src/clj" "src/cljs" "src/brepl"]
  :test-paths ["target/test/clj" "target/test/cljs"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2069"]
                 [compojure "1.1.6"]
                 [domina "1.0.3-SNAPSHOT"]
                 [hiccups "0.2.0"]
                 [org.clojars.magomimmo/shoreleave-remote-ring "0.3.1-SNAPSHOT"]
                 [org.clojars.magomimmo/shoreleave-remote "0.3.1-SNAPSHOT"]
                 [com.cemerick/valip "0.3.2"]
                 [enlive "1.1.4"]]
  
  ; lein-cljsbuild plugin to build a cljs project
  :plugins [[lein-cljsbuild "1.0.0"]
            [lein-ring "0.8.8"]
            [com.cemerick/clojurescript.test "0.2.1"]
            [com.keminglabs/cljx "0.3.2"]]

  :hooks [leiningen.cljsbuild]

  ; cljx task configuration
  :cljx {:builds [{:source-paths ["test/cljx"]
                   :output-path "target/test/clj"
                   :rules :clj}

                  {:source-paths ["test/cljx"]
                   :output-path "target/test/cljs"
                   :rules :cljs}]}

  ; cljsbuild options configuation
  :cljsbuild {:crossovers [valip.core valip.predicates
                           modern-cljs.login.validators
                           modern-cljs.shopping.validators]
              :test-commands {"phantomjs-whitespace"
                              ["phantomjs" :runner "test/js/testable_dbg.js"]

                              "phantomjs-simple"
                              ["phantomjs" :runner "test/js/testable_pre.js"]

                              "phantomjs-advanced"
                              ["phantomjs" :runner "test/js/testable.js"]}
              :builds
              {:whitespace-unit-tests
               {; CLJS scource code and unit test paths
                :source-paths ["src/brepl" "src/cljs" "target/test/cljs"]

                ; Google Closure Compilter options
                :compiler {; the name of emitted JS script file for unit testing
                           :output-to "test/js/testable_dbg.js"

                           ; minimum optimization
                           :optimizations :whitespace
                           ;prettify
                           :pretty-print true}}

               :simple-unit-tests
               {
                :source-paths ["src/brepl" "src/cljs" "target/test/cljs"]

                :compiler {
                            :output-to "test/js/testable_pre.js"
                            :optimizations :simple
                            :pretty-print false}}

               :advanced-unit-tests
               {:source-paths ["src/cljs" "target/test/cljs"]
                :compiler {
                           :output-to "test/js/testable.js"
                           :optimizations :advanced
                           :pretty false}}
               :dev
               {; cljs source code path
                :source-paths ["src/brepl" "src/cljs"]

                ; Google Closure (CLS) options configuration
                :compiler {; CLS generated JS script filename
                           :output-to "resources/public/js/modern_dbg.js"

                           ; minimal JS optimization directive
                           :optimizations :whitespace

                           ; generated JS code prettyfication
                           :pretty-print true}}
               :pre-prod
               {; cljs source code path
                :source-paths ["src/cljs"]

                :compiler {; different output name
                           :output-to "resources/public/js/modern_pre.js"

                           ; simple optimization
                           :optimizations :simple

                           ;no prettyfication
                           :pretty-print false}}  
               :prod
               {; cljs source code path
                :source-paths ["src/brepl" "src/cljs"]

                :compiler {; the name of emitted JS script file
                           :output-to "resources/public/js/modern.js"

                           ; advanced optimization
                           :optimizations :advanced

                           ; no prettyfication
                           :pretty-print false}}
               }}
  
  :ring {:handler modern-cljs.core/app})
