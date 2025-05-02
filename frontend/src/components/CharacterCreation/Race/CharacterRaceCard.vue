<script setup>
import ScrollPanel from "primevue/scrollpanel";
import Card from "primevue/card";
import {Button} from "primevue";

const props = defineProps(['race']);
const emit = defineEmits(['select']);

function onSelectRace() { emit('select', props.race.id); }

</script>

<template>
    <Button @click="onSelectRace" variant="text">
        <Card>
            <template #content>
                <div class="flex flex-row gap-4 w-150">
                    <img
                        :src="race.image ?? 'https://as1.ftcdn.net/jpg/05/81/46/10/1000_F_581461096_AnVuuZfcsm4ZXVlNZfqQLecvHChVRq5y.webp'"
                        :alt="race.name"
                        class="w-30 h-60 rounded object-cover object-right"
                    />
                    <div class="flex flex-col">
                        <h1 class="text-2xl font-bold text-gray-800">{{ race.name }}</h1>
                        <div class="flex justify-between items-center">
                            <ScrollPanel style="width: 100%; height: 200px">
                                <p>{{ props.race.description }}</p>
                            </ScrollPanel>
                        </div>
                    </div>
                </div>
            </template>
        </Card>
    </Button>
</template>

<style scoped>

</style>