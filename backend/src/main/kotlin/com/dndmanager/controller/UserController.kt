package com.dndmanager.controller

import com.dndmanager.dto.UserCreateDTO
import com.dndmanager.dto.UserFindDTO
import com.dndmanager.dto.UserGetDTO
import com.dndmanager.dto.UserUpdateDTO
import com.dndmanager.service.UserService
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam

@Path("/users")
class UserController: BaseControllerImp<UserService, UserCreateDTO, UserUpdateDTO, UserGetDTO, UserFindDTO>() {

    @Inject
    override lateinit var service: UserService

    @Path("/like")
    @GET
    fun findByUsername(@QueryParam("username") username: String): List<UserFindDTO> = service.findByUsername(username)
}