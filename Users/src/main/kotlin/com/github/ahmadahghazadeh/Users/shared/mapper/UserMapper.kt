package com.github.ahmadahghazadeh.users.shared.mapper

import com.github.ahmadahghazadeh.users.data.UserEntity
import com.github.ahmadahghazadeh.users.ui.model.CreateUserRequestModel
import com.github.ahmadahghazadeh.users.shared.UserDto


interface UserMapper {
    fun  createUserRequestModelToUserDto(user: CreateUserRequestModel): UserDto

    fun userDtoToUserEntity(userDto: UserDto): UserEntity

    fun userDtoToCreateUserRequestModel(userDto: UserDto): CreateUserRequestModel

}