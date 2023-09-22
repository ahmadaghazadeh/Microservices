package com.github.ahmadahghazadeh.apigateway


import com.google.common.net.HttpHeaders
import io.jsonwebtoken.*
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


@Component
class AuthorizationHeaderFilter(var environment: Environment ) :
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {


    class Config { // Put configuration properties here
    }

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return@GatewayFilter onError(
                    exchange,
                    "No authorization header",
                    HttpStatus.UNAUTHORIZED
                )
            }
            val authorizationHeader =
                request.headers[HttpHeaders.AUTHORIZATION]!![0]
            val jwt = authorizationHeader.replace("Bearer", "")
            if (!isJwtValid(jwt)) {
                return@GatewayFilter onError(
                    exchange,
                    "JWT token is not valid",
                    HttpStatus.UNAUTHORIZED
                )
            }
            chain.filter(exchange)
        }
    }

    private fun onError(exchange: ServerWebExchange, err: String, httpStatus: HttpStatus): Mono<Void?> {
        val response = exchange.response
        response.setStatusCode(httpStatus)
        return response.setComplete()
    }

    private fun isJwtValid(jwt: String): Boolean {
        var returnValue = true
        var subject: String? = null
        val tokenSecret: String = environment.getProperty("token.secret")!!
        val secretKeyBytes = Base64.getEncoder().encode(tokenSecret.toByteArray())
        val signingKey: SecretKey = SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.jcaName)
        val jwtParser = Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
        try {
            val parsedToken: Jws<Claims> = jwtParser.parseClaimsJws(jwt)
            subject = parsedToken.body.subject
        } catch (ex: Exception) {
            returnValue = false
        }
        if (subject == null || subject.isEmpty()) {
            returnValue = false
        }
        return returnValue
    }
}



