package com.dndmanager.service.additional

import com.dndmanager.domain.*
import com.dndmanager.dto.*
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken
import java.time.Instant
import kotlin.collections.mutableListOf

class ConverterService {
    // toEntity
    fun toEntity(abilityDTO: AbilityCreateDTO): Ability = abilityDTO.run {
        Ability(name, description)
    }

    fun toEntity(sessionCharacterDTO: SessionCharacterCreateDTO): SessionCharacter = sessionCharacterDTO.run {
        val character = Character.findById(characterId) ?: throw NotFoundException()
        SessionCharacter(
            Session.findById(sessionId) ?: throw NotFoundException(),
            character,
            emptyList(), mutableListOf(0, 0, 0, 0, 0), 1, 0,
            character.characterClass.hitPointDie,
            character.stats.toMutableList()
        )
    }

    fun toEntity(userDto: UserCreateDTO): User = userDto.run {
        User(username, sub, emptyList(), emptyList())
    }

    fun toEntity(user: User, characterDto: CharacterCreateDTO): Character = characterDto.run {
        Character(
            name, background, ideals, bonds, flaws, image, traits, alignment,
            Class.findById(classId) ?: throw NotFoundException(),
            Race.findById(raceId) ?: throw NotFoundException(), stats, user
//            RaceAbilityBonus.findById(raceAbilityId) ?: throw NotFoundException(), user
        )
    }

    fun toEntity(sessionCreatDTO: SessionCreatDTO, user: JsonWebToken): Session = sessionCreatDTO.run {
        Session(
            name,
            Instant.now(),
            null,
            mutableListOf(),
            User.find("sub", user.subject).firstResult() ?: throw NotFoundException()
        )
    }

    fun toEntity(equipmentCreateDTO: EquipmentCreateDTO): Equipment = equipmentCreateDTO.run {
        Equipment(name, description, suggestedPriceGp, weight)
    }

    fun toEntity(ciCreateDTO: CharacterInventoryCreateDTO): CharacterInventory = ciCreateDTO.run {
        CharacterInventory(
            amount,
            SessionCharacter.findById(character) ?: throw NotFoundException(),
            Equipment.findById(equipment) ?: throw NotFoundException()
        )
    }

    fun toEntity(npcCreateDTO: NpcCreateDTO): Npc = npcCreateDTO.run {
        Npc(
            name, description, health, alignment, Class.findById(npcClassId) ?: throw NotFoundException(),
            Race.findById(npcRaceId) ?: throw NotFoundException(),
            isHostile, role, locationId?.let { Location.findById(it) ?: throw NotFoundException() }
        )
    }

    // toGetDTO
    fun toGetDTO(ability: Ability): AbilityGetDTO = ability.run {
        AbilityGetDTO(id ?: -1, name, description)
    }

    fun toGetDTO(sessionCharacter: SessionCharacter): SessionCharacterGetDTO = sessionCharacter.run {
        SessionCharacterGetDTO(id ?: -1, toFindDTO(session), toGetDTO(character), level, experience, health, stats)
    }

    fun toGetDTO(user: User) = user.run {
        UserGetDTO(username, sub, characters.map { toFindDTO(it) }, sessions.map { toFindDTO(it) })
    }

    fun toGetDTO(session: Session): SessionGetDTO = session.run {
        SessionGetDTO(id ?: 0, name, start, lastUpdated, characters.map { toGetDTO(it) })
    }

    fun toGetDTO(character: Character): CharacterGetDTO = character.run {
        CharacterGetDTO(
            id ?: 0, name, background, ideals, bonds, flaws, imageURI, personalityTraits, alignment,
            toFindDTO(characterClass), stats, toFindDTO(race),
//            toFindDTO(abilityBonus)
        )
    }

    fun toGetDTO(equipment: Equipment): EquipmentGetDTO = equipment.run {
        EquipmentGetDTO(id ?: 0, name, description ?: "", suggestedPriceGp, weight)
    }

    fun toGetDTO(classEntity: Class): ClassGetDTO = classEntity.run {
        ClassGetDTO(id ?: 0, name, image, description ?: "", hitPointDie)
    }

    fun toGetDTO(ci: CharacterInventory): CharacterInventoryGetDTO = ci.run {
        CharacterInventoryGetDTO(id ?: 0, amount, toFindDTO(equipment))
    }

    fun toGetDTO(race: Race): RaceGetDTO = race.run { RaceGetDTO(id ?: 0, name, description, image) }

    fun toGetDTO(npc: Npc): NpcGetDTO = npc.run {
        NpcGetDTO(
            id ?: 0, name, description, health, alignment, toFindDTO(classField),
            toFindDTO(race), isHostile, role, location?.let { toFindDTO(it) }
        )
    }

    //    toFindDTO
    fun toFindDTO(ability: Ability): AbilityFindDTO =
        ability.run { AbilityFindDTO(id ?: 0, name) }

