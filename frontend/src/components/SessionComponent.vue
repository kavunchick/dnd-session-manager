<script setup>
import DataView from 'primevue/dataview';
import {Button, Card, Dialog, InputText, Toast, useToast} from "primevue";
import Chip from 'primevue/chip';
import {onMounted, ref} from "vue";
import {characterApi, sessionApi} from "@/plugins/api.js";
import {useI18n} from "vue-i18n";

const sessions = ref([]);
const players = ref([])

const session = ref({
    name: ''
});
const createDialogVisible = ref(false);
const {t} = useI18n();
const toast = useToast();

async function submit() {
    await sessionApi.createSession(session.value).then(() => {
        toast.add({severity: 'success', summary: t('general.success'), detail: t('session.create.success'), life: 3000});
        load()
    }).catch(() => {
        toast.add({severity: 'error', summary: t('general.error'), detail: t('session.create.error'), life: 3000});
    })
    createDialogVisible.value = false
}

async function load() {
    try {
        sessions.value = await sessionApi.getSessionList();
        players.value = await characterApi.getCharacterList();
        sessions.value.forEach((session) => {
            session.players = players.value
        });
    } catch (error) {
        console.error("Failed to fetch sessions:", error);
    }
}

onMounted(async () => { await load() });

</script>

<template>

    <div class="m-8">
        <div class="flex flex-col items-end mb-2">
            <Button :label="t('session.createSession')" @click='createDialogVisible = true'/>
        </div>
        <Toast/>
        <Dialog v-model:visible="createDialogVisible" modal :header="t('session.createSession')"
                :style="{ width: '25rem' }">
            <div class="flex items-center gap-4 mb-4">
                <label for="sessionName" class="font-semibold w-24">{{ t('session.create.name') }}</label>
                <InputText id="sessionName" class="flex-auto" autocomplete="off" v-model="session.name"/>
            </div>
            <div class="flex justify-end gap-2">
                <Button type="button" :label="t('general.cancel')" severity="secondary"
                        @click="createDialogVisible = false"></Button>
                <Button type="button" :label="t('general.save')" @click="submit"></Button>
            </div>
        </Dialog>


        <Card>
            <template #content>
                <DataView :value="sessions">
                    <template #list="slotProps">
                        <div class="flex flex-col">
                            <div v-for="(item, index) in slotProps.items" :key="index">
                                <div class="flex flex-col sm:flex-row sm:items-center p-6 gap-4"
                                     :class="{ 'border-t border-surface-200 dark:border-surface-700': index !== 0 }">
                                    <div class="flex flex-col md:flex-row justify-between md:items-center flex-1 gap-6">
                                        <div
                                            class="flex flex-row md:flex-col justify-between items-start gap-2 text-lg font-medium mt-2">
                                            {{ item.name }}
                                        </div>
                                        <div class="grid grid-rows-2 grid-flow-col gap-2">
                                            <div v-for="player in item.players">
                                                <Chip
                                                    :image="player.imageURI != null ? player.imageURI : 'https://i.imgflip.com/22vo2u.jpg'"
                                                    :label="player.name"/>
                                            </div>
                                        </div>
                                        <div class="flex flex-col md:items-end">
                                            <Button label="Manage"
                                                    class="flex-auto md:flex-initial whitespace-nowrap"></Button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </template>
                </DataView>
            </template>
        </Card>
    </div>
</template>
