<script setup>
import Stepper from 'primevue/stepper';
import StepList from 'primevue/steplist';
import StepPanels from 'primevue/steppanels';
import Step from 'primevue/step';
import StepPanel from 'primevue/steppanel';
import Button from "primevue/button";

import {useI18n} from "vue-i18n";

const {t} = useI18n()

function loadRaces() {
}

</script>

<template>
    <div class="m-8">
        <Stepper value="1" linear>
            <StepList>
                <Step value="1">{{ t('character.create.race') }}</Step>
                <Step value="2">{{ t('character.create.background') }}</Step>
                <Step value="3">{{ t('character.create.species') }}</Step>
                <Step value="4">{{ t('character.create.abilities') }}</Step>
            </StepList>
            <StepPanels>
                <StepPanel value="1" v-slot="{ activateCallback }">
                    <div class="flex flex-col h-48">
                        <div
                            class="border-2 border-dashed border-surface-200 dark:border-surface-700 rounded bg-surface-50 dark:bg-surface-950 flex-auto flex justify-center items-center font-medium">
                            Content I
                        </div>
                    </div>
                    <div class="flex pt-6 justify-end">
                        <Button :label="t('general.next')" @click="activateCallback('2')" icon="pi pi-arrow-right" iconPos="right"/>
                    </div>
                </StepPanel>
                <StepPanel value="2" v-slot="{ activateCallback }">
                    <div class="flex pt-6 justify-between">
                        <Button :label="t('general.back')" @click="activateCallback('1')" icon="pi pi-arrow-left"/>
                        <Button :label="t('general.next')" @click="activateCallback('3')" icon="pi pi-arrow-right" iconPos="right"/>
                    </div>
                </StepPanel>
                <StepPanel value="3" v-slot="{ activateCallback }">
                    <div class="flex pt-6 justify-between">
                        <Button :label="t('general.back')" @click="activateCallback('2')" icon="pi pi-arrow-left"/>
                        <Button :label="t('general.next')" @click="activateCallback('4')" icon="pi pi-arrow-right" iconPos="right"/>
                    </div>
                </StepPanel>
                <StepPanel value="4" v-slot="{ activateCallback }">
                    <div class="flex pt-6">
                        <Button :label="t('general.back')" @click="activateCallback('3')" icon="pi pi-arrow-left"/>
                    </div>
                </StepPanel>
            </StepPanels>
        </Stepper>
    </div>
</template>
