package com.github.ahmadahghazadeh.users.security

import com.github.ahmadahghazadeh.users.controller.service.UsersService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import java.util.*


@Configuration
@EnableWebSecurity
class WebSecurity(private val usersService: UsersService,
                  val environment: Environment,
                  val authenticationConfiguration: AuthenticationConfiguration,
                  private val encoder: BCryptPasswordEncoder
) {


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

       http.addFilterBefore(getAuthenticationFilter(http), UsernamePasswordAuthenticationFilter::class.java)
        http.authenticationProvider(authenticationProvider())
        http.headers {headers ->
            headers.frameOptions{
                it.disable()
            }
        }

        return http.getOrBuild()
    }


    fun getAuthenticationFilter(http: HttpSecurity): AuthenticationFilter {
        val authenticationFilter=AuthenticationFilter(usersService, environment,authenticationConfiguration.authenticationManager)
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"))
        return authenticationFilter
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(usersService)
        authenticationProvider.setPasswordEncoder(encoder)
        return authenticationProvider
    }
}