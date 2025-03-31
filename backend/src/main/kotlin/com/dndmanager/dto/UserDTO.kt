package com.dndmanager.dto

data class UserCreateDTO(val username: String, val sub: String) : BaseCreateDTO()

data class UserUpdateDTO(val username: String?, val sub: String?) : BaseUpdateDTO()

data class UserGetDTO(
    val username: String,
    val sub: String,
    val characters: List<CharacterFindDTO>,
    val session: List<SessionFindDTO>
) : BaseGetDTO()

data class UserFindDTO(val username: String, val sub: String) : BaseFindDTO()