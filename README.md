# ring-cider-debug

Establishes—in your development ring handler—thread local bindings necessary for CIDER debugging.  This should only be used in development, but it is safe to include even when cider-nrepl is not on the classpath.

As of CIDER 0.20, when code instrumented for CIDER debugging is called outside of an nREPL request/response cycle (e.g. when called from a ring handler), there is some global state that is modified and can cause break points to no longer break.  Establishing a thread local binding for each ring request leaves the global state alone (as it should), and therefore break points work correctly.

## Usage

Acquire from your favorite maven repo:

`[ring-cider-debug "0.1.0"]`

Add to your development handler middleware as usual.

```clojure
(require ’[ring.middleware.cider.debug :refer [wrap-cider-debug]])

(wrap-cider-debug some-handler)
```

## License

```
Copyright © Paul Stadig

This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary Licenses when the conditions for such availability set forth in the Eclipse Public License, v. 2.0 are satisfied: GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version, with the GNU Classpath Exception which is available at https://www.gnu.org/software/classpath/license.html.
```
