package com.dndmanager.controller

import com.dndmanager.dto.CharacterCreateDTO
import com.dndmanager.dto.CharacterFindDTO
import com.dndmanager.dto.CharacterGetDTO
import com.dndmanager.dto.CharacterUpdateDTO
import com.dndmanager.service.CharacterService
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam

@Path("/characters")
class CharacterController :
    BaseControllerImp<CharacterService, CharacterCreateDTO, CharacterUpdateDTO, CharacterGetDTO, CharacterFindDTO>() {

    @Inject
    override lateinit var service: CharacterService

    @Path("/like")
    @GET
    fun findByNameAndUsername(
        @QueryParam("name") name: String, @QueryParam("sub") userSub: String
    ): List<CharacterFindDTO> = service.findByNameAndUsername(name, userSub)
}