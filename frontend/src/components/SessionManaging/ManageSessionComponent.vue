<script setup>
import {onMounted, ref} from "vue";
import {sessionApi, sessionCharacterApi} from "@/plugins/api.js";
import PlayerCardComponent from "@/components/PlayerCardComponent.vue";
import {Toast, useToast} from "primevue";
import {useI18n} from "vue-i18n";

const props = defineProps(['sessionId'])
const session = ref(null)
const toast = useToast()
const {t} = useI18n()

async function load() {
    session.value = await sessionApi.getSession(props.sessionId);
}

async function deletePlayer(playerId) {
    await sessionCharacterApi.deleteCharacter(playerId).then(() => {
        session.value.players = session.value.players.filter(player => player.id !== playerId)
        toast.add({
            severity: 'success',
            summary: t('general.success'),
            detail: t('sessionCharacter.deleteMessage'),
            life: 3000
        })
    }).catch((err) => {
            toast.add({
                severity: 'error',
                summary: t('general.error'),
                detail: t('sessionCharacter.deleteError'),
                life: 3000
            })
    })
}

async function updatePlayer(playerId, payload) {
    await sessionCharacterApi.updateCharacter(playerId, payload).then(
        (_) => {
            toast.add({
                severity: 'success',
                summary: t('general.success'),
                detail: t('sessionCharacter.updateMessage'),
                life: 3000
            })
        }
    ).catch(() => {
        toast.add({
            severity: 'error',
            summary: t('general.error'),
            detail: t('sessionCharacter.updateError'),
            life: 3000
        })
    })
}

onMounted(async () => {
    await load()
    console.log(session.value);
})
</script>

<template>
    <Toast/>
    <div
        v-if="session"
        class="p-10 grid gap-4
         grid-cols-1
         sm:grid-cols-2
         md:grid-cols-3
         lg:grid-cols-4"
    >
        <div
            v-for="player in session.players"
            :key="player.id"
            class="min-w-0">
            <PlayerCardComponent :player="player" :character="player.character" :stats="stats" class="min-w-0"
                                 @delete-player="deletePlayer" @update-player="updatePlayer"/>
        </div>
    </div>

    <div v-else>
    </div>

</template>
