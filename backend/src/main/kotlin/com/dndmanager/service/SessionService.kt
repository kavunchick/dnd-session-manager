package com.dndmanager.service

import com.dndmanager.domain.Session
import com.dndmanager.dto.SessionCreatDTO
import com.dndmanager.dto.SessionFindDTO
import com.dndmanager.dto.SessionGetDTO
import com.dndmanager.dto.SessionUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class SessionService : BaseService<SessionCreatDTO, SessionGetDTO, SessionFindDTO, SessionUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): SessionGetDTO {
        val session = Session.findById(id) ?: throw NotFoundException()
        return converter.toGetDTO(session)
    }

    override fun getAll(user: JsonWebToken): List<SessionFindDTO> {
        val sessions = Session.list("author.sub = ?1", user.subject)
        return sessions.map { converter.toFindDTO(it) }
    }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val session = Session.findById(id) ?: throw NotFoundException()
        if (!session.isTrusted(user.subject)) throw ForbiddenException()
        session.delete()
    }

    @Transactional
    override fun create(dto: SessionCreatDTO, user: JsonWebToken): SessionGetDTO {
        val session = converter.toEntity(dto, user)
        session.persistAndFlush()
        return converter.toGetDTO(session)
    }

    @Transactional
    override fun update(id: Long, dto: SessionUpdateDTO, user: JsonWebToken): SessionGetDTO {
        val session = Session.findById(id) ?: throw NotFoundException()
        if (!session.isTrusted(user.subject)) throw ForbiddenException()
        converter.merge(session, dto)
        return converter.toGetDTO(session)
    }
}