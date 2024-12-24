package com.app.azati_edu.service

import com.app.azati_edu.model.CommentModel
import com.app.azati_edu.dto.CommentModelDTO

interface CommentService {
    fun createComment(view: CommentModelDTO): CommentModel
    fun getCommentById(id: Long): CommentModel
    fun updateComment(id: Long, view: CommentModelDTO): CommentModel
    fun deleteComment(id: Long): Boolean
}
