package com.dndmanager.service

import com.dndmanager.domain.Equipment
import com.dndmanager.dto.EquipmentCreateDTO
import com.dndmanager.dto.EquipmentFindDTO
import com.dndmanager.dto.EquipmentGetDTO
import com.dndmanager.dto.EquipmentUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class EquipmentService : BaseService<EquipmentCreateDTO, EquipmentGetDTO, EquipmentFindDTO, EquipmentUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): EquipmentGetDTO =
        Equipment.findById(id)?.let { converter.toGetDTO(it) } ?: throw NotFoundException()

    override fun getAll(user: JsonWebToken): List<EquipmentFindDTO> =
        Equipment.listAll().map { converter.toFindDTO(it) }


    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        val equipment = Equipment.findById(id) ?: throw NotFoundException()
        if (!equipment.isTrusted(user.subject)) throw ForbiddenException()
        equipment.delete()
    }

    @Transactional
    override fun create(dto: EquipmentCreateDTO, user: JsonWebToken): EquipmentGetDTO {
        val equipment = converter.toEntity(dto)
        equipment.persistAndFlush()
        return converter.toGetDTO(equipment)
    }

    @Transactional
    override fun update(id: Long, dto: EquipmentUpdateDTO, user: JsonWebToken): EquipmentGetDTO {
        val equipment = Equipment.findById(id) ?: throw NotFoundException()
        if (!equipment.isTrusted(user.subject)) throw ForbiddenException()
        converter.merge(equipment, dto)
        return converter.toGetDTO(equipment)
    }
}
