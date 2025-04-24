package com.dndmanager.service

import com.dndmanager.domain.Character
import com.dndmanager.domain.Session
import com.dndmanager.domain.SessionCharacter
import com.dndmanager.domain.User
import com.dndmanager.dto.SessionCharacterCreateDTO
import com.dndmanager.dto.SessionCharacterFindDTO
import com.dndmanager.dto.SessionCharacterGetDTO
import com.dndmanager.dto.SessionCharacterUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityExistsException
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class SessionCharacterService :
    BaseService<SessionCharacterCreateDTO, SessionCharacterGetDTO, SessionCharacterFindDTO, SessionCharacterUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): SessionCharacterGetDTO =
        converter.toGetDTO(SessionCharacter.findById(id) ?: throw NotFoundException())

    override fun getAll(user: JsonWebToken): List<SessionCharacterFindDTO> =
        SessionCharacter.listAll().map { converter.toFindDTO(it) }

    @Transactional
    override fun update(id: Long, dto: SessionCharacterUpdateDTO, user: JsonWebToken): SessionCharacterGetDTO {
        val userEntity = User.find(user.subject)
        val sessionCharacter = SessionCharacter.findById(id) ?: throw NotFoundException()
        if (sessionCharacter.session.author != userEntity) throw ForbiddenException()
        dto.sessionId?.let {
            sessionCharacter.session = Session.findById(it) ?: throw NotFoundException()
            if (sessionCharacter.session.author != userEntity) throw ForbiddenException()
        }
        dto.characterId?.let { sessionCharacter.character = Character.findById(it) ?: throw NotFoundException() }
        sessionCharacter.persistAndFlush()
        return converter.toGetDTO(sessionCharacter)
    }

    @Transactional
    override fun create(dto: SessionCharacterCreateDTO, user: JsonWebToken): SessionCharacterGetDTO {
        if (!SessionCharacter.list("session.id = ?1 and character.id = ?2", dto.sessionId, dto.characterId)
                .isEmpty()
        ) throw EntityExistsException()
        println("in end point")
        return converter.toEntity(dto).apply { persistAndFlush() }.let(converter::toGetDTO)
    }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val sessionCharacter = SessionCharacter.findById(id) ?: throw NotFoundException()
        if (sessionCharacter.session.author.sub != user.subject) throw ForbiddenException()
        sessionCharacter.delete()
    }
}