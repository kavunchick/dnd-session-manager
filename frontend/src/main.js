import './assets/main.css'

import {createApp} from 'vue'
import PrimeVue from 'primevue/config'
import App from './App.vue'
import Aura from '@primeuix/themes/aura'
import 'primeicons/primeicons.css';
import router from '@/plugins/router.js'
import i18n from "@/plugins/translations.js";
import {createPinia} from "pinia";
import { initializeApp } from "firebase/app";
import {ToastService} from "primevue";

const firebaseConfig = {
    apiKey: import.meta.env.VITE_API_KEY,
    authDomain: import.meta.env.VITE_AUTH_DOMAIN,
    projectId: import.meta.env.VITE_PROJECT_ID,
    storageBucket: import.meta.env.VITE_STORAGE_BUCKET,
    messagingSenderId: import.meta.env.VITE_MESSAGING_SENDER_ID,
    appId: import.meta.env.VITE_APP_ID
};

initializeApp(firebaseConfig);

const pinia = createPinia()

createApp(App).use(PrimeVue, {
    theme: {
        preset: Aura,
        options: {
            cssLayer: {
                name: "primevue",
                order: "base, primevue",
            },
            darkModeSelector: ".dark",
        },
    }
}).use(router)
    .use(i18n)
    .use(pinia)
    .use(ToastService)
    .mount('#app')
