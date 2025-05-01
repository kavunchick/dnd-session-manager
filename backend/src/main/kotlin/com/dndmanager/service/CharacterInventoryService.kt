package com.dndmanager.service

import com.dndmanager.domain.CharacterInventory
import com.dndmanager.domain.Equipment
import com.dndmanager.domain.SessionCharacter
import com.dndmanager.dto.CharacterInventoryCreateDTO
import com.dndmanager.dto.CharacterInventoryFindDTO
import com.dndmanager.dto.CharacterInventoryGetDTO
import com.dndmanager.dto.CharacterInventoryUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class CharacterInventoryService :
    BaseService<CharacterInventoryCreateDTO, CharacterInventoryGetDTO, CharacterInventoryFindDTO, CharacterInventoryUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): CharacterInventoryGetDTO =
        CharacterInventory.findById(id)?.let { converter.toGetDTO(it) } ?: throw NotFoundException()

    override fun getAll(user: JsonWebToken): List<CharacterInventoryFindDTO> =
        CharacterInventory.listAll().map { converter.toFindDTO(it) }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val entry = CharacterInventory.findById(id) ?: throw NotFoundException()
        if (!entry.isTrusted(user.subject)) throw ForbiddenException()
        entry.delete()
    }

    @Transactional
    override fun create(dto: CharacterInventoryCreateDTO, user: JsonWebToken): CharacterInventoryGetDTO {
        if (Equipment.count("id", dto.equipment) == 0L ||
            SessionCharacter.count("id", dto.character) == 0L
        ) throw NotFoundException()
        val ciEntity = converter.toEntity(dto)
        ciEntity.persistAndFlush()
        return converter.toGetDTO(ciEntity)
    }

    @Transactional
    override fun update(id: Long, dto: CharacterInventoryUpdateDTO, user: JsonWebToken): CharacterInventoryGetDTO {
        val entry = CharacterInventory.findById(id) ?: throw NotFoundException()
        if (!entry.isTrusted(user.subject)) throw ForbiddenException()
        converter.merge(entry, dto)
        val res = converter.toGetDTO(entry)
        if (entry.amount == 0) entry.delete()
        return res
    }

    fun getAllByCharacter(id: Long): List<CharacterInventoryFindDTO> =
        CharacterInventory.list("character", id).map { converter.toFindDTO(it) }
}