    fun toFindDTO(sessionCharacter: SessionCharacter): SessionCharacterFindDTO =
        sessionCharacter.run { SessionCharacterFindDTO(id ?: -1, toFindDTO(session), toFindDTO(character)) }

    fun toFindDTO(session: Session): SessionFindDTO =
        session.run { SessionFindDTO(id ?: 0, name, characters.map { toFindDTO(it.character) }, toFindDTO(author)) }

    fun toFindDTO(character: Character): CharacterFindDTO =
        character.run {
            CharacterFindDTO(
                id ?: 0,
                name,
                background,
                ideals,
                bonds,
                flaws,
                imageURI,
                personalityTraits,
                alignment,
                toFindDTO(characterClass),
                toFindDTO(race),
                stats
            )
        }

    fun toFindDTO(abilityBonus: RaceAbilityBonus): RaceAbilityBonusFindDTO =
        abilityBonus.run { RaceAbilityBonusFindDTO(id ?: 0, race.id ?: 0, ability.id ?: 0) }

    fun toFindDTO(race: Race): RaceFindDTO =
        race.run { RaceFindDTO(id ?: 0, name, description, image) }

    fun toFindDTO(classEntity: Class): ClassFindDTO =
        classEntity.run { ClassFindDTO(id ?: 0, name, description, image) }

    fun toFindDTO(equipment: Equipment): EquipmentFindDTO =
        equipment.run { EquipmentFindDTO(id ?: 0, name, weight) }

    fun toFindDTO(ci: CharacterInventory): CharacterInventoryFindDTO =
        ci.run { CharacterInventoryFindDTO(id ?: 0, amount, toFindDTO(equipment)) }

    fun toFindDTO(location: Location): LocationFindDTO =
        location.run { LocationFindDTO(id ?: 0, name, description) }

    fun toFindDTO(npc: Npc): NpcFindDTO =
        npc.run {
            NpcFindDTO(
                id ?: 0, name, alignment, toFindDTO(classField),
                toFindDTO(race), isHostile, role, location?.let { toFindDTO(it) })
        }

    fun toFindDTO(user: User): UserFindDTO = user.run { UserFindDTO(username, sub) }

    // merge
    fun merge(ability: Ability, abilityDTO: AbilityUpdateDTO) {
        ability.apply {
            name = abilityDTO.name ?: name
            description = abilityDTO.description ?: description
        }
    }

    fun merge(sessionCharacter: SessionCharacter, sessionCharacterDto: SessionCharacterUpdateDTO) {
        sessionCharacter.apply {
            level = sessionCharacterDto.level ?: level
            experience = sessionCharacterDto.experience ?: experience
            health = sessionCharacterDto.health ?: health
            stats = sessionCharacterDto.stats ?: stats
        }
    }

    fun merge(character: Character, characterDto: CharacterUpdateDTO) {
        character.apply {
            name = characterDto.name ?: name
            background = characterDto.background ?: background
            ideals = characterDto.ideals ?: ideals
            bonds = characterDto.bonds ?: bonds
            flaws = characterDto.flaws ?: flaws
            imageURI = characterDto.imageURI ?: imageURI
            personalityTraits = characterDto.personalityTraits ?: personalityTraits
            alignment = characterDto.alignment ?: alignment
            characterClass = characterDto.classId?.let { Class.findById(it) ?: throw NotFoundException() }
                ?: character.characterClass
            race = characterDto.raceId?.let { Race.findById(it) ?: throw NotFoundException() } ?: character.race
            stats = characterDto.stats ?: stats
//            characterDto.raceAbilityId?.let { RaceAbilityBonus.findById(it) ?: throw NotFoundException() }
//                ?: character.abilityBonus,
        }
    }

    fun merge(session: Session, sessionDto: SessionUpdateDTO) {
        session.apply {
            name = sessionDto.name
            lastUpdated = Instant.now()
        }
    }

    fun merge(equipment: Equipment, equipmentDto: EquipmentUpdateDTO) {
        equipment.apply {
            name = equipmentDto.name ?: name
            description = equipmentDto.description ?: description
            suggestedPriceGp = equipmentDto.suggestedPriceGp ?: suggestedPriceGp
            weight = equipmentDto.weight ?: weight
        }
    }

    fun merge(ci: CharacterInventory, ciDto: CharacterInventoryUpdateDTO) {
        ci.apply { amount = ciDto.amount }
    }

    fun merge(npc: Npc, npcDto: NpcUpdateDTO) {
        npc.apply {
            name = npcDto.name ?: name
            description = npcDto.description ?: description
            health = npcDto.health ?: health
            alignment = npcDto.alignment ?: alignment
            classField = npcDto.npcClassId?.let { Class.findById(it) ?: throw NotFoundException() } ?: classField
            race = npcDto.npcRaceId?.let { Race.findById(it) ?: throw NotFoundException() } ?: race
            isHostile = npcDto.isHostile ?: isHostile
            role = npcDto.role ?: role
            location = npcDto.locationId?.let { Location.findById(it) ?: throw NotFoundException() } ?: location
        }
    }
}