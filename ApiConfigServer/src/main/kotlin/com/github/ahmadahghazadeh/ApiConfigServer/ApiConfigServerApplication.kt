package com.github.ahmadahghazadeh.ApiConfigServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication

class ApiConfigServerApplication

fun main(args: Array<String>) {
	runApplication<ApiConfigServerApplication>(*args)
}
