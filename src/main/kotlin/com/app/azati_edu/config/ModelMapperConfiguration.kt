package com.app.azati_edu.config

import com.app.azati_edu.model.CommentModel
import com.app.azati_edu.model.PostModel
import com.app.azati_edu.model.UserModel
import com.app.azati_edu.dto.CommentModelDTO
import com.app.azati_edu.dto.PostViewDTO
import com.app.azati_edu.dto.UserViewDTO
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfiguration {
    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STANDARD
        modelMapper.configuration.isSkipNullEnabled = true
        return modelMapper
    }
}

fun ModelMapper.userModelToView(user: UserModel): UserViewDTO {
    return UserViewDTO(user.username, user.email).also { this.map(user, it) }
}

fun ModelMapper.userViewToModel(userViewDTO: UserViewDTO, userModel: UserModel): UserModel {
    this.map(userViewDTO, userModel)
    return userModel
}

fun ModelMapper.postModelToView(post: PostModel): PostViewDTO {
    return PostViewDTO(post.title, post.content, post.user.id).also { this.map(post, it) }
}

fun ModelMapper.postViewToModel(postViewDTO: PostViewDTO, postModel: PostModel): PostModel {
    this.map(postViewDTO, postModel)
    return postModel.apply { user = postModel.user }
}

fun ModelMapper.commentModelToView(comment: CommentModel): CommentModelDTO {
    return CommentModelDTO(comment.content, comment.user.id, comment.post.id).also { this.map(comment, it) }
}

fun ModelMapper.commentViewToModel(
    commentModelDTO: CommentModelDTO,
    commentModel: CommentModel,
): CommentModel {
    this.map(commentModelDTO, commentModel)
    return commentModel.apply {
        user = commentModel.user
        post = commentModel.post
    }
}