package com.app.azati_edu.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserViewDTO(
    var username: String,
    var email: String,
) : BaseDTO(), Serializable {

    operator fun plus(other: UserViewDTO): MutableList<UserViewDTO> {
        return mutableListOf(this, other)
    }

}