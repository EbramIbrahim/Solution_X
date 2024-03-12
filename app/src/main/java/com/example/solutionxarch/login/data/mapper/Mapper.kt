package com.example.solutionxarch.login.data.mapper

interface Mapper<DOMAIN, DATA, ENTITY> {
    fun toDomain(data: DATA): DOMAIN
    fun toData(domain: DOMAIN): DATA
    fun toEntity(domain: DOMAIN): ENTITY

    fun fromEntityToDomain(entity: ENTITY): DOMAIN
}