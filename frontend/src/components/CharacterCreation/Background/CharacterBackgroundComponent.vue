<script setup>
import {Form} from '@primevue/forms';
import {InputText} from "primevue";
import {Button} from "primevue";
import Select from 'primevue/select';
import {FloatLabel} from "primevue";
import Textarea from 'primevue/textarea';
import {useI18n} from 'vue-i18n';
import {onMounted, ref} from "vue";
import ImageUploadComponent from "@/components/CharacterCreation/Background/ImageUploadComponent.vue";

const {t} = useI18n();

const character = ref({
    name: '',
    alignment: null,
    background: null,
    flaws: null,
    ideals: null,
    bonds: null,
    traits: null,
    image: null
});

const emit = defineEmits(['submit']);

const options = [
    {label: t('character.alignment.chaoticEvil'), value: 0},
    {label: t('character.alignment.chaoticGood'), value: 1},
    {label: t('character.alignment.chaoticNeutral'), value: 2},
    {label: t('character.alignment.lawfulEvil'), value: 3},
    {label: t('character.alignment.lawfulGood'), value: 4},
    {label: t('character.alignment.lawfulNeutral'), value: 5},
    {label: t('character.alignment.neutral'), value: 6},
    {label: t('character.alignment.neutralEvil'), value: 7},
    {label: t('character.alignment.neutralGood'), value: 8}
]

const resolver = ({values}) => {
    const errors = {};

    if (!values.username) {
        errors.username = [{message: 'Username is required.'}];
    }

    return {
        values,
        errors
    };
};

const onFormSubmit = ({valid}) => {
    if (valid) {
        console.log('Form submitted successfully!');
    }
};

onMounted(() => {
    console.log(options.alignments);
})
</script>

<template>
    <Form v-slot="$form" :resolver @submit="onFormSubmit" class="flex flex-col gap-4 w-full">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4 w-full">
            <div class="flex flex-col gap-3">
                <InputText name="name" type="text" :placeholder="t('character.create.characterName')"
                           v-model="character.name" fluid/>
                <Select
                    v-model="character.alignment"
                    :options="options"
                    optionLabel="label"
                    optionValue="value"
                    :placeholder="t('character.create.selectAlignment')"
                    class="w-full md:w-auto"
                />
                <FloatLabel variant="on">
                    <Textarea id="description" v-model="character.background" rows="6" class="w-full"/>
                    <label for="description">{{ t('character.create.background') }}</label>
                </FloatLabel>
                <FloatLabel variant="on">
                    <Textarea id="flaws" v-model="character.flaws" rows="4" class="w-full"/>
                    <label for="flaws">{{ t('character.create.flaws') }}</label>
                </FloatLabel>
            </div>
            <div class="flex flex-col gap-3">
                <FloatLabel variant="on">
                    <Textarea id="ideals" v-model="character.ideals" rows="4" class="w-full"/>
                    <label for="ideals">{{ t('character.create.ideals') }}</label>
                </FloatLabel>
                <FloatLabel variant="on">
                    <Textarea id="bonds" v-model="character.bonds" rows="4" class="w-full"/>
                    <label for="bonds">{{ t('character.create.bonds') }}</label>
                </FloatLabel>
                <FloatLabel variant="on">
                    <Textarea id="traits" v-model="character.traits" rows="4" class="w-full"/>
                    <label for="traits">{{ t('character.create.traits') }}</label>
                </FloatLabel>
            </div>
            <div class="flex items-start justify-center p-4">
                <ImageUploadComponent @upload="(imageUrl) => character.image = imageUrl"/>
            </div>
        </div>
        <Button type="submit" severity="secondary" :label="t('general.submit')" class="self-end"/>
    </Form>
</template>