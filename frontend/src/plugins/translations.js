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
                back: "Back"
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
                createSession: "Create session",
                create: {
                    name: "Session name",
                    success: "Session was created successfully!",
                    error: "Something went wrong during session creation :("
                }
            },
            character: {
                create: {
                    class: "Class",
                    race: "Race",
                    background: "Background",
                    abilities: "Abilities",
                    chooseRace: "Choose a Class"
                },
                alignment: {
                    lawfulGood: "Lawful Good",
                    neutralGood: "Neutral Good",
                    chaoticGood: "Chaotic Good",
                    lawfulNeutral: "Lawful Neutral",
                    trueNeutral: "True Neutral",
                    chaoticNeutral: "Chaotic Neutral",
                    lawfulEvil: "Lawful Evil",
                    neutralEvil: "Neutral Evil",
                    chaoticEvil: "Chaotic Evil"
                }
            }
        },
        ua: {}
    }
})

export default i18n