package com.app.azati_edu.services

import com.app.azati_edu.models.CommentModel
import com.app.azati_edu.views.CommentView

interface CommentService {
    fun createComment(view: CommentView): CommentModel
    fun getCommentById(id: Long): CommentModel
    fun updateComment(id: Long, view: CommentView): CommentModel
    fun deleteComment(id: Long): Boolean
}
