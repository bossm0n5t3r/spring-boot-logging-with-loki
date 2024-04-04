package me.bossm0n5t3r.loki.presentation.dto

import me.bossm0n5t3r.loki.application.dto.UserApplicationDto

data class CreateUserDto(
    val firstName: String,
    val lastName: String,
    val age: Int,
) {
    fun toUserApplicationDto(id: Long): UserApplicationDto {
        return UserApplicationDto(
            id = id,
            firstName = firstName,
            lastName = lastName,
            age = age,
        )
    }
}
