package com.app.azati_edu.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserModel(
    @Column(name = "username", nullable = false, length = 50)
    var username: String,

    @Column(name = "email", nullable = false, length = 100)
    var email: String
) : BaseModel()