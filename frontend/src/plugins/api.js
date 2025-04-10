import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080';

export const sessionApi = {
    async getSessionList() {
        const response = await axios.get("/sessions")
        return response.data
    },

    async createSession(body) {
        const response = await axios.put("/sessions", body)
        return response.data
    }
}

export const characterApi = {
    async getCharacterList() {
        const response = await axios.get("/characters")
        return response.data
    }
}

export const userApi = {
    async registerUser() {
        const response = await axios.put("/login")
        return response.data
    }
}

export const raceApi = {
    async getRaceList() {
        const response = await axios.get("/races")
        return response.data
    }
}

export const classApi = {
    async getClassList() {
        const response = await axios.get("/classes")
        return response.data
    }
}