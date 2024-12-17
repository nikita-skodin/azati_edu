package com.app.azati_edu.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
class UserModel(
    @Column(name = "username", nullable = false, length = 50)
    var username: String,

    @Column(name = "email", nullable = false, length = 100)
    var email: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0L

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
}