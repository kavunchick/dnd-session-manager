<script setup>
import {ref} from 'vue'
import InputText from 'primevue/inputtext'
import i18n from "@/plugins/translations.js";
import {useI18n} from "vue-i18n";
const imageUrl = ref('')

const {t} = useI18n()

const emit = defineEmits(['upload']);

function handleUrlInput() {
    if (imageUrl.value) {
        emit('upload', imageUrl.value);
    }
}
</script>

<template>
    <div class="p-fluid">
        <label for="imageUrl">{{t("character.create.uploadImage")}}</label>
        <InputText id="imageUrl" v-model="imageUrl" @input="handleUrlInput" placeholder="https://example.com/image.jpg" />
        <div v-if="imageUrl" class="mt-3">
            <img :src="imageUrl" alt="Image Preview" style="max-width: 300px; max-height: 300px;" />
        </div>
    </div>
</template>
