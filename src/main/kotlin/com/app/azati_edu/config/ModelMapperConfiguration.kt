package com.app.azati_edu.config

import com.app.azati_edu.models.CommentModel
import com.app.azati_edu.models.PostModel
import com.app.azati_edu.models.UserModel
import com.app.azati_edu.views.CommentView
import com.app.azati_edu.views.PostView
import com.app.azati_edu.views.UserView
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

fun ModelMapper.userModelToView(user: UserModel): UserView {
    return UserView(user.username, user.email).also { this.map(user, it) }
}

fun ModelMapper.userViewToModel(userView: UserView, userModel: UserModel): UserModel {
    this.map(userView, userModel)
    return userModel
}

fun ModelMapper.postModelToView(post: PostModel): PostView {
    return PostView(post.title, post.content, post.user.id).also { this.map(post, it) }
}

fun ModelMapper.postViewToModel(postView: PostView, postModel: PostModel): PostModel {
    this.map(postView, postModel)
    postModel.user = postModel.user
    return postModel
}

fun ModelMapper.commentModelToView(comment: CommentModel): CommentView {
    return CommentView(comment.content, comment.user.id, comment.post.id).also { this.map(comment, it) }
}

fun ModelMapper.commentViewToModel(
    commentView: CommentView,
    commentModel: CommentModel,
): CommentModel {
    this.map(commentView, commentModel)
    commentModel.user = commentModel.user
    commentModel.post = commentModel.post
    return commentModel
}