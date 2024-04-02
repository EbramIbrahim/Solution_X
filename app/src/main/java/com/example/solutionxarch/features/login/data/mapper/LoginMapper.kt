package com.example.solutionxarch.features.login.data.mapper

import com.example.solutionxarch.core.data.mapper.Mapper
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto
import com.example.solutionxarch.features.login.domain.models.User

object LoginMapper: Mapper<User, UserLoginDto, UserEntity> {

    override fun toDomain(data: UserLoginDto): User {
        return User(
            username = data.userDto?.username.orEmpty(),
            token = data.token.orEmpty(),
            email = data.userDto?.email.orEmpty(),
            id = data.userDto?.id ?: -1
        )
    }

    override fun toData(domain: User): UserLoginDto {
       TODO()
    }

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            username = domain.username,
            token = domain.token,
            email = domain.email,
            id = domain.id
        )
    }

    override fun fromEntityToDomain(entity: UserEntity): User {
        return User(
            username = entity.username,
            token = entity.token,
            email = entity.email,
            id = entity.id
        )
    }
}