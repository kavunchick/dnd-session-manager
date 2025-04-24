package com.dndmanager.dto

data class SessionCharacterCreateDTO(
    val sessionId: Long,
    val characterId: Long,
) : BaseCreateDTO()

data class SessionCharacterUpdateDTO(
    val sessionId: Long?,
    val characterId: Long?,
) : BaseUpdateDTO()

data class SessionCharacterFindDTO(
    val id: Long,
    val session: SessionFindDTO,
    val character: CharacterFindDTO
) : BaseFindDTO()

data class SessionCharacterGetDTO(
    val id: Long,
    val session: SessionFindDTO,
    val character: CharacterFindDTO
) : BaseGetDTO()