package com.app.azati_edu.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PostViewDTO(
    var title: String,
    var content: String,
    var userId: Long,
) : BaseDTO(), Serializable {

    operator fun plus(other: PostViewDTO): MutableList<PostViewDTO> {
        return mutableListOf(this, other)
    }

}