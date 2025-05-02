package com.dndmanager.controller.auth

import com.dndmanager.dto.UserCreateDTO
import com.dndmanager.dto.UserGetDTO
import com.dndmanager.service.UserService
import io.quarkus.security.Authenticated
import jakarta.inject.Inject
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import org.eclipse.microprofile.jwt.JsonWebToken


@Path("/login")
@Authenticated
class Auth {

    @Inject
    lateinit var service: UserService

    @Inject
    lateinit var jsonWebToken: JsonWebToken

    @PUT
    fun createUser(): UserGetDTO { return service.create(UserCreateDTO(jsonWebToken.getClaim("name"), jsonWebToken.subject), jsonWebToken) }

}
