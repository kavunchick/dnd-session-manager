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
    },

    async addCharacterToSession(session, character) {
        const response = await axios.put("/session/character",
            {sessionId: session, characterId: character})
        return response.data
    }
}

export const characterApi = {
    async getCharacterList() {
        const response = await axios.get("/characters")
        return response.data
    },

    async createCharacter(body) {
        const response = await axios.put("/characters", body)
        return response.data
    },

    async getByNameAndAuthor(name, author) {
        const response = await axios.get("/characters/like", {
            params: {
                name: name,
                sub: author
            }
        })
        return response.data
    }
}

export const userApi = {
    async registerUser() {
        const response = await axios.put("/login")
        return response.data
    },

    async findByUsername(username) {
        const response = await axios.get("/users/like", {params: {username}})
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