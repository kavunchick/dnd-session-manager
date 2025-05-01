<script setup>
import PlayerCardComponent from "@/components/PlayerCardComponent.vue";
import {onMounted, ref} from "vue";
import {characterApi, sessionCharacterApi} from "@/plugins/api.js";
import {Button, Toast, useToast} from "primevue";
import {useI18n} from "vue-i18n";
import router from "@/plugins/router.js";

const characters = ref();
const toast = useToast()
const {t} = useI18n()

async function deletePlayer(characterId) {
    await characterApi.deleteCharacter(characterId).then((_) => {
        characters.value = characters.value.filter(character => character.id !== characterId)
        toast.add({
            severity: 'success',
            summary: t('general.success'),
            detail: t('character.deleteSuccess'),
            life: 3000
        })
    }).catch((err) => {
        if (err.response.status === 409) {
            toast.add({
                severity: 'error',
                summary: t('general.error'),
                detail: t('character.deleteConflict'),
                life: 3000
            })
        } else {
            toast.add({
                severity: 'error',
                summary: t('general.error'),
                detail: t('character.deleteError'),
                life: 3000
            })
        }
    })
}

async function updatePlayer(characterId, payload) {
    await characterApi.updateCharacter(characterId, payload).then((_) => {
        toast.add({
            severity: 'success',
            summary: t('general.success'),
            detail: t('character.updateSuccess'),
            life: 3000
        })
    }).catch(() => {
        toast.add({
            severity: 'error',
            summary: t('general.error'),
            detail: t('character.updateError'),
            life: 3000
        })
    })
}

onMounted(async () => {
    characters.value = await characterApi.getCharacterList()
})
</script>

<template>
    <Toast/>
    <div
        v-if="characters"
        class="p-10 grid gap-4
           grid-cols-1
           sm:grid-cols-2
           md:grid-cols-3
           lg:grid-cols-4"
    >
        <div v-for="character in characters" :key="character.id" class="min-w-0" >
            <PlayerCardComponent
                :character="character"
                :stats="character.stats"
                class="min-w-0"
                @delete-player="deletePlayer"
                @update-player="updatePlayer"
            />
        </div>

        <div
            style="width:25rem"
            class="flex items-center justify-center cursor-pointer border-2 border-dashed rounded-lg text-3xl text-gray-400"
            @click='router.push("/character/create")'
        >
            +
        </div>
    </div>
</template>