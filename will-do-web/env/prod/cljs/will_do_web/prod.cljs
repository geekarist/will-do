(ns will-do-web.prod
  (:require [will-do-web.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
