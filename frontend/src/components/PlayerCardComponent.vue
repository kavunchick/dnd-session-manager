<script setup>
import {ref, watch, computed} from 'vue'
import {Card} from 'primevue'
import InputNumber from 'primevue/inputnumber'
import Button from 'primevue/button'
import {Panel} from 'primevue'
import {Knob} from 'primevue'
import {useI18n} from 'vue-i18n'
import {Drawer} from "primevue";

const props = defineProps({
    player: {
        type: Object,
        default: null
    },
    character: {
        type: Object,
        required: true
    },
    stats: {
        type: Array,
        default: () => [0, 0, 0, 0, 0, 0]
    }
})

const {t} = useI18n()

const hasPlayer = computed(() => props.player !== null)

const editing = ref(false)
const localLevel = ref(props.player?.level ?? 1)
const localExp = ref(props.player?.experience ?? 0)
const localHealth = ref(props.player?.health ?? 0)
const localStats = ref([...(props.player?.stats ?? props.stats)])


const emit = defineEmits(['deletePlayer', 'updatePlayer'])

function toggleEdit() {
    if (editing.value) {
        const payload = {
            level: localLevel.value,
            experience: localExp.value,
            health: localHealth.value,
            stats: localStats.value
        }
        emit('updatePlayer', hasPlayer.value ? props.player.id : props.character.id, payload)
    }
    editing.value = !editing.value
}

const statDefs = [
    {key: 'str', label: 'character.stats.strength'},
    {key: 'dex', label: 'character.stats.dexterity'},
    {key: 'con', label: 'character.stats.constitution'},
    {key: 'int', label: 'character.stats.intelligence'},
    {key: 'wis', label: 'character.stats.wisdom'},
    {key: 'cha', label: 'character.stats.charisma'},
]

const visible = ref(false);

watch(() => props.player, p => {
    if (p) {
        localLevel.value = p.level
        localExp.value = p.experience
        localHealth.value = p.health
        localStats.value = [...p.stats]
    }
})
watch(() => props.stats, s => {
    if (!props.player) {
        localStats.value = [...s]
    }
})
</script>

<template>
    <Drawer v-model:visible="visible" position="top" class="h-fit max-h-[80vh] overflow-y-auto">
        <div class="space-y-2">
        <!-- Background -->
        <Panel toggleable class="text-black text-sm border border-gray-300 rounded shadow-sm">
            <template #header>
                <h1 class="font-semibold">{{ t('character.create.background') }}</h1>
            </template>
            <p class="mt-1 whitespace-pre-line">{{ props.character.background }}</p>
        </Panel>

        <!-- Flaws -->
        <Panel toggleable class="text-black text-sm border border-gray-300 rounded shadow-sm">
            <template #header>
                <h1 class="font-semibold">{{ t('character.create.flaws') }}</h1>
            </template>
            <p class="mt-1 whitespace-pre-line">{{ props.character.flaws }}</p>
        </Panel>

        <!-- Ideals -->
        <Panel toggleable class="text-black text-sm border border-gray-300 rounded shadow-sm">
            <template #header>
                <h1 class="font-semibold">{{ t('character.create.ideals') }}</h1>
            </template>
            <p class="mt-1 whitespace-pre-line">{{ props.character.ideals }}</p>
        </Panel>

        <!-- Bonds -->
        <Panel toggleable class="text-black text-sm border border-gray-300 rounded shadow-sm">
            <template #header>
                <h1 class="font-semibold">{{ t('character.create.bonds') }}</h1>
            </template>
            <p class="mt-1 whitespace-pre-line">{{ props.character.bonds }}</p>
        </Panel>

        <!-- Traits -->
        <Panel toggleable class="text-black text-sm border border-gray-300 rounded shadow-sm">
            <template #header>
                <h1 class="font-semibold">{{ t('character.create.traits') }}</h1>
            </template>
            <p class="mt-1 whitespace-pre-line">{{ props.character.traits }}</p>
        </Panel>
        </div>
    </Drawer>
    <Card style="width:25rem;overflow:hidden">
        <template #header>
            <div class="w-full aspect-[4/3] overflow-hidden">
                <img :src="character.imageURI" alt="Avatar"
                     class="w-full h-full object-cover object-top cursor-pointer hover:opacity-90 transition"
                     @click="visible = true"/>
            </div>
        </template>

        <template #title>
            <div class="flex flex-col items-center">
                <h2>{{ character.name }}</h2>
            </div>
        </template>
        <template #subtitle>
            <div class="flex flex-col items-center">
                <h3>{{ character.characterRace.name }}</h3>
                <h3>{{ character.characterClass.name }}</h3>
            </div>
        </template>

        <template #content>
            <div class="space-y-4">
                <template v-if="hasPlayer">
                    <div class="flex items-center space-x-2" v-for="field in [
                   { label: t('character.card.level'),   model: localLevel,   min:1 },
                   { label: t('character.card.exp'),     model: localExp,     min:0 },
                   { label: t('character.card.health'),  model: localHealth,  min:0 }
                 ]" :key="field.label">
                        <span class="font-semibold">{{ field.label }}</span>
                        <template v-if="!editing">
                            <span>{{ field.model }}</span>
                        </template>
                        <template v-else>
                            <InputNumber v-model="field.model"
                                         :min="field.min" :showButtons="true"/>
                        </template>
                    </div>
                </template>

                <Panel class="p-mb-4">
                    <div class="grid grid-cols-3 gap-4">
                        <div v-for="(stat, i) in statDefs" :key="stat.key">
                            <div class="flex flex-col items-center">
                                <span class="font-semibold mb-2">{{ t(stat.label) }}</span>
                                <Knob v-model="localStats[i]"
                                      :min="0" :max="30" :size="100" :step="1"
                                      :readonly="!editing"
                                      :valueColor="editing ? '#42A5F5' : '#666666'"/>
                            </div>
                        </div>
                    </div>
                </Panel>

                <div class="flex justify-between">
                    <Button label="Delete" icon="pi pi-trash" severity="danger"
                            @click="$emit('deletePlayer', hasPlayer ? props.player.id : props.character.id)"/>
                    <Button :label="editing ? t('general.save') : t('general.edit')"
                            icon="pi pi-pencil"
                            @click="toggleEdit"/>
                </div>
            </div>
        </template>
    </Card>
</template>
