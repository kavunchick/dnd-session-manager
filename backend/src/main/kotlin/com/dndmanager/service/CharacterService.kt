package com.dndmanager.service

import com.dndmanager.domain.*
import com.dndmanager.dto.CharacterCreateDTO
import com.dndmanager.dto.CharacterFindDTO
import com.dndmanager.dto.CharacterGetDTO
import com.dndmanager.dto.CharacterUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class CharacterService : BaseService<CharacterCreateDTO, CharacterGetDTO, CharacterFindDTO, CharacterUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): CharacterGetDTO {
        val character = Character.findById(id) ?: throw NotFoundException()
        return converter.toGetDTO(character)
    }

    override fun getAll(user: JsonWebToken): List<CharacterFindDTO> {
        return Character.listAll().map { converter.toFindDTO(it) }
    }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val character = Character.findById(id) ?: throw NotFoundException()
        character.delete()
    }

    @Transactional
    override fun create(dto: CharacterCreateDTO, user: JsonWebToken): CharacterGetDTO {
        if (Race.findById(dto.raceId) == null) throw NotFoundException()
        if (Class.findById(dto.classId) == null) throw NotFoundException()
        if (RaceAbilityBonus.findById(dto.raceAbilityId) == null) throw NotFoundException()
        val user: User = User.find("sub = ?1", dto.createdBy).firstResult() ?: throw NotFoundException()
        val character = converter.toEntity(user, dto)
        character.persistAndFlush()
        return converter.toGetDTO(character)
    }

    @Transactional
    override fun update(id: Long, dto: CharacterUpdateDTO, user: JsonWebToken): CharacterGetDTO {
        var character = Character.findById(id) ?: throw NotFoundException()
        character = converter.merge(character, dto)
        character.persistAndFlush()
        return converter.toGetDTO(character)
    }
}