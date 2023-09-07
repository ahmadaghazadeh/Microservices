package com.github.ahmadahghazadeh.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class AccountApplication

fun main(args: Array<String>) {
	runApplication<AccountApplication>(*args)
}
