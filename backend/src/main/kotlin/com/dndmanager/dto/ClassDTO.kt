package com.dndmanager.dto

data class ClassFindDTO(val id: Long, val name: String, val description: String?, val image: String?) : BaseFindDTO()

data class ClassUpdateDTO(val name: String?, val description: String?, val hitPoint: Short?) : BaseUpdateDTO()

data class ClassCreateDTO(val id: Long, val name: String, val description: String?, val hitPoint: Short) :
    BaseCreateDTO()

data class ClassGetDTO(
    val id: Long,
    val name: String,
    val image: String?,
    val description: String?,
    val hitPoint: Short
) : BaseGetDTO()
