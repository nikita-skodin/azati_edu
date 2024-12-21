package com.app.azati_edu.views

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserView(
    var username: String,
    var email: String,
) : Serializable {

    var id: Long? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    operator fun plus(other: UserView): MutableList<UserView> {
        return mutableListOf(this, other)
    }

}