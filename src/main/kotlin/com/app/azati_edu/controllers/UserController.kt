package com.app.azati_edu.controllers

import com.app.azati_edu.config.userModelToView
import com.app.azati_edu.services.UserService
import com.app.azati_edu.views.UserView
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
    fun createUser(@RequestBody user: UserView): ResponseEntity<UserView> {
        val createdUser = userService.createUser(user)
        return ResponseEntity.ok(modelMapper.userModelToView(createdUser))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserView> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(modelMapper.userModelToView(user))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody view: UserView): ResponseEntity<UserView> {
        val user = userService.updateUser(id, view)
        return ResponseEntity.ok(modelMapper.userModelToView(user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = userService.deleteUser(id)
        return ResponseEntity.ok(isDeleted)
    }

}