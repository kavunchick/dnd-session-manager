<script setup>
import {onMounted, ref} from "vue";
import {classApi, raceApi} from "@/plugins/api.js";
import CharacterClassCard from "@/components/CharacterCreation/Class/CharacterClassCard.vue";
import CharacterRaceCard from "@/components/CharacterCreation/Race/CharacterRaceCard.vue";
const races = ref([])

onMounted(async () => {
    races.value = await raceApi.getRaceList()
})
</script>

<template>
    <div class="w-200">
        <CharacterRaceCard v-for="race in races" :race="race" />
    </div>
</template>
