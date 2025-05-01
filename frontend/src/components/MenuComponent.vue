<script setup>
import MenuCardComponent from "@/components/MenuCardComponent.vue";
import {useI18n} from 'vue-i18n';
import {useUserStore} from "@/plugins/userStore.js";

const {t} = useI18n();
const userStore = useUserStore();

</script>

<template>
    <div class="flex flex-col justify-center items-center min-h-full p-4">
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-2 gap-4">
            <MenuCardComponent image-path="/src/assets/character_creation.png"
                               :title="t('menu.titles.listCharacter')"
                               :text="t('menu.content.listCharacter')"
                               router-link="/character"/>
            <MenuCardComponent image-path="/src/assets/session.png"
                               :title="t('menu.titles.manageSession')"
                               :text="t('menu.content.manageSession')"
                               router-link="/sessions"/>
        </div>
    </div>
</template>
