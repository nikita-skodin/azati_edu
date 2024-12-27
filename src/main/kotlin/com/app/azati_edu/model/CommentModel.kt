package com.app.azati_edu.model

import jakarta.persistence.*

@Entity
@Table(name = "comments")
class CommentModel(
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.REMOVE)])
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserModel,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.REMOVE)])
    @JoinColumn(name = "post_id", nullable = false)
    var post: PostModel,
) : BaseModel()