package com.dndmanager.controller

import com.dndmanager.dto.SessionCharacterCreateDTO
import com.dndmanager.dto.SessionCharacterFindDTO
import com.dndmanager.dto.SessionCharacterGetDTO
import com.dndmanager.dto.SessionCharacterUpdateDTO
import com.dndmanager.service.SessionCharacterService
import jakarta.inject.Inject
import jakarta.ws.rs.Path

@Path("/session/character")
class SessionCharacterController :
    BaseControllerImp<SessionCharacterService, SessionCharacterCreateDTO, SessionCharacterUpdateDTO, SessionCharacterGetDTO, SessionCharacterFindDTO>() {

    @Inject
    override lateinit var service: SessionCharacterService
}