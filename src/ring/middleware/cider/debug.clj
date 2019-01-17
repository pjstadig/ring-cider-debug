(ns ring.middleware.cider.debug)

(defn wrap-cider-debug
  "Establishes thread local bindings necessary for CIDER debugging.  This should
  only be used in development, but it is safe to include even when cider-nrepl
  is not on the classpath.

  As of CIDER 0.20, when code instrumented for CIDER debugging is called outside
  of an nREPL request/response cycle (e.g. when called from a ring handler),
  there is some global state that is modified and can cause break points to no
  longer break.  Establishing a thread local binding for each ring request
  leaves the global state alone (as it should), and therefore break points work
  correctly."
  [handler]
  (let [*skip-breaks* (delay (ns-resolve 'cider.nrepl.middleware.debug '*skip-breaks*))]
    (fn
      ([request]
       (if @*skip-breaks*
         (with-bindings* {@*skip-breaks* (atom nil)}
           handler request)
         (handler request)))
      ([request respond raise]
       (if @*skip-breaks*
         (with-bindings* {@*skip-breaks* (atom nil)}
           handler request respond raise)
         (handler request respond raise))))))
