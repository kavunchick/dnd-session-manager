package com.dndmanager.service

import com.dndmanager.domain.Equipment
import com.dndmanager.dto.EquipmentCreateDTO
import com.dndmanager.dto.EquipmentFindDTO
import com.dndmanager.dto.EquipmentGetDTO
import com.dndmanager.dto.EquipmentUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.eclipse.microprofile.jwt.JsonWebToken

@ApplicationScoped
class EquipmentService : BaseService<EquipmentCreateDTO, EquipmentGetDTO, EquipmentFindDTO, EquipmentUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): EquipmentGetDTO {
        val equipment = Equipment.findById(id) ?: throw NotFoundException()
        return converter.toGetDTO(equipment)
    }

    override fun getAll(user: JsonWebToken): List<EquipmentFindDTO> {
        val equipment = Equipment.listAll()
        return equipment.map { converter.toFindDTO(it) }
    }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        if (!Equipment.deleteById(id)) throw NotFoundException()
    }

    @Transactional
    override fun create(dto: EquipmentCreateDTO, user: JsonWebToken): EquipmentGetDTO {
        val equipment = converter.toEntity(dto);
        equipment.persistAndFlush()
        return converter.toGetDTO(equipment)
    }

    @Transactional
    override fun update(id: Long, dto: EquipmentUpdateDTO, user: JsonWebToken): EquipmentGetDTO {
        var equipment = Equipment.findById(id) ?: throw NotFoundException()
        equipment = converter.merge(equipment, dto)
        equipment.persistAndFlush()
        return converter.toGetDTO(equipment)
    }
}
