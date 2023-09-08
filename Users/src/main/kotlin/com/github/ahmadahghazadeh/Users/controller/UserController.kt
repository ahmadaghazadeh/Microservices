package com.github.ahmadahghazadeh.users.controller

import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val environment: Environment) {
    @GetMapping("/{name}")
    fun get(@PathVariable("name") name: String): ResponseEntity<String> {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("Hello $name ${environment.getProperty("local.server.port")}")
    }

}