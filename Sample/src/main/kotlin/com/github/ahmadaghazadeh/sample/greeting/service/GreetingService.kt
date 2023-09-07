package com.github.ahmadaghazadeh.users.greeting.service

interface GreetingService {
    fun retrieveGreeting(name: String): String
}