<script setup>
import {Avatar, Menubar} from "primevue";
import {useUserStore} from "@/plugins/userStore.js";
import router from "@/plugins/router.js";
import {Button} from "primevue";

const userStore = useUserStore()
</script>

<template>
    <Menubar style="padding: 1rem 1rem 1rem 1.5rem">
        <template #start>
            <Button variant="outlined" rounded @click="router.push('/menu')">
                <i class="pi pi-home" />
            </Button>
        </template>
        <template #end>
            <div class="flex items-center gap-2">
                <Avatar :image="userStore.avatar" style="width: 32px; height: 32px" shape="circle"/>
            </div>
        </template>
    </Menubar>
</template>

<style scoped>

</style>