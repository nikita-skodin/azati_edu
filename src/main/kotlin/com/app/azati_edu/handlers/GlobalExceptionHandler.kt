package com.app.azati_edu.handlers

import com.app.azati_edu.getClassLogger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = getClassLogger<GlobalExceptionHandler>()

    @ExceptionHandler(ResponseStatusException::class)
    fun handleHttpStatusCodeException(ex: ResponseStatusException): ResponseEntity<Map<String, String>> {
        val body = mapOf(
            "status" to ex.statusCode.value().toString(),
            "message" to ex.reason.toString()
        )
        return ResponseEntity(body, ex.statusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Map<String, String>> {
        logger.error(ex.message, ex)
        val body = mapOf(
            "status" to "500",
            "message" to ex.message.toString()
        )
        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
