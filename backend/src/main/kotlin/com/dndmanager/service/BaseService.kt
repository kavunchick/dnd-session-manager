package com.dndmanager.service

import com.dndmanager.dto.BaseCreateDTO
import com.dndmanager.dto.BaseFindDTO
import com.dndmanager.dto.BaseGetDTO
import com.dndmanager.dto.BaseUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.transaction.Transactional
import org.eclipse.microprofile.jwt.JsonWebToken

interface BaseService< C: BaseCreateDTO, G: BaseGetDTO, F: BaseFindDTO, U: BaseUpdateDTO> {

    val converter: ConverterService

    @Transactional
    fun getById(id: Long, user: JsonWebToken) : G

    @Transactional
    fun getAll(user: JsonWebToken) : List<F>

    @Transactional
    fun update(id: Long, dto: U, user: JsonWebToken) : G

    @Transactional
    fun create(dto: C, user: JsonWebToken) : G

    @Transactional
    fun delete(id: Long, user: JsonWebToken)
}