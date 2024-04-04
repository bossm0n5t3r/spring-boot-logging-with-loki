package me.bossm0n5t3r.loki.application.dto

import me.bossm0n5t3r.loki.presentation.dto.UserControllerDto

data class UserApplicationDto(
    val id: Long,
    var firstName: String,
    var lastName: String,
    var age: Int,
) {
    fun toUserControllerDto(): UserControllerDto {
        return UserControllerDto(
            id = id,
            firstName = firstName,
            lastName = lastName,
            age = age,
        )
    }
}
