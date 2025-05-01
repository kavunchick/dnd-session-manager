import {createI18n} from "vue-i18n";

const i18n = createI18n({
    locale: 'en',
    fallbackLocale: 'en',
    messages: {
        en: {
            general: {
                save: "Save",
                delete: "Delete",
                cancel: "Cancel",
                success: "Success!",
                error: "Error",
                next: "Next",
                back: "Back",
                submit: "Submit",
                edit: "Edit",
                manage: "Manage",
                confirmed: "Confirmed",
                rejected: "Rejected",
            },
            menu: {
                titles: {
                    listCharacter: "Character list",
                    createSession: "Create session",
                    manageSession: "Manage session",
                },
                content: {
                    listCharacter: "See your characters or build a new one to join someone's adventure",
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
                },
                deleteMessage: "Are you sure you want to delete this session?",
                deleteSuccess: "Session was deleted successfully!",
                deleteError: "Something went wrong during session deletion :(",
                deleteRejection: "You have rejected the deletion of the session",
            },
            character: {
                deleteConflict: "You can't delete this character, because it is already in use",
                deleteError: "Something went wrong during session deletion :(",
                deleteSuccess: "Character successfully deleted",
                updateSuccess: "Character successfully updated",
                updateError: "Something went wrong during character update :(",
                createButton: "Create character",
                create: {
                    class: "Class",
                    race: "Race",
                    background: "Background",
                    flaws: "Flaws",
                    bonds: "Bonds",
                    ideals: "Ideals",
                    traits: "Traits",
                    uploadImage: "Upload image ",
                    abilities: "Abilities",
                    chooseRace: "Choose a Class",
                    characterName: "Character name",
                    selectAlignment: "Select alignment",
                    pointsLeft: "Points left: ",
                },
                card: {
                    level: "Level: ",
                    exp: "Experience: ",
                    health: "Health: ",
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
            },
            sessionCharacter: {
                updateMessage: "Player was updated successfully",
                updateError: "Something went wrong during player update :(",
                deleteMessage: "Player was removed from session successfully",
                deleteError: "Something went wrong during player removal :(",
            }
        },
    }
})

export default i18n