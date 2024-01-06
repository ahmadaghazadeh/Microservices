package com.github.ahmadahghazadeh.users.exception

import org.springdoc.api.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

class AppExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = {Exception.class})
    fun handleAnyException(exception: Exception ,request:WebRequest ): ResponseEntity<Any>{
        var message=exception.localizedMessage;
        if(message==null) message=exception.message;
        var errorMessage= ErrorMessage(message)
        return ResponseEntity<Any>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}