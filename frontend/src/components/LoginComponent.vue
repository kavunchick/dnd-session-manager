<script setup>
import {Button, Image} from "primevue";
import { getAuth, signInWithPopup, GoogleAuthProvider } from "firebase/auth"
import {onMounted} from "vue";
import {useUserStore} from "@/plugins/userStore.js";
import router from "@/plugins/router.js";
import axios from "axios";
import {userApi} from "@/plugins/api.js";

const userStore = useUserStore()

function login() {
    const provider = new GoogleAuthProvider();
    signInWithPopup(getAuth(), provider).then((result => {
        userStore.username = result.user.displayName;
        userStore.avatar = result.user.photoURL;
        axios.defaults.headers.common['Authorization'] = `Bearer ${result.user["accessToken"]}`;
        userApi.registerUser();
        router.push("/menu")
    })).catch((err) => console.log(err))
}


onMounted(() => {

})
</script>

<template>
    <div class="flex flex-col justify-center items-center min-h-screen">
        <Image src="/src/assets/dnd_logo.png" alt="Image" width="250"/>
        <h1 class="font-extrabold text-5xl m-8 text-center"> Sign in to <br>D&D Session Manager </h1>
        <Button outlined rounded class="min-w-2xl" @click="login">
            Sign in with Google
        </Button>
    </div>
</template>
