package com.app.azati_edu.service

import com.app.azati_edu.model.PostModel
import com.app.azati_edu.dto.PostViewDTO

interface PostService {
    fun createPost(view: PostViewDTO): PostModel
    fun getPostById(id: Long): PostModel
    fun updatePost(id: Long, view: PostViewDTO): PostModel
    fun deletePost(id: Long): Boolean
}
