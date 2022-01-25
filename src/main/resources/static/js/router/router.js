import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import ShopForm from "components/shop/ShopForm.vue";
import Categories from "../components/shop/Categories.vue";
Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessagesList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '/categories', component: Categories},
    { path: '/shop', component: ShopForm },
    { path: '*', component: MessagesList }
]

export default new VueRouter({
    mode: 'history',
    routes
})