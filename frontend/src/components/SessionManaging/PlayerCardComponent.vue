<script setup>
import {ref, watch} from 'vue'
import {Card} from 'primevue'
import InputNumber from 'primevue/inputnumber'
import Button from 'primevue/button'
import {Panel} from "primevue";
import {Knob} from "primevue";
import {useI18n} from 'vue-i18n'
import {sessionCharacterApi} from "@/plugins/api.js";

const props = defineProps(['player'])

const {t} = useI18n()

const editing = ref(false)
const localLevel = ref(props.player.level)
const localExp = ref(props.player.experience)
const localHealth = ref(props.player.health)
const localStats = ref(props.player.stats)

const emit = defineEmits(['deletePlayer'])

const stats = [
    {key: 'str', label: 'character.stats.strength'},
    {key: 'dex', label: 'character.stats.dexterity'},
    {key: 'con', label: 'character.stats.constitution'},
    {key: 'int', label: 'character.stats.intelligence'},
    {key: 'wis', label: 'character.stats.wisdom'},
    {key: 'cha', label: 'character.stats.charisma'},
]

watch(() => props.player.level, v => localLevel.value = v)
watch(() => props.player.experience, v => localExp.value = v)
watch(() => props.player.health, v => localHealth.value = v)
watch(() => props.player.stats, v => localStats.value = v)

function toggleEdit() {
    if (editing.value) {
        const payload = {
            level: localLevel.value,
            experience: localExp.value,
            health: localHealth.value,
            stats: localStats.value
        }
        sessionCharacterApi.updateCharacter(props.player.id, payload)
    }
    editing.value = !editing.value
}

function deletePlayer() {
    sessionCharacterApi.deleteCharacter(props.player.id)
    emit('deletePlayer', props.player.id)
}
</script>

<template>
    <Card style="width: 25rem; overflow: hidden">
        <template #header>
            <div class="w-full aspect-[4/3] overflow-hidden">
                <img
                    :src="props.player.character.imageURI"
                    alt="Avatar"
                    class="w-full h-full object-cover object-center"
                />
            </div>
        </template>

        <template #title>
            <div class="flex flex-col items-center">
                <h2>{{ props.player.character.name }}</h2>
            </div>
        </template>

        <template #subtitle>
            <div class="flex flex-col items-center">
                <h3>{{ props.player.character.characterRace.name }}</h3>
                <h3>{{ props.player.character.characterClass.name }}</h3>
            </div>
        </template>

        <template #content>
            <div class="space-y-2">
                <!-- Level -->
                <div class="flex items-center space-x-2">
                    <span class="font-semibold">{{ t('character.card.level') }}</span>
                    <template v-if="!editing">
                        <span>{{ localLevel }}</span>
                    </template>
                    <template v-else>
                        <InputNumber v-model="localLevel" :min="1" :showButtons="true"/>
                    </template>
                </div>

                <!-- Experience -->
                <div class="flex items-center space-x-2">
                    <span class="font-semibold">{{ t('character.card.exp') }}</span>
                    <template v-if="!editing">
                        <span>{{ localExp }}</span>
                    </template>
                    <template v-else>
                        <InputNumber v-model="localExp" :min="0" :showButtons="true"/>
                    </template>
                </div>

                <!-- Health -->
                <div class="flex items-center space-x-2">
                    <span class="font-semibold">{{ t('character.card.health') }}</span>
                    <template v-if="!editing">
                        <span>{{ localHealth }}</span>
                    </template>
                    <template v-else>
                        <InputNumber v-model="localHealth" :min="0" :showButtons="true"/>
                    </template>
                </div>

                <Panel class="p-mb-4">
                    <div class="grid grid-cols-3 gap-4">
                        <div
                            class="p-col-12 p-md-6 p-lg-4"
                            v-for="(stat, i) in stats"
                            :key="stat.key"
                        >
                            <div class="p-d-flex p-flex-column p-ai-center">
                                <span class="font-semibold p-mb-2">{{ t(stat.label) }}</span>
                                <Knob
                                    v-model="localStats[i]"
                                    :min="0"
                                    :max="30"
                                    :size="100"
                                    :step="1"
                                    :readonly="!editing"
                                    :valueColor="editing ? '#42A5F5' : '#666666'"
                                    class="p-mb-2"
                                />
                            </div>
                        </div>
                    </div>
                </Panel>
                <div class="flex flex-row justify-between">
                    <Button
                        label="Delete"
                        icon="pi pi-trash"
                        severity="danger"
                        @click="deletePlayer"
                    />

                    <Button
                        :label="editing ? t('general.save') : t('general.edit')"
                        icon="pi pi-pencil"
                        @click="toggleEdit"
                    />
                </div>
            </div>
        </template>
    </Card>
</template>
