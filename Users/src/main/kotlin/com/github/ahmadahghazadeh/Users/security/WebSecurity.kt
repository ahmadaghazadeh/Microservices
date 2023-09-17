package com.github.ahmadahghazadeh.users.security

import com.github.ahmadahghazadeh.users.controller.service.UsersService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher


@Configuration
@EnableWebSecurity
class WebSecurity(private val usersService: UsersService, val environment: Environment) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }
        http.authorizeHttpRequests {
            it.requestMatchers(antMatcher("/users/**")).permitAll()
            it.requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
            it.requestMatchers(antMatcher("/api-docs/**")).permitAll()
            it.requestMatchers(antMatcher("/**")).permitAll() // hasIpAddress(environment.getProperty("gateway.ip"))
            it.anyRequest().authenticated()
        }

        //http.addFilterBefore(getAuthenticationFilter(http), UsernamePasswordAuthenticationFilter::class.java)
        http.headers {headers ->
            headers.frameOptions{
                it.disable()
            }
        }

        return http.build()
    }

    fun getAuthenticationFilter(http: HttpSecurity): AuthenticationFilter {
        val authenticationManager: AuthenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        return AuthenticationFilter(usersService, environment, authenticationManager.build())
    }
}