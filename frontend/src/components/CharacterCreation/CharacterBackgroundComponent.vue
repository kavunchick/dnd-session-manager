<script setup>
import { Form } from '@primevue/forms';
import alignments from '@/plugins/constants.js'

const resolver = ({ values }) => {
    const errors = {};

    if (!values.username) {
        errors.username = [{ message: 'Username is required.' }];
    }

    return {
        values,
        errors
    };
};

const onFormSubmit = ({ valid }) => {
    if (valid) {
        console.log('Form submitted successfully!');
    }
};
</script>

<template>
    <Form v-slot="$form" :resolver @submit="onFormSubmit" class="flex flex-col gap-4 w-full sm:w-56">
        <div class="flex flex-col gap-1">
            <InputText name="username" type="text" placeholder="Username" fluid />
            <Message v-if="$form.username?.invalid" severity="error" size="small" variant="simple">{{ $form.username.error?.message }}</Message>
        </div>
        <Button type="submit" severity="secondary" label="Submit" />
    </Form>

</template>