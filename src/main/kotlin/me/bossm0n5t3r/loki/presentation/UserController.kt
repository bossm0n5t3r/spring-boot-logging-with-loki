package me.bossm0n5t3r.loki.presentation

import me.bossm0n5t3r.loki.application.UserService
import me.bossm0n5t3r.loki.configuration.LOGGER
import me.bossm0n5t3r.loki.enumeration.Result
import me.bossm0n5t3r.loki.presentation.dto.CreateUserDto
import me.bossm0n5t3r.loki.presentation.dto.UpdateUserDto
import me.bossm0n5t3r.loki.presentation.dto.UserControllerDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllUsers(): List<UserControllerDto> {
        return userService.getAllUsers().map { it.toUserControllerDto() }.also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserController.getAllUsers", it)
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(
        @PathVariable id: Long,
    ): UserControllerDto? {
        return userService.getUserById(id)?.toUserControllerDto().also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserController.getUserById", it)
        }
    }

    @GetMapping("/name/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    fun getUsersByName(
        @PathVariable firstName: String,
        @PathVariable lastName: String,
    ): List<UserControllerDto> {
        return userService.getUsersByName(firstName, lastName).map { it.toUserControllerDto() }.also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserController.getUserByName", it)
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @RequestBody createUserDto: CreateUserDto,
    ): UserControllerDto {
        return userService.createUser(createUserDto).toUserControllerDto().also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserController.createUser", it)
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody updateUserDto: UpdateUserDto,
    ): UserControllerDto? {
        return userService.updateUser(id, updateUserDto).toUserControllerDto().also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserController.updateUser", it)
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(
        @PathVariable id: Long,
    ) {
        userService.deleteUser(id).also {
            LOGGER.info("[{}] {}: {}", Result.SUCCESS, "UserController.deleteUser", it)
        }
    }
}
