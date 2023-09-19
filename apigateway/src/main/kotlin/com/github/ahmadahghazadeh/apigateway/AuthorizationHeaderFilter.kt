package com.github.ahmadahghazadeh.apigateway

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


@Component
class AuthorizationHeaderFilter :
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {
    @Autowired
    var env: Environment? = null

    class Config { // Put configuration properties here
    }

    override fun apply(config: Config?): GatewayFilter {
        TODO("Not yet implemented")
    }
}

