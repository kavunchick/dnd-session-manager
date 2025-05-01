import {createRouter, createWebHistory} from 'vue-router'
import LoginView from "@/views/LoginView.vue";
import MenuView from "@/views/MenuView.vue";
import SessionView from "@/views/SessionView.vue";

import axios from "axios";
import CharacterCreationView from "@/views/CharacterCreationView.vue";
import ManageSessionComponent from "@/components/SessionManaging/ManageSessionComponent.vue";


const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        component: LoginView
    },
    {
        path: '/menu',
        component: MenuView
    },
    {
        path: '/sessions',
        component: SessionView
    },
    {
        path: '/character',
        component: CharacterCreationView
    },
    {
        path: '/sessions/:sessionId',
        component: ManageSessionComponent,
        props: true,
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from) => {
    if ( !axios.defaults.headers.common['Authorization'] && to.path !== '/login')
        return { path: '/login'}
})

export default router