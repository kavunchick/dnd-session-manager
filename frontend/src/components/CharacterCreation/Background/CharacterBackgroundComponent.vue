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
    <Form v-slot="$form" :resolver @submit="onFormSubmit" class="flex flex-col gap-4 w-full sm:w-56">
        <div class="flex flex-row gap-4 w-full">
            <div class="flex flex-col gap-3">
                <InputText name="name" type="text" :placeholder="t('character.create.characterName')"
                           v-model="character.name" fluid/>
                <Select
                    v-model="character.alignment"
                    :options="options"
                    optionLabel="label"
                    optionValue="value"
                    :placeholder="t('character.create.selectAlignment')"
                    class="w-full md:w-14rem"
                />
                <FloatLabel variant="on">
                    <Textarea id="description" v-model="character.background" rows="10" cols="60" style="resize: none"/>
                    <label for="description">{{ t("character.create.background") }}</label>
                </FloatLabel>
                <FloatLabel variant="on">
                    <Textarea id="flaws" v-model="character.flaws" rows="5" cols="30" style="resize: none"/>
                    <label for="flaws">{{ t("character.create.flaws") }}</label>
                </FloatLabel>
                <FloatLabel variant="on">
                    <Textarea id="ideals" v-model="character.ideals" rows="5" cols="30" style="resize: none"/>
                    <label for="ideals">{{ t("character.create.ideals") }}</label>
                </FloatLabel>
                <FloatLabel variant="on">
                    <Textarea id="bonds" v-model="character.bonds" rows="5" cols="30" style="resize: none"/>
                    <label for="bonds">{{ t("character.create.bonds") }}</label>
                </FloatLabel>
            </div>
            <ImageUploadComponent @upload="(imageUrl) => character.image = imageUrl"/>
        </div>
        <Button type="submit" severity="secondary" label="Submit" @click="emit('submit', character)"/>
    </Form>
</template>