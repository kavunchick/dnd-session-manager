package com.dndmanager.dto

data class RaceFindDTO(val id: Long, val name: String, val description: String?) : BaseFindDTO()

data class RaceGetDTO(val id: Long, val name: String, val description: String?) : BaseGetDTO()