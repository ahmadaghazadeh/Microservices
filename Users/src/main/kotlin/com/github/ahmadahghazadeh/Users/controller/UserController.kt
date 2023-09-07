package com.github.ahmadahghazadeh.users.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): ResponseEntity<String> {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Hello $name")
    }

}