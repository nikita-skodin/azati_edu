package com.app.azati_edu.services

import com.app.azati_edu.models.UserModel
import com.app.azati_edu.views.UserView
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun createUser(view: UserView): UserModel
    fun getUserById(id: Long): UserModel
    fun updateUser(id: Long, view: UserView): UserModel
    fun deleteUser(id: Long): Boolean
}