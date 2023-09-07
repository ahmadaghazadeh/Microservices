package com.github.ahmadaghazadeh.users.greeting.controller

import com.github.ahmadaghazadeh.users.greeting.exception.GreetingNotFoundException
import com.github.ahmadaghazadeh.users.greeting.model.Greeting
import com.github.ahmadaghazadeh.users.greeting.service.GreetingService
import com.github.ahmadaghazadeh.users.greeting.service.impl.GreetingServiceImpl
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/greetings")
class GreetingsController(val greetingService: GreetingService) {

    companion object: KLogging()
    @GetMapping("/{name}", produces = [
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    ])
    fun retrieveGreeting(@PathVariable("name") name: String):ResponseEntity<Greeting>{
        if(name=="Ahmad"){
            throw GreetingNotFoundException("$name is not found")
        }
        logger.info("Name is $name")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Greeting("Ahmad",1))
    }
}