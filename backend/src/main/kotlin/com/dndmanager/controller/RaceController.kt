package com.dndmanager.controller

import com.dndmanager.dto.RaceFindDTO
import com.dndmanager.dto.RaceGetDTO
import com.dndmanager.service.RaceService
import jakarta.inject.Inject
import jakarta.ws.rs.Path

@Path("/races")
class RaceController: BaseControllerImp<RaceService, Nothing, Nothing, RaceGetDTO, RaceFindDTO>() {
    @Inject
    override lateinit var service: RaceService
}