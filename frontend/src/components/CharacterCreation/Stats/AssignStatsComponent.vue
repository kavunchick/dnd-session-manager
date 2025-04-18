<script setup>
import { ref, provide } from 'vue'
import StatKnobComponent from '@/components/CharacterCreation/Stats/StatKnobComponent.vue'
import {useI18n} from "vue-i18n";

const stats = ref({
  strength: 8,
  dexterity: 8,
  constitution: 8,
  intelligence: 8,
  wisdom: 8,
  charisma: 8
})
const allocate = ref(27)
const value = ref(0)

const emit = defineEmits(['submit']);

const onFormSubmit = ({valid}) => {
  if (valid) {
    console.log('Form submitted successfully!');
  }
};

function getStats() { return stats.value }

defineExpose({ getStats })
const {t} = useI18n()

function assignPoints(statKey, increment = true) {
  if (increment) {
    stats.value[statKey]++
    value.value++
  } else {
    stats.value[statKey]--
    value.value--
  }
}

provide('statsContext', { stats, assignPoints })
provide('pointsSpent', value)
</script>

<template>
  <div class="flex flex-col gap-4 items-center justify-center">
    <div class="flex flex-col items-center justify-center font-extrabold text-3xl">
      <h1>{{t("character.create.pointsLeft") + (allocate - value) }}</h1>
    </div>

    <div class="grid grid-cols-3 gap-10">
      <StatKnobComponent statKey="strength"  :allocate="allocate" />
      <StatKnobComponent statKey="dexterity" :allocate="allocate" />
      <StatKnobComponent statKey="constitution" :allocate="allocate" />
      <StatKnobComponent statKey="intelligence" :allocate="allocate" />
      <StatKnobComponent statKey="wisdom" :allocate="allocate" />
      <StatKnobComponent statKey="charisma" :allocate="allocate" />
    </div>
  </div>
</template>
