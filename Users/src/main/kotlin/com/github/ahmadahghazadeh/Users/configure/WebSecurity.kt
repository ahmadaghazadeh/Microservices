package com.github.ahmadahghazadeh.users.configure

import org.springframework.boot.fromApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
@EnableWebSecurity
class WebSecurity {
    //Basic Authentication
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }
//        http
//        .csrf {authorize ->
//            authorize.disable().authorizeHttpRequests {
//                it.requestMatchers(antMatcher("/swagger-ui")).permitAll() // TODO: found a write code
//                it.anyRequest().authenticated()
//        }
//        }
        http.authorizeHttpRequests {
            it.requestMatchers(antMatcher("/users/**")).permitAll()
            it.anyRequest().authenticated()
        }.httpBasic(Customizer.withDefaults())


        http.headers {headers ->
            headers.frameOptions{
                it.disable()
            }
        }

        return http.build()
    }

//    @Bean
//    fun  userDetailsService(): UserDetailsService{
//        val ahmad=User.builder()
//            .username("ahmad")
//            .password("asd123")
//            .roles("USER")
//            .build()
//
//        val admin =User.builder()
//            .username("admin")
//            .password("admin")
//            .roles("ADMIN")
//            .build()
//        return InMemoryUserDetailsManager(ahmad,admin)
//    }
}