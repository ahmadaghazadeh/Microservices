package com.github.ahmadahghazadeh.users.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ahmadahghazadeh.users.controller.service.UsersService
import com.github.ahmadahghazadeh.users.shared.UserDto
import com.github.ahmadahghazadeh.users.ui.model.LoginRequestModel
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*


class AuthenticationFilter (private val usersService: UsersService,
                           private val environment: Environment,
    private val authenticationManager: AuthenticationManager
): UsernamePasswordAuthenticationFilter() {

    init {
        super.setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?
    ): Authentication {
        return try {
             val (email, password) = ObjectMapper()
                .readValue(req.inputStream, LoginRequestModel::class.java)
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    email,
                    password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }


    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest?,
        res: HttpServletResponse,
        chain: FilterChain?,
        auth: Authentication
    ) {
        val userName: String = (auth.principal as User).username
        val userDetails: UserDto = usersService.getUserDetailsByEmail(userName)
        val key = Keys.secretKeyFor(SignatureAlgorithm.HS512) //or HS384 or HS512

        val token: String = Jwts.builder()
            .setSubject(userDetails.userId)
            .setExpiration(
                Date(
                    System.currentTimeMillis() + environment.getProperty("token.expiration_time")!!.toLong()
                )
            )
            .signWith(key )
            .compact()
        res.addHeader("token", token)
        res.addHeader("userId", userDetails.userId)
    }



}