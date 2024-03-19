package com.example.solutionxarch.login.data.mapper

import com.example.solutionxarch.login.data.models.UserEntity
import com.example.solutionxarch.login.data.models.UserDto
import com.example.solutionxarch.login.domain.models.User

object LoginMapper:  Mapper<User, UserDto, UserEntity>{

    override fun toDomain(data: UserDto): User {
        return User(
            username = data.username,
            token = data.token
        )
    }

    override fun toData(domain: User): UserDto {
        return UserDto(
            username = domain.username,
            token = domain.token
        )
    }

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            username = domain.username,
            token = domain.token
        )
    }

    override fun fromEntityToDomain(entity: UserEntity): User {
        return User(
            username = entity.username,
            token = entity.token
        )
    }
}