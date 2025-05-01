package com.dndmanager.service

import com.dndmanager.domain.Class
import com.dndmanager.dto.ClassCreateDTO
import com.dndmanager.dto.ClassFindDTO
import com.dndmanager.dto.ClassGetDTO
import com.dndmanager.dto.ClassUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class ClassService : BaseService<ClassCreateDTO, ClassGetDTO, ClassFindDTO, ClassUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): ClassGetDTO =
        Class.findById(id)?.let { converter.toGetDTO(it) } ?: throw NotFoundException()

    override fun getAll(user: JsonWebToken): List<ClassFindDTO> = Class.listAll().map(converter::toFindDTO)

    override fun delete(id: Long, user: JsonWebToken) =
        throw WebApplicationException(Response.Status.NOT_IMPLEMENTED)

    override fun create(dto: ClassCreateDTO, user: JsonWebToken): ClassGetDTO =
        throw WebApplicationException(Response.Status.NOT_IMPLEMENTED)


    override fun update(id: Long, dto: ClassUpdateDTO, user: JsonWebToken): ClassGetDTO =
        throw WebApplicationException(Response.Status.NOT_IMPLEMENTED)

}