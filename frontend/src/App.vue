<script setup>
import ToolBarComponent from "@/components/ToolBarComponent.vue";
import router from "@/plugins/router.js";

</script>

<template>
    <ToolBarComponent v-if="router.currentRoute.value.path !== '/login'"/>
    <RouterView/>
</template>

