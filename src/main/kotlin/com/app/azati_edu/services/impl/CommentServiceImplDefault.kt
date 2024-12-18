package com.app.azati_edu.services.impl

import com.app.azati_edu.models.CommentModel
import com.app.azati_edu.services.CommentService
import com.app.azati_edu.views.CommentView
import org.springframework.stereotype.Service

@Service
class CommentServiceImplDefault : CommentService {
    override fun createComment(view: CommentView): CommentModel {
        TODO("Not yet implemented")
    }

    override fun getCommentById(id: Long): CommentModel {
        TODO("Not yet implemented")
    }

    override fun updateComment(id: Long, view: CommentView): CommentModel {
        TODO("Not yet implemented")
    }

    override fun deleteComment(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}