<script setup>
import ScrollPanel from "primevue/scrollpanel";
import Card from "primevue/card";
import {Button} from "primevue";

const props = defineProps(['clazz']);
</script>

<template>
    <Button @click="console.log(props.clazz.id)" variant="text">
        <Card>
            <template #content>
                <div class="flex flex-row gap-4">
                    <img
                        :src="clazz.image ?? 'https://as1.ftcdn.net/jpg/05/81/46/10/1000_F_581461096_AnVuuZfcsm4ZXVlNZfqQLecvHChVRq5y.webp'"
                        :alt="clazz.name" class="w-80 h-60 rounded"/>
                    <div class="flex flex-col">
                        <h1 class="text-2xl font-bold text-gray-800">{{ clazz.name }}</h1>
                        <div class="flex justify-between items-center">
                            <ScrollPanel style="width: 100%; height: 200px">
                                <p>{{ props.clazz.description }}</p>
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