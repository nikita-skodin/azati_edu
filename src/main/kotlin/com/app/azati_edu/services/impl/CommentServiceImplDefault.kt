package com.app.azati_edu.services.impl

import com.app.azati_edu.config.commentViewToModel
import com.app.azati_edu.getClassLogger
import com.app.azati_edu.models.CommentModel
import com.app.azati_edu.repositories.CommentModelRepository
import com.app.azati_edu.repositories.PostModelRepository
import com.app.azati_edu.repositories.UserModelRepository
import com.app.azati_edu.services.CommentService
import com.app.azati_edu.views.CommentView
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CommentServiceImplDefault(
    private val userModelRepository: UserModelRepository,
    private val postModelRepository: PostModelRepository,
    private val modelMapper: ModelMapper,
    private val commentModelRepository: CommentModelRepository
) : CommentService {
    private val logger = getClassLogger<PostServiceImplDefault>()

    @Transactional(rollbackFor = [Exception::class])
    override fun createComment(view: CommentView): CommentModel {
        logger.info("Creating comment $view")
        val userDB = getUserOrExc(view.userId)
        val postDB = getPostOrExc(view.postId)
        val comment = modelMapper
            .commentViewToModel(view, CommentModel(view.content, userDB, postDB))
        val saved = commentModelRepository.save(comment)
        val (content, userId, postId) = view
        logger.info("Comment: ${"content: $content userId: $userId postId: $postId"} successfully created")
        return saved
    }

    @Transactional(readOnly = true)
    override fun getCommentById(id: Long): CommentModel {
        logger.info("Searching for comment with id $id")
        return getCommentOrExc(id)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updateComment(id: Long, view: CommentView): CommentModel {
        if (id != view.id) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Ids are not the same")
        }

        var commentDB = getCommentOrExc(id)
        getPostOrExc(view.postId)
        getUserOrExc(view.userId)

        commentDB = modelMapper.commentViewToModel(view, commentDB)
        val (content, userId, postId) = view
        logger.info("Comment: ${"content: $content userId: $userId postId: $postId"} successfully updated")
        return commentDB

    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteComment(id: Long): Boolean {
        logger.info("Attempting to delete comment with id $id")
        val isExist = commentModelRepository.existsById(id)
        if (isExist) {
            commentModelRepository.deleteById(id)
            logger.info("Comment $id deleted")
            return true
        } else {
            logger.info("Comment $id not found")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with id $id not found")
        }
    }

    private fun getCommentOrExc(id: Long) = commentModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Comment with id $id not found"
    )

    private fun getPostOrExc(id: Long) = postModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Post with id $id not found"
    )

    private fun getUserOrExc(id: Long) = userModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "User with id $id not found"
    )
}