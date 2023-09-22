package com.github.ahmadahghazadeh.apigateway

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import reactor.core.publisher.Mono

@Configuration
class GlobalFiltersConfiguration {

    val logger: Logger = LoggerFactory.getLogger(GlobalFiltersConfiguration::class.java)

    @Order(1)
    @Bean
    fun secondPreFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->
            logger.info("My second Global Pre-Filter is executed")
            chain.filter(exchange).then(Mono.fromRunnable {
                logger.info("My third Global Post-Filter is executed")
            })
        }
    }
    @Order(2)
    @Bean
    fun thirdPreFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->
            logger.info("My third Global Pre-Filter is executed")
            chain.filter(exchange).then(Mono.fromRunnable {
                logger.info("My Second Global Post-Filter is executed")
            })
        }
    }
    @Order(3 )
    @Bean
    fun forthPreFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->
            logger.info("My forth Global Pre-Filter is executed")
            chain.filter(exchange).then(Mono.fromRunnable {
                logger.info("My First Global Post-Filter is executed")
            })
        }
    }
}