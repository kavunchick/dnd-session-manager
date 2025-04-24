<script setup>
import DataView from 'primevue/dataview';
import {Button, Card, Dialog, InputText, Toast, useToast} from "primevue";
import Chip from 'primevue/chip';
import {onMounted, ref, watch} from "vue";
import {characterApi, sessionApi, userApi} from "@/plugins/api.js";
import {useI18n} from "vue-i18n";
import AutoComplete from 'primevue/autocomplete';
import Popover from 'primevue/popover';
import {all} from "axios";

const op = ref();
const sessions = ref([]);
const session = ref({name: ''});
const createDialogVisible = ref(false);
const {t} = useI18n();
const toast = useToast();

async function submit() {
    await sessionApi.createSession(session.value).then(() => {
        toast.add({
            severity: 'success',
            summary: t('general.success'),
            detail: t('session.create.success'),
            life: 3000
        });
        load()
    }).catch(() => {
        toast.add({severity: 'error', summary: t('general.error'), detail: t('session.create.error'), life: 3000});
    })
    createDialogVisible.value = false
}

async function load() {
    try { sessions.value = await sessionApi.getSessionList(); }
    catch (error) { console.error("Failed to fetch sessions:", error); }
}

const users = ref([])
const user = ref()
const username = ref()

const allowCharacter = ref(true)

const characters = ref([])
const name = ref()
const character = ref()
const selectedSessionId = ref()

async function findUserByUsername() {
    try {
        users.value = await userApi.findByUsername(username.value)
    } catch (error) {
        console.error("Failed to fetch user:", error);
    }
}

async function findCharacterByNameAndAuthor() {
    try {
        characters.value = await characterApi.getByNameAndAuthor(name.value, user.value.sub)
    } catch (error) {
        console.error("Failed to fetch character:", error);
    }
}

function toggle(event, sessionId) {
    selectedSessionId.value = sessionId
    op.value.toggle(event)
}


onMounted(async () => {
    await load()
});

watch(username, (newVal) => {
    if (newVal && typeof newVal === 'object') {
        console.log('User selected:', newVal)
        allowCharacter.value = false;
        user.value = newVal;
    } else {
        allowCharacter.value = true;
    }
})

watch(name, async (newVal) => {
    if (newVal && typeof newVal === 'object') {
        console.log('Character selected:', newVal)
        character.value = newVal;
        await sessionApi.addCharacterToSession(selectedSessionId.value, newVal.id)
        await load()
        op.value.hide()
    }
})
</script>

<template>

    <div class="m-8">
        <div class="flex flex-col items-end mb-2">
            <Button :label="t('session.createSession')" @click='createDialogVisible = true'/>
        </div>
        <Toast/>
        <Dialog v-model:visible="createDialogVisible" modal :header="t('menu.titles.createSession')"
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
                                            class="flex flex-row md:flex-col justify-between items-start gap-2 text-lg font-medium mt-2 w-50">
                                            {{ item.name }}
                                        </div>
                                        <div class="grid grid-rows-2 grid-flow-col gap-2">
                                            <div v-for="player in item.players">
                                                <Chip
                                                    :image="player.imageURI != null ? player.imageURI : 'https://i.imgflip.com/22vo2u.jpg'"
                                                    :label="player.name"/>
                                            </div>
                                            <Chip
                                                :label="t('session.create.addPlayer')"
                                                icon="pi pi-plus"
                                                class="cursor-pointer hover:bg-gray-200 transition-shadow rounded-md px-2 py-1 shadow-sm"
                                                @click="toggle($event, item.id)"/>
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
    <Popover ref="op">
        <div class="flex flex-col gap-4 w-[25rem]">
            <div>
                <span class="font-medium block mb-2">Chose User</span>
                <AutoComplete v-model="username" type="text" placeholder="Username" :suggestions="users"
                              @complete="findUserByUsername" option-label="username" forceSelection/>
            </div>
            <div>
                <span class="font-medium block mb-2">Chose Character</span>
                <AutoComplete type="text" placeholder="Character name" :disabled="allowCharacter" v-model="name"
                              :suggestions="characters" @complete="findCharacterByNameAndAuthor" option-label="name"/>
            </div>
        </div>
    </Popover>
</template>
