(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "ClojureScript tutorial app"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ; clj and cljs source path code
  :source-paths ["src/clj" "src/cljs" "src/brepl"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2069"]
                 [compojure "1.1.6"]
                 [domina "1.0.3-SNAPSHOT"]]
  
  ; lein-cljsbuild plugin to build a cljs project
  :plugins [[lein-cljsbuild "1.0.0"]
            [lein-ring "0.8.8"]]

  ; cljsbuild options configuation
  :cljsbuild {:builds
              {:dev
               {; cljs source code path
                :source-paths ["src/brepl" "src/cljs"]

                ; Google Closure (CLS) options configuration
                :compiler {; CLS generated JS script filename
                           :output-to "resources/public/js/modern_dbg.js"

                           ; minimal JS optimization directive
                           :optimizations :whitespace

                           ; generated JS code prettyfication
                           :pretty-print true}}
               :prod
               {; cljs source code path
                :source-paths ["src/brepl" "src/cljs"]

                :compiler {; the name of emitted JS script file
                           :output-to "resources/public/js/modern.js"

                           ; advanced optimization
                           :optimizations :advanced

                           ; no prettyfication
                           :pretty-print false}}
               :pre-prod
               {; cljs source code path
                :source-paths ["src/cljs"]

                :compiler {; different output name
                           :output-to "resources/public/js/modern_pre.js"

                           ; simple optimization
                           :optimizations :simple

                           ;no prettyfication
                           :pretty-print false}}}}
  
  :ring {:handler modern-cljs.core/handler})
