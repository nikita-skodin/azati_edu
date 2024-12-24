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
) : Serializable {
    var id: Long? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    operator fun plus(other: PostViewDTO): MutableList<PostViewDTO> {
        return mutableListOf(this, other)
    }

}