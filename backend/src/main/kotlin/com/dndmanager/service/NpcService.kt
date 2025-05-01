package com.dndmanager.service

import com.dndmanager.domain.Npc
import com.dndmanager.dto.NpcCreateDTO
import com.dndmanager.dto.NpcFindDTO
import com.dndmanager.dto.NpcGetDTO
import com.dndmanager.dto.NpcUpdateDTO
import com.dndmanager.service.additional.ConverterService
import io.quarkus.panache.common.Sort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class NpcService : BaseService<NpcCreateDTO, NpcGetDTO, NpcFindDTO, NpcUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): NpcGetDTO =
        Npc.findById(id)?.let { converter.toGetDTO(it) } ?: throw NotFoundException()

    override fun getAll(user: JsonWebToken): List<NpcFindDTO> =
        Npc.listAll(Sort.by("name")).map { converter.toFindDTO(it) }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val npc =  Npc.findById(id) ?: throw NotFoundException()
        if (!npc.isTrusted(user.subject)) throw ForbiddenException()
        npc.delete()
    }

    @Transactional
    override fun create(dto: NpcCreateDTO, user: JsonWebToken): NpcGetDTO {
        val entity = converter.toEntity(dto)
        entity.persistAndFlush()
        return converter.toGetDTO(entity)
    }

    @Transactional
    override fun update(id: Long, dto: NpcUpdateDTO, user: JsonWebToken): NpcGetDTO {
        val npc = Npc.findById(id) ?: throw NotFoundException()
        if (!npc.isTrusted(user.subject)) throw ForbiddenException()
        converter.merge(npc, dto)
        return converter.toGetDTO(npc)
    }
}