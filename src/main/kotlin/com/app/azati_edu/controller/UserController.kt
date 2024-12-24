package com.app.azati_edu.controller

import com.app.azati_edu.config.userModelToView
import com.app.azati_edu.service.UserService
import com.app.azati_edu.dto.UserViewDTO
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserController(
    private val userService: UserService,
    private val modelMapper: ModelMapper
) {

    @PostMapping
    fun createUser(@RequestBody user: UserViewDTO): ResponseEntity<UserViewDTO> {
        val createdUser = userService.createUser(user)
        return ResponseEntity.ok(modelMapper.userModelToView(createdUser))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserViewDTO> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(modelMapper.userModelToView(user))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody view: UserViewDTO): ResponseEntity<UserViewDTO> {
        val user = userService.updateUser(id, view)
        return ResponseEntity.ok(modelMapper.userModelToView(user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = userService.deleteUser(id)
        return ResponseEntity.ok(isDeleted)
    }

}