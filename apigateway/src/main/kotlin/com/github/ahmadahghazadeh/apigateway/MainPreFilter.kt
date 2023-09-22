package com.github.ahmadahghazadeh.apigateway

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Consumer

@Component
class MainPreFilter:GlobalFilter {

    val logger: Logger = LoggerFactory.getLogger(MainPreFilter::class.java)
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        logger.info("My first Pre-filter is executed...")
        val requestPath = exchange.request.path.toString()
        logger.info("Request path = $requestPath")
        val headers: HttpHeaders = exchange.request.headers
        val headerNames: Set<String> = headers.keys
        headerNames.forEach(Consumer { headerName: String ->
            val headerValue: String? = headers.getFirst(headerName)
            logger.info("$headerName $headerValue")
        })
        return chain.filter(exchange)
    }
    fun getOrder(): Int {
        return 0
    }
}