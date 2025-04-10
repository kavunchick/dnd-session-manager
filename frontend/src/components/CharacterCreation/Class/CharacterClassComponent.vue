<script setup>
import {defineEmits, onMounted, ref} from "vue";
import {classApi} from "@/plugins/api.js";
import CharacterClassCard from "@/components/CharacterCreation/Class/CharacterClassCard.vue";

const classes = ref([]);
const emit = defineEmits(['select']);

function handleClassSelect(selectedClass) {
    emit('select', selectedClass);
}

onMounted(async () => {
    classes.value = await classApi.getClassList();
});
</script>

<template>
    <div class="w-200">
        <CharacterClassCard v-for="clazz in classes" :key="clazz.id" :clazz="clazz"
                            @select="(selectedClass) => handleClassSelect(selectedClass)"
        />
    </div>
</template>
