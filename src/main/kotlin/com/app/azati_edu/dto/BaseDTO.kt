package com.app.azati_edu.dto

import java.time.LocalDateTime

open class BaseDTO(
    var id: Long? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)
