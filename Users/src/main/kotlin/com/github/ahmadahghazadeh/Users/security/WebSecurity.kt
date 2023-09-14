package com.github.ahmadahghazadeh.users.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurity {
    // Basic Authentication
    @Bean
    fun securityFilterChain(http:HttpSecurity ): SecurityFilterChain{
        http.csrf {authorize ->
            authorize.disable().authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
        }.httpBasic(Customizer.withDefaults())

        return http.build()
    }

    @Bean
    fun  userDetailsService(): UserDetailsService{
        val ahmad=User.builder()
            .username("ahmad")
            .password("asd123")
            .roles("USER")
            .build()

        val admin =User.builder()
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build()
        return InMemoryUserDetailsManager(ahmad,admin)
    }
}