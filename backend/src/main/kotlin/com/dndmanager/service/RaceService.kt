package com.dndmanager.service

import com.dndmanager.domain.Race
import com.dndmanager.dto.RaceFindDTO
import com.dndmanager.dto.RaceGetDTO
import com.dndmanager.service.additional.ConverterService
import io.quarkus.panache.common.Sort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class RaceService : BaseService<Nothing, RaceGetDTO, RaceFindDTO, Nothing> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): RaceGetDTO =
        Race.findById(id)?.let { converter.toGetDTO(it) } ?: throw NotFoundException()

    override fun getAll(user: JsonWebToken): List<RaceFindDTO> =
        Race.listAll(Sort.by("name")).map(converter::toFindDTO)

    override fun delete(id: Long, user: JsonWebToken) =
        throw WebApplicationException(Response.Status.NOT_IMPLEMENTED)


    override fun create(dto: Nothing, user: JsonWebToken) =
        throw WebApplicationException(Response.Status.NOT_IMPLEMENTED)


    override fun update(id: Long, dto: Nothing, user: JsonWebToken) =
        throw WebApplicationException(Response.Status.NOT_IMPLEMENTED)

}