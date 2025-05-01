package com.dndmanager.dto

import com.dndmanager.domain.helper.Alignment

data class CharacterCreateDTO(
    val name: String,
    val background: String?,
    val ideals: String?,
    val bonds: String?,
    val flaws: String?,
    val image: String?,
    val alignment: Alignment,
    val classId: Long,
    val raceId: Long,
    val traits: String?,
    val stats: MutableList<Int>,
//    val raceAbilityId: Long,
    ) : BaseCreateDTO()

data class CharacterUpdateDTO(
    val name: String?,
    val background: String?,
    val ideals: String?,
    val bonds: String?,
    val flaws: String?,
    val imageURI: String?,
    val traits: String?,
    val alignment: Alignment?,
    val classId: Long?,
    val raceId: Long?,
    val stats: MutableList<Int>?,
//    val raceAbilityId: Long?
) : BaseUpdateDTO()

data class CharacterFindDTO(
    val id: Long,
    val name: String,
    val background: String?,
    val ideals: String?,
    val bonds: String?,
    val flaws: String?,
    val imageURI: String?,
    val traits: String?,
    val alignment: Alignment,
    val characterClass: ClassFindDTO,
    val characterRace: RaceFindDTO,
    val stats: MutableList<Int>,
) : BaseFindDTO()

data class CharacterGetDTO(
    val id: Long,
    val name: String,
    val background: String?,
    val ideals: String?,
    val bonds: String?,
    val flaws: String?,
    val imageURI: String?,
    val traits: String?,
    val alignment: Alignment,
    val characterClass: ClassFindDTO,
    val stats: MutableList<Int>,
    val characterRace: RaceFindDTO,
//    val raceAbilityBonus: RaceAbilityBonusFindDTO
) : BaseGetDTO()