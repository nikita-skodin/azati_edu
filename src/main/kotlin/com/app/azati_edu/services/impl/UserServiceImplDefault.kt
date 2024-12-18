package com.app.azati_edu.services.impl

import com.app.azati_edu.config.userViewToModel
import com.app.azati_edu.getClassLogger
import com.app.azati_edu.models.UserModel
import com.app.azati_edu.repositories.UserModelRepository
import com.app.azati_edu.services.UserService
import com.app.azati_edu.views.UserView
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class UserServiceImplDefault(
    private val modelMapper: ModelMapper,
    private val userModelRepository: UserModelRepository
) : UserService {

    private val logger = getClassLogger<UserServiceImplDefault>()

    @Transactional(rollbackFor = [Exception::class])
    override fun createUser(view: UserView): UserModel {
        logger.info("Creating new User")
        if (userModelRepository.existsByUsernameOrEmail(view.username, view.email)) {
            logger.warn("User $view already exists")
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "User with username ${view.username} or email ${view.email} already exists."
            )
        }
        val saved = userModelRepository.save(modelMapper.userViewToModel(view))
        logger.info("User $view created successfully")
        return saved
    }

    @Transactional(readOnly = true)
    override fun getUserById(id: Long): UserModel {
        logger.info("Searching for user with id $id")
        return userModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User with id $id not found"
        )
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updateUser(id: Long, view: UserView): UserModel {
        logger.info("Updating user with id $id")
        if (id != view.id) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Ids are not the same")
        }

        var userDB = userModelRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User with id $id not found"
        )

        if (userModelRepository.existsByUsernameOrEmailAndIdNot(view.username, view.email, userDB.id)) {
            logger.warn("User $view already exists")
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "User with username ${view.username} or email ${view.email} already exists."
            )
        }

        userDB = modelMapper.userViewToModel(view)
        val saved = userModelRepository.save(userDB)
        logger.info("User $view updated successfully")
        return saved
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteUser(id: Long): Boolean {
        logger.info("Attempting to delete User with id $id")
        val isExist = userModelRepository.existsById(id)
        if (isExist) {
            userModelRepository.deleteById(id)
            logger.info("User $id deleted")
            return true
        } else {
            logger.info("User $id not found")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found")
        }
    }
}