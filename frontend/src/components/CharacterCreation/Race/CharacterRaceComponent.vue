<script setup>
import {onMounted, ref} from "vue";
import {raceApi} from "@/plugins/api.js";
import CharacterRaceCard from "@/components/CharacterCreation/Race/CharacterRaceCard.vue";

const races = ref([])
const emit = defineEmits(['select']);

onMounted(async () => {
    races.value = await raceApi.getRaceList()
})

function handleRaceSelect(selectedRace) {
    emit('select', selectedRace);
}
</script>

<template>
    <div class="w-200">
        <CharacterRaceCard v-for="race in races" :key="race.id" :race="race"
                           @select="(selectedRace) => handleRaceSelect(selectedRace)" />
    </div>
</template>
