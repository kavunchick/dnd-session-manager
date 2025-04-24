package com.dndmanager.controller

import com.dndmanager.dto.BaseCreateDTO
import com.dndmanager.dto.BaseFindDTO
import com.dndmanager.dto.BaseGetDTO
import com.dndmanager.dto.BaseUpdateDTO
import com.dndmanager.service.BaseService
import jakarta.inject.Inject
import jakarta.ws.rs.*
import org.eclipse.microprofile.jwt.JsonWebToken
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody


abstract class BaseControllerImp<
        S : BaseService<C, G, F, U>,
        C : BaseCreateDTO,
        U : BaseUpdateDTO,
        G : BaseGetDTO,
        F : BaseFindDTO> : BaseController<C, U, G, F> {

    abstract val service: S

    @Inject
    lateinit var jsonWebToken: JsonWebToken

    @GET
    override fun getAll(): List<F> = service.getAll(jsonWebToken)

    @GET
    @Path("/{id}")
    override fun getById(id: Long): G = service.getById(id, jsonWebToken)

    @PATCH
    @Path("/{id}")
    override fun update(id: Long, updateDTO: U): G = service.update(id, updateDTO, jsonWebToken)

    @DELETE
    @Path("/{id}")
    override fun delete(id: Long) = service.delete(id, jsonWebToken)

    @PUT
    override fun create(@RequestBody createDTO: C): G = service.create(createDTO, jsonWebToken)
}