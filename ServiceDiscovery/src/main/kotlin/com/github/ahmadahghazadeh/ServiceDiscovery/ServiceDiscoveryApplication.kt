package com.github.ahmadahghazadeh.ServiceDiscovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceDiscoveryApplication

fun main(args: Array<String>) {
	runApplication<ServiceDiscoveryApplication>(*args)
}
