package com.github.ahmadaghazadeh.users.greeting.service.impl

import com.github.ahmadaghazadeh.users.greeting.service.GreetingService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class GreetingServiceImpl: GreetingService {

    @Value("\${message}")
    lateinit var message: String
    override fun retrieveGreeting(name: String)  ="$name, $message"

}