package com.app.azati_edu.model

import jakarta.persistence.*

@Entity
@Table(name = "posts")
class PostModel(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.REMOVE)])
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserModel
) : BaseModel()