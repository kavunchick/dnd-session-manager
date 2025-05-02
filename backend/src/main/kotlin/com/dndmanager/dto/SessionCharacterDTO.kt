package com.dndmanager.dto

data class SessionCharacterCreateDTO(
    val sessionId: Long,
    val characterId: Long,
) : BaseCreateDTO()

data class SessionCharacterUpdateDTO(
    val level: Short?,
    val experience: Short?,
    val health: Short?,
    val stats: MutableList<Int>?,
) : BaseUpdateDTO()

data class SessionCharacterFindDTO(
    val id: Long,
    val session: SessionFindDTO,
    val character: CharacterFindDTO
) : BaseFindDTO()

data class SessionCharacterGetDTO(
    val id: Long,
    val session: SessionFindDTO,
    val character: CharacterGetDTO,
    val level: Short,
    val experience: Short,
    val health: Short,
    val stats: List<Int>,
) : BaseGetDTO()