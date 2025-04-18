<script setup>
import {inject} from 'vue'
import {Button, Knob} from 'primevue'
import {useI18n} from "vue-i18n";

const {t} = useI18n()
const { statKey, allocate } = defineProps(['statKey','allocate'])
const { stats, assignPoints } = inject('statsContext')
const pointsSpent = inject('pointsSpent')
</script>

<template>
  <div class="flex flex-col items-center">
    <p class="capitalize">{{ statKey }}: {{ stats[statKey] }}</p>
    <Knob :model-value="stats[statKey]" :size="150" readonly :max="30"/>
    <div class="flex gap-2">
      <Button
          icon="pi pi-plus"
          @click="assignPoints(statKey)"
          :disabled="pointsSpent >= allocate || stats[statKey] >= 15"
      />
      <Button
          icon="pi pi-minus"
          @click="assignPoints(statKey, false)"
          :disabled="stats[statKey] <= 8"
      />
    </div>
  </div>
</template>
