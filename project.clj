(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "ClojureScript tutorial app"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ; clj and cljs source path code
  :source-paths ["src/cj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.5.1"]]
  
  ; lein-cljsbuild plugin to build a cljs project
  :plugins [[lein-cljsbuild "1.0.0"]]

  ; cljsbuild options configuation
  :cljsbuild {:builds
              [{; cljs source code path
                :source-paths ["src/cljs"]
                
                ; Google Closure (CLS) options configuration
                :compiler {; CLS generated JS script filename
                           :output-to "resources/public/js/modern.js"

                           ; minimal JS optimization directive
                           :optimizations :whitespace

                           ; generated JS code prettyfication
                           :pretty-print true}}]})