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
    return this.map(user, UserView::class.java)
}

fun ModelMapper.postModelToView(post: PostModel): PostView {
    return this.map(post, PostView::class.java).apply { userId = post.user.id }
}

fun ModelMapper.commentModelToView(comment: CommentModel): CommentView {
    return this.map(comment, CommentView::class.java).apply {
        userId = comment.user.id
        postId = comment.post.id
    }
}