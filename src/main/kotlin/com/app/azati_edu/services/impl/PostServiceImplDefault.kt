package com.app.azati_edu.services.impl

import com.app.azati_edu.models.PostModel
import com.app.azati_edu.services.PostService
import com.app.azati_edu.views.PostView
import org.springframework.stereotype.Service

@Service
class PostServiceImplDefault : PostService {
    override fun createPost(view: PostView): PostModel {
        TODO("Not yet implemented")
    }

    override fun getPostById(id: Long): PostModel {
        TODO("Not yet implemented")
    }

    override fun updatePost(id: Long, view: PostView): PostModel {
        TODO("Not yet implemented")
    }

    override fun deletePost(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}