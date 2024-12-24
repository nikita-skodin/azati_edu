package com.app.azati_edu.service

import com.app.azati_edu.model.UserModel
import com.app.azati_edu.dto.UserViewDTO

interface UserService {
    fun createUser(view: UserViewDTO): UserModel
    fun getUserById(id: Long): UserModel
    fun updateUser(id: Long, view: UserViewDTO): UserModel
    fun deleteUser(id: Long): Boolean
}