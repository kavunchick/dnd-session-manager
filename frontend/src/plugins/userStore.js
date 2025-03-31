import {ref} from "vue";
import {defineStore} from "pinia";

export const useUserStore = defineStore('user', () => {
    const username = ref('')
    const avatar = ref('')

    return { username, avatar }
})