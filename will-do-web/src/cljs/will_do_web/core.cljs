(ns will-do-web.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [reagent.session :as session]
    [reitit.frontend :as reitit]
    [clerk.core :as clerk]
    [accountant.core :as accountant]
    [ajax.core :refer [GET]]
    [hickory.core :as hick]
    [markdown.core :as markdown]))

;; -------------------------
;; Routes

(def router
  (reitit/router
    [["/" :index]
     ["/items"
      ["" :items]
      ["/:item-id" :item]]
     ["/about" :about]]))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

(path-for :about)

;; -------------------------
;; Page components

(defn home-page []
  (fn []
    [:div.main.container.my-4
     [:h1 "Welcome to will-do-web"]
     [:table.table.table-striped.table-bordered.table-hover.my-4
      [:thead [:tr
               [:th "Title"]
               [:th "Description"]
               [:th "Due date"]
               [:th "Status"]
               [:th "Pledge"]
               ]]
      [:tbody
       [:tr
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]]
       [:tr
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]]
       [:tr
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]]
       [:tr
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]
        [:td "Value"]]]]]))

(defn items-page []
  (fn []
    [:span.main
     [:h1 "The items of will-do-web"]
     [:ul (map (fn [item-id]
                 [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
                  [:a {:href (path-for :item {:item-id item-id})} "Item: " item-id]])
               (range 1 60))]]))

(defn item-page []
  (fn []
    (let [routing-data (session/get :route)
          item (get-in routing-data [:route-params :item-id])]
      [:span.main
       [:h1 (str "Item " item " of will-do-web")]
       [:p [:a {:href (path-for :items)} "Back to the list of items"]]])))

(def about-state (atom {:data "Loading..."}))

(defn conslog [str]
  (js/console.log str)
  str)

(defn md-to-hiccup [md-data]
  (as-> md-data v
        (markdown/md->html v)
        (hick/parse-fragment v)
        (map hick/as-hiccup v)))

(defn fetch-about []
  (GET "/about.md"
       {:handler
        (fn [response]
          (swap! about-state assoc :data
                 (-> response
                     str
                     md-to-hiccup)))}))

(defn about-page []
  (fetch-about)
  (fn []
    [:div.main.container.my-4
     [:h1 "About Will Do"]
     [:div (:data @about-state)]]))

;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :index #'home-page
    :about #'about-page
    :items #'items-page
    :item #'item-page))

;; -------------------------
;; Page mounting component

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div
       [:nav.navbar.navbar-light.bg-light.navbar-expand-sm
        [:ul.navbar-nav
         [:li.nav-item [:a.nav-link.navbar-brand {:href (path-for :index)} "Will Do"]]
         [:li.nav-item [:a.nav-link {:href (path-for :about)} "About"]]]]
       [page]
       [:footer.text-center
        [:p "Generated by the "
         [:a {:href   "https://github.com/reagent-project/reagent-template"
              :target "_blank"} "Reagent Template"] "."]]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (let [match (reitit/match-by-path router path)
             current-page (:name (:data match))
             route-params (:path-params match)]
         (reagent/after-render clerk/after-render!)
         (session/put! :route {:current-page (page-for current-page)
                               :route-params route-params})
         (clerk/navigate-page! path)
         ))
     :path-exists?
     (fn [path]
       (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
