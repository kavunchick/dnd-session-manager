package com.dndmanager.service

import com.dndmanager.domain.Ability
import com.dndmanager.dto.AbilityCreateDTO
import com.dndmanager.dto.AbilityFindDTO
import com.dndmanager.dto.AbilityGetDTO
import com.dndmanager.dto.AbilityUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class AbilityService :
    BaseService<AbilityCreateDTO, AbilityGetDTO, AbilityFindDTO, AbilityUpdateDTO> {

    override val converter = ConverterService()

    @Transactional
    override fun create(dto: AbilityCreateDTO, user: JsonWebToken): AbilityGetDTO {
        val ability = converter.toEntity(dto)
        ability.persistAndFlush()
        return converter.toGetDTO(ability)
    }

    @Transactional
    override fun update(id: Long, dto: AbilityUpdateDTO, user: JsonWebToken): AbilityGetDTO {
        val ability = Ability.findById(id) ?: throw NotFoundException()
        if (!ability.isTrusted(user.subject)) throw ForbiddenException()
        converter.merge(ability, dto)
        return converter.toGetDTO(ability)
    }

    override fun getById(id: Long, user: JsonWebToken): AbilityGetDTO =
        Ability.findById(id)?.let { converter.toGetDTO(it) } ?: throw NotFoundException()

    override fun getAll(user: JsonWebToken): List<AbilityFindDTO> =
        Ability.listAll().map { converter.toFindDTO(it) }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val ability = Ability.findById(id) ?: throw NotFoundException()
        if (!ability.isTrusted(user.subject)) throw ForbiddenException()
        ability.delete()
    }
}