package com.app.azati_edu.services

import com.app.azati_edu.models.PostModel
import com.app.azati_edu.views.PostView
import org.springframework.stereotype.Service

@Service
interface PostService {
    fun createPost(view: PostView): PostModel
    fun getPostById(id: Long): PostModel
    fun updatePost(id: Long, view: PostView): PostModel
    fun deletePost(id: Long): Boolean
}
