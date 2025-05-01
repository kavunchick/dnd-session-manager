<script setup>
import {onMounted, ref} from "vue";
import {sessionApi} from "@/plugins/api.js";
import PlayerCardComponent from "@/components/SessionManaging/PlayerCardComponent.vue";

const props = defineProps(['sessionId'])

const session = ref(null)

async function load() {
    session.value = await sessionApi.getSession(props.sessionId);
}

function deletePlayer(playerId) {
    session.value.players = session.value.players.filter(player => player.id !== playerId)
}

onMounted(async () => {
    await load()
    console.log(session.value);
})
</script>

<template>
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
            <PlayerCardComponent :player="player" class="min-w-0" @delete-player="deletePlayer"/>
        </div>
    </div>

    <div v-else>
    </div>

</template>
