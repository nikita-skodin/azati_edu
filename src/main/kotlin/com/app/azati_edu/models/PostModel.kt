package com.app.azati_edu.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener::class)
class PostModel(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.REMOVE)])
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserModel
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