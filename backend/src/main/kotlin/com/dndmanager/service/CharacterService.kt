package com.dndmanager.service

import com.dndmanager.domain.*
import com.dndmanager.dto.CharacterCreateDTO
import com.dndmanager.dto.CharacterFindDTO
import com.dndmanager.dto.CharacterGetDTO
import com.dndmanager.dto.CharacterUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.Locale.getDefault

@ApplicationScoped
class CharacterService : BaseService<CharacterCreateDTO, CharacterGetDTO, CharacterFindDTO, CharacterUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): CharacterGetDTO =
        converter.toGetDTO(Character.findById(id) ?: throw NotFoundException())

    override fun getAll(user: JsonWebToken): List<CharacterFindDTO> =
        Character.listAll().map { converter.toFindDTO(it) }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val character = Character.findById(id) ?: throw NotFoundException()
        if (!character.isTrusted(user.subject)) throw ForbiddenException()
        character.delete()
    }

    @Transactional
    override fun create(dto: CharacterCreateDTO, user: JsonWebToken): CharacterGetDTO {
        if (Race.findById(dto.raceId) == null) throw NotFoundException()
        if (Class.findById(dto.classId) == null) throw NotFoundException()
//        if (RaceAbilityBonus.findById(dto.raceAbilityId) == null) throw NotFoundException()
        val user: User = User.find("sub = ?1", user.name).firstResult() ?: throw NotFoundException()
        val character = converter.toEntity(user, dto)
        character.persistAndFlush()
        return converter.toGetDTO(character)
    }

    @Transactional
    override fun update(id: Long, dto: CharacterUpdateDTO, user: JsonWebToken): CharacterGetDTO {
        val character = Character.findById(id) ?: throw NotFoundException()
        if (!character.isTrusted(user.subject)) throw ForbiddenException()
        converter.merge(character, dto)
        return converter.toGetDTO(character)
    }

    fun findByNameAndUsername(name: String, userSub: String): List<CharacterFindDTO> {
        val user: User = User.find("sub = ?1", userSub).firstResult() ?: throw NotFoundException()
        val res = Character.list("createdBy = ?1 and lower(name) LIKE ?2", user, name.lowercase(getDefault()) + "%")
            .map { converter.toFindDTO(it) }
        return res
    }
}