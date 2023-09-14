package com.github.ahmadahghazadeh.users.configure

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class Security {
    @Bean
    fun bCryptPasswordEncoder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}