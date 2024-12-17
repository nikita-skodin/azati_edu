package com.app.azati_edu.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener::class)
class CommentModel(
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.REMOVE)])
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserModel,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.REMOVE)])
    @JoinColumn(name = "post_id", nullable = false)
    var post: PostModel,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
}