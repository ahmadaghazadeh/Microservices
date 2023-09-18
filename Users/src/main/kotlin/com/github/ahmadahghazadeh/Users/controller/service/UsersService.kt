package com.github.ahmadahghazadeh.users.controller.service

import com.github.ahmadahghazadeh.users.shared.UserDto
import org.springframework.security.core.userdetails.UserDetailsService


interface UsersService: UserDetailsService {
    fun add(user: UserDto): UserDto

    fun getUserDetailsByEmail(email: String): UserDto

    fun getUserByUserId(userId: String?): UserDto?
}