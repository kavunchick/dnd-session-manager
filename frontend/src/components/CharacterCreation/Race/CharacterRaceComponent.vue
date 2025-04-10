<script setup>
import {onMounted, ref} from "vue";
import {classApi} from "@/plugins/api.js";
import CharacterClassCard from "@/components/CharacterCreation/Class/CharacterClassCard.vue";
import CharacterRaceCard from "@/components/CharacterCreation/Race/CharacterRaceCard.vue";
const classes = ref([])

onMounted(async () => {
    classes.value = await classApi.getClassList()
})
</script>

<template>
    <div class="w-200">
        <CharacterRaceCard v-for="clazz in classes" :clazz="clazz" />
    </div>
</template>
