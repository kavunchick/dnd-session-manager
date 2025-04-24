package com.dndmanager.service

import com.dndmanager.domain.User
import com.dndmanager.dto.UserCreateDTO
import com.dndmanager.dto.UserFindDTO
import com.dndmanager.dto.UserGetDTO
import com.dndmanager.dto.UserUpdateDTO
import com.dndmanager.service.additional.ConverterService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.Locale.getDefault

@ApplicationScoped
class UserService : BaseService<UserCreateDTO, UserGetDTO, UserFindDTO, UserUpdateDTO> {

    override val converter = ConverterService()

    override fun getById(id: Long, user: JsonWebToken): UserGetDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(user: JsonWebToken): List<UserFindDTO> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun delete(id: Long, user: JsonWebToken) {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun create(dto: UserCreateDTO, user: JsonWebToken): UserGetDTO {
        val existingUser = User.find("sub", dto.sub).firstResult()
        return if (existingUser != null) {
            existingUser.username = dto.username
            existingUser.persistAndFlush()
            converter.toGetDTO(existingUser)
        } else {
            val user = converter.toEntity(dto)
            user.persistAndFlush()
            converter.toGetDTO(user)
        }
    }

    @Transactional
    override fun update(id: Long, dto: UserUpdateDTO, user: JsonWebToken): UserGetDTO {
        TODO("Not yet implemented")
    }

    fun findByUsername(username: String): List<UserFindDTO> =
        User.list("lower(username) LIKE ?1", username.lowercase(getDefault()) + "%")
            .map { converter.toFindDTO(it) }
}