package com.github.ahmadahghazadeh.users.controller.service

import com.github.ahmadahghazadeh.users.shared.UserDto

interface UsersService {
    fun add(user: UserDto): UserDto

    fun getUserDetailsByEmail(email: String): UserDto
}