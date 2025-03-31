<script setup>
import {Card} from "primevue";
import {Button} from "primevue";
import router from "@/plugins/router.js";

defineProps(['imagePath', 'title', 'text', 'routerLink']);
</script>

<template>
    <Button variant="text" @click="router.push(routerLink)">
        <Card class="w-120 h-130 p-4 shadow-md bg-white rounded-lg">
            <template #header>
                <img class="object-cover rounded-md" alt="user header" :src="imagePath"/>
            </template>
            <template #title>
                <h3 class="text-md font-semibold text-center">{{ title }}</h3>
            </template>
            <template #content>
                <p class="text-gray-600 text-sm text-center">{{ text }}</p>
            </template>
        </Card>
    </Button>
</template>
