<script setup>
import Stepper from 'primevue/stepper';
import StepList from 'primevue/steplist';
import StepPanels from 'primevue/steppanels';
import Step from 'primevue/step';
import StepPanel from 'primevue/steppanel';
import Button from "primevue/button";
import {useI18n} from "vue-i18n";
import {reactive} from "vue";
import CharacterClassComponent from "@/components/CharacterCreation/Class/CharacterClassComponent.vue";
import CharacterRaceComponent from "@/components/CharacterCreation/Race/CharacterRaceComponent.vue";
import CharacterBackgroundComponent from "@/components/CharacterCreation/Background/CharacterBackgroundComponent.vue";

const character = reactive({
    class: null,
    name: null,
    alignment: null,
    background: null,
    flaws: null,
    ideals: null,
    bonds: null,
    image: null,
    race: null,
    abilities: null
});

function handleStepSubmit(stepValue, payload, activateCallback) {
    if (stepValue === '1')
        character.class = payload;
    else if (stepValue === '2')
        character.background = payload;
    else if (stepValue === '3')
        character.race = payload;
    else if (stepValue === '4')
        character.abilities = payload;
    const nextStep = (parseInt(stepValue) + 1).toString();
    activateCallback(nextStep);
}

const {t} = useI18n()
</script>

<template>
    <div class="m-8">
        <Stepper value="1" linear>
            <StepList>
                <Step value="1">{{ t('character.create.class') }}</Step>
                <Step value="2">{{ t('character.create.background') }}</Step>
                <Step value="3">{{ t('character.create.race') }}</Step>
                <Step value="4">{{ t('character.create.abilities') }}</Step>
            </StepList>
            <StepPanels>
                <StepPanel value="1" v-slot="{ activateCallback }">
                    <div class="flex flex-col justify-center items-center">
                        <CharacterClassComponent
                            @select="(selectedClass) => {
                                character.class = selectedClass;
                                activateCallback('2');
                        }"
                        />
                    </div>
                </StepPanel>
                <StepPanel value="2" v-slot="{ activateCallback }">
                    <div class="flex flex-col ">
                        <CharacterBackgroundComponent
                            @submit="(ch) => {
                                character.name = ch.name;
                                character.alignment = ch.alignment;
                                character.background = ch.background;
                                character.flaws = ch.flaws;
                                character.ideals = ch.ideals;
                                character.bonds = ch.bonds;
                                character.image = ch.image;
                                activateCallback('3');
                        }"
                        />
                    </div>
                    <div class="flex pt-6 justify-between">
                        <Button :label="t('general.back')" @click="activateCallback('1')" icon="pi pi-arrow-left"/>
                    </div>
                </StepPanel>
                <StepPanel value="3" v-slot="{ activateCallback }">
                    <div class="flex flex-col justify-center items-center">
                        <CharacterRaceComponent
                            @select="(selectedRace) => {
                                character.race = selectedRace;
                                activateCallback('4');
                        }"/>
                    </div>
                    <div class="flex pt-6 justify-between">
                        <Button :label="t('general.back')" @click="activateCallback('2')" icon="pi pi-arrow-left"/>
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
