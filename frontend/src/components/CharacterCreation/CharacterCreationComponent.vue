<script setup>
import Stepper from 'primevue/stepper';
import StepList from 'primevue/steplist';
import StepPanels from 'primevue/steppanels';
import Step from 'primevue/step';
import StepPanel from 'primevue/steppanel';
import Button from "primevue/button";
import {useI18n} from "vue-i18n";
import {reactive, ref} from "vue";
import CharacterClassComponent from "@/components/CharacterCreation/Class/CharacterClassComponent.vue";
import CharacterRaceComponent from "@/components/CharacterCreation/Race/CharacterRaceComponent.vue";
import CharacterBackgroundComponent from "@/components/CharacterCreation/Background/CharacterBackgroundComponent.vue";
import AssignStatsComponent from "@/components/CharacterCreation/Stats/AssignStatsComponent.vue";
import {characterApi} from "@/plugins/api.js";
import router from "@/plugins/router.js";

const character = reactive({
    name: null,
    background: null,
    ideals: null,
    bonds: null,
    flaws: null,
    image: null,
    alignment: null,
    classId: null,
    raceId: null,
    traits: null,
    stats: null
});

function createCharacter() {
    character.stats = Object.values(statsCmp.value.getStats())
    console.log(character.stats)
    characterApi.createCharacter(character)
    router.push("/character");
}

const statsCmp = ref(null)

function handleStepSubmit(stepValue, payload, activateCallback) {
    if (stepValue === '1')
        character.classId = payload;
    else if (stepValue === '2')
        character.background = payload;
    else if (stepValue === '3')
        character.raceId = payload;
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
                                character.classId = selectedClass;
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
                                character.traits = ch.traits;
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
                                character.raceId = selectedRace;
                                activateCallback('4');
                        }"/>
                    </div>
                    <div class="flex pt-6 justify-between">
                        <Button :label="t('general.back')" @click="activateCallback('2')" icon="pi pi-arrow-left"/>
                    </div>
                </StepPanel>
                <StepPanel value="4" v-slot="{ activateCallback }">
                    <div>
                        <AssignStatsComponent ref="statsCmp"/>
                    </div>
                    <div class="flex pt-6 justify-between">
                        <Button :label="t('general.back')" @click="activateCallback('3')" icon="pi pi-arrow-left"/>
                        <Button type="submit" severity="secondary" :label="t('general.submit')" @click="createCharacter"/>
                    </div>
                </StepPanel>
            </StepPanels>
        </Stepper>
    </div>
</template>
