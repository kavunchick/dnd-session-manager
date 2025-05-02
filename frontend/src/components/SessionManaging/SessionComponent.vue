<script setup>
import DataView from 'primevue/dataview';
import {Button, Card, Dialog, InputText, Toast, useToast, useConfirm, Chip} from "primevue";
import {onMounted, ref, watch} from "vue";
import {characterApi, sessionApi, sessionCharacterApi, userApi} from "@/plugins/api.js";
import {useI18n} from "vue-i18n";
import AutoComplete from 'primevue/autocomplete';
import Popover from 'primevue/popover';
import router from "@/plugins/router.js";
import ConfirmPopup from 'primevue/confirmpopup';

const op = ref();
const sessions = ref([]);
const session = ref({name: ''});
const createDialogVisible = ref(false);
const {t} = useI18n();
const toast = useToast();
const confirm = useConfirm();

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

const load = async () => sessions.value = await sessionApi.getSessionList()

const users = ref([])
const user = ref()
const username = ref()

const allowCharacter = ref(true)

const characters = ref([])
const name = ref()
const character = ref()
const selectedSessionId = ref()

const findUserByUsername = async () => users.value = await userApi.findByUsername(username.value)

const findCharacterByNameAndAuthor = async () => characters.value = await characterApi.getByNameAndAuthor(name.value, user.value.sub)

function toggle(event, sessionId) {
    selectedSessionId.value = sessionId
    op.value.toggle(event)
}

const confirmDelete = (event, id) => {
    confirm.require({
            target: event.currentTarget,
            message: t('session.deleteMessage'),
            icon: 'pi pi-info-circle',
            rejectProps: {
                label: t('general.cancel'),
                severity: 'secondary',
                outlined: true
            },
            acceptProps: {
                label: t('general.delete'),
                severity: 'danger'
            },
            accept: async () => {
                await sessionApi.deleteSession(id).then(_ => {
                        toast.add({
                            severity: 'info',
                            summary: t('general.confirmed'),
                            detail: t('session.deleteSuccess'),
                            life: 3000
                        });
                        sessions.value = sessions.value.filter(session => session.id !== id)
                    }
                ).catch(
                    () => {
                        toast.add({
                            severity: 'error',
                            summary: t('general.error'),
                            detail: t('session.deleteError'),
                            life: 3000
                        });
                    }
                )

            },
            reject: () => {
                toast.add({
                    severity: 'error',
                    summary: t('general.rejected'),
                    detail: t('session.deleteRejection'),
                    life: 3000
                });
            }
        }
    )
    ;
}

onMounted(async () => await load() );

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
        await sessionCharacterApi.addCharacterToSession(selectedSessionId.value, newVal.id)
        await load()
        op.value.hide()
    }
})
</script>

<template>
    <Toast/>
    <ConfirmPopup></ConfirmPopup>
    <div class="m-8">
        <div class="flex flex-col items-end mb-2">
            <Button :label="t('menu.titles.createSession')" @click='createDialogVisible = true'/>
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
                                        <div class="p-4 bg-gray-50 rounded-lg shadow-sm">
                                            <div
                                                class="flex flex-col-reverse sm:flex-row sm:justify-end sm:items-center gap-2">
                                                <Button
                                                    :label="t('general.delete')"
                                                    severity="danger"
                                                    @click="confirmDelete($event, item.id)"
                                                    class="w-full sm:w-auto whitespace-nowrap"
                                                />
                                                <Button
                                                    :label="t('general.manage')"
                                                    @click="router.push(`/sessions/${item.id}`)"
                                                    class="w-full sm:w-auto whitespace-nowrap"
                                                />
                                            </div>
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
