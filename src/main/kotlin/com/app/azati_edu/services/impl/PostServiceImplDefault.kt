package com.app.azati_edu.services.impl

import com.app.azati_edu.config.postViewToModel
import com.app.azati_edu.getClassLogger
import com.app.azati_edu.models.PostModel
import com.app.azati_edu.repositories.PostModelRepository
import com.app.azati_edu.repositories.UserModelRepository
import com.app.azati_edu.services.PostService
import com.app.azati_edu.views.PostView
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class PostServiceImplDefault(
    private val userModelRepository: UserModelRepository,
    private val modelMapper: ModelMapper,
    private val postModelRepository: PostModelRepository
) : PostService {

    private val logger = getClassLogger<PostServiceImplDefault>()

    @Transactional(rollbackFor = [Exception::class])
    override fun createPost(view: PostView): PostModel {
        logger.info("Creating post $view")
        val userDB = getUserOrExc(view.userId)

        val post = modelMapper.postViewToModel(view, PostModel(view.title, view.content, userDB))
        val saved = postModelRepository.save(post)
        logger.info("Post $view successfully created")
        return saved
    }

    @Transactional(readOnly = true)
    override fun getPostById(id: Long): PostModel {
        logger.info("Searching for post with id $id")
        return getPostOrExc(id)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updatePost(id: Long, view: PostView): PostModel {
        if (id != view.id) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Ids are not the same")
        }

        var postDB = getPostOrExc(id)
        getUserOrExc(view.userId)

        postDB = modelMapper.postViewToModel(view, postDB)
        logger.info("Post $view successfully updated")
        return postDB
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deletePost(id: Long): Boolean {
        logger.info("Attempting to delete post with id $id")
        val isExist = postModelRepository.existsById(id)
        if (isExist) {
            postModelRepository.deleteById(id)
            logger.info("Post $id deleted")
            return true
        } else {
            logger.info("Post $id not found")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id $id not found")
        }
    }

    private fun getPostOrExc(id: Long) = postModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Post with id $id not found"
    )

    private fun getUserOrExc(id: Long) = userModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "User with id $id not found"
    )
}