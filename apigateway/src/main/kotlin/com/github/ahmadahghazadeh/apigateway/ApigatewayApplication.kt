package com.github.ahmadahghazadeh.apigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ApigatewayApplication

fun main(args: Array<String>) {
	runApplication<ApigatewayApplication>(*args)
}
