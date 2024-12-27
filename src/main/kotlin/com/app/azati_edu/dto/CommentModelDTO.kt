package com.app.azati_edu.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CommentModelDTO(
    var content: String,
    var userId: Long,
    var postId: Long,
) : BaseDTO(), Serializable {
    operator fun plus(other: CommentModelDTO): MutableList<CommentModelDTO> {
        return mutableListOf(this, other)
    }

}