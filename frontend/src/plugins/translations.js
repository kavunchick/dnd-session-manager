import {createI18n} from "vue-i18n";

const i18n = createI18n({
    locale: 'en',
    fallbackLocale: 'en',
    messages: {
        en: {
            general: {
                save: "Save",
                cancel: "Cancel",
                success: "Success!",
                error: "Error",
                next: "Next",
                back: "Back",
                submit: "Submit",
            },
            menu: {
                titles: {
                    createCharacter: "Create Character",
                    createSession: "Create session",
                    manageSession: "Manage session",
                },
                content: {
                    createCharacter: "Use our character builder to join someone's adventure",
                    createSession: "Start tour own journey with friends",
                    manageSession: "Keep track of already existing sessions or create a new one without any complications",
                }
            },
            session: {
                create: {
                    name: "Session name",
                    success: "Session was created successfully!",
                    error: "Something went wrong during session creation :(",
                    addPlayer: "Add player",
                }
            },
            character: {
                create: {
                    class: "Class",
                    race: "Race",
                    background: "Background",
                    flaws: "Flaws",
                    bonds: "Bonds",
                    ideals: "Ideals",
                    traits: "Traits",
                    uploadImage: "Upload image",
                    abilities: "Abilities",
                    chooseRace: "Choose a Class",
                    characterName: "Character name",
                    selectAlignment: "Select alignment",
                    pointsLeft: "Points left: ",
                },
                alignment: {
                    lawfulGood: "Lawful Good",
                    neutralGood: "Neutral Good",
                    chaoticGood: "Chaotic Good",
                    lawfulNeutral: "Lawful Neutral",
                    chaoticNeutral: "Chaotic Neutral",
                    neutral: "Neutral",
                    lawfulEvil: "Lawful Evil",
                    neutralEvil: "Neutral Evil",
                    chaoticEvil: "Chaotic Evil"
                },
                stats: {
                    strength: "Strength",
                    dexterity: "Dexterity",
                    constitution: "Constitution",
                    intelligence: "Intelligence",
                    wisdom: "Wisdom",
                    charisma: "Charisma"
                }
            }
        },
        ua: {}
    }
})

export default i18n