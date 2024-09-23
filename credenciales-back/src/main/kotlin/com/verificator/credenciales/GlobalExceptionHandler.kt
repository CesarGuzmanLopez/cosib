package com.verificator.credenciales

import documents.api.generated.dto.ErrorResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(error  = ex.message ?: "Unknown error")
        return ResponseEntity.status(500).body(errorResponse)
    }
}