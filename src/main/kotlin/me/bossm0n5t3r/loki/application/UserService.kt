package me.bossm0n5t3r.loki.application

import me.bossm0n5t3r.loki.application.dto.UserApplicationDto
import me.bossm0n5t3r.loki.configuration.LOGGER
import me.bossm0n5t3r.loki.enumeration.Result
import me.bossm0n5t3r.loki.presentation.dto.CreateUserDto
import me.bossm0n5t3r.loki.presentation.dto.UpdateUserDto
import org.springframework.stereotype.Service

@Service
class UserService {
    private val users =
        mutableListOf(
            UserApplicationDto(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                age = 30,
            ),
            UserApplicationDto(
                id = 2,
                firstName = "Jane",
                lastName = "Doe",
                age = 25,
            ),
            UserApplicationDto(
                id = 3,
                firstName = "Bob",
                lastName = "Smith",
                age = 40,
            ),
            UserApplicationDto(
                id = 4,
                firstName = "Alice",
                lastName = "Smith",
                age = 35,
            ),
            UserApplicationDto(
                id = 5,
                firstName = "Charlie",
                lastName = "Johnson",
                age = 50,
            ),
        )

    fun getAllUsers(): List<UserApplicationDto> {
        return users.also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserService.getAllUsers", it)
        }
    }

    fun getUserById(id: Long): UserApplicationDto? {
        return users.find { it.id == id }
            .also { LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserService.getUserById", it) }
    }

    fun getUsersByName(
        firstName: String,
        lastName: String,
    ): List<UserApplicationDto> {
        return users.filter { it.firstName == firstName && it.lastName == lastName }
            .also { LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserService.getUserByName", it) }
    }

    fun createUser(createUserDto: CreateUserDto): UserApplicationDto {
        val id = users.maxByOrNull { it.id }?.id?.plus(1) ?: 1
        val newUser = createUserDto.toUserApplicationDto(id)
        users.add(newUser)
        LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserService.createUser", newUser)
        return newUser
    }

    fun updateUser(
        id: Long,
        updateUserDto: UpdateUserDto,
    ): UserApplicationDto {
        val user =
            users.find { it.id == id }
                ?: run {
                    LOGGER.error("[{}] {}: {}", Result.FAILURE, "UserService.updateUser", "User not found")
                    error("User not found")
                }
        updateUserDto.firstName?.let { user.firstName = it }
        updateUserDto.lastName?.let { user.lastName = it }
        updateUserDto.age?.let { user.age = it }
        LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserService.updateUser", user)
        return user
    }

    fun deleteUser(id: Long): Long {
        val user =
            users.find { it.id == id }
                ?: run {
                    LOGGER.error("[{}] {}: {}", Result.FAILURE, "UserService.deleteUser", "User not found")
                    error("User not found")
                }
        users.remove(user)
        return id
    }
}
