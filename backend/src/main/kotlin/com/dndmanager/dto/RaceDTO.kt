package com.dndmanager.dto

data class RaceFindDTO(val id: Long, val name: String, val description: String?, val image: String?) : BaseFindDTO()

data class RaceGetDTO(val id: Long, val name: String, val description: String?, val image: String?) : BaseGetDTO()