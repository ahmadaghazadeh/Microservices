package com.github.ahmadaghazadeh.users.greeting.controller

import com.github.ahmadaghazadeh.users.greeting.exception.GreetingNotFoundException
import com.github.ahmadaghazadeh.users.greeting.service.GreetingService
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/greetings")
class GreetingsController(val greetingService: GreetingService) {

    companion object: KLogging()
    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String):String{
        if(name=="Ahmad"){
            throw GreetingNotFoundException("$name is not found")
        }
        logger.info("Name is $name")
        return greetingService.retrieveGreeting(name);
    }
}