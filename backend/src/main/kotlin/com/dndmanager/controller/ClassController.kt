package com.dndmanager.controller

import com.dndmanager.dto.*
import com.dndmanager.service.ClassService
import jakarta.inject.Inject
import jakarta.ws.rs.Path

@Path("/classes")
class ClassController: BaseControllerImp<ClassService, ClassCreateDTO, ClassUpdateDTO, ClassGetDTO, ClassFindDTO>() {

    @Inject
    override lateinit var service: ClassService
}