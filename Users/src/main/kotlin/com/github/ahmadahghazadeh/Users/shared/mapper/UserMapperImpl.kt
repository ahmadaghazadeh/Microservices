package com.github.ahmadahghazadeh.users.shared.mapper

import com.github.ahmadahghazadeh.users.data.UserEntity
import com.github.ahmadahghazadeh.users.ui.model.CreateUserRequestModel
import com.github.ahmadahghazadeh.users.shared.UserDto
import org.springframework.stereotype.Service

@Service
class UserMapperImpl:UserMapper {
    override fun createUserRequestModelToUserDto(user: CreateUserRequestModel): UserDto {
        return UserDto(
            user.firstName,
            user.lastName,
            user.email,
            user.password)
    }


    override fun userDtoToUserEntity(userDto: UserDto): UserEntity {
        return UserEntity(null,
            userDto.firstName!!,
            userDto.lastName!!,
            userDto.email!!,
            userDto.userId!!,
            "")
    }

    override fun userDtoToCreateUserRequestModel(userDto: UserDto): CreateUserRequestModel {
        return CreateUserRequestModel(
            userDto.firstName!!,
            userDto.lastName!!,
            userDto.email!!,
            userDto.userId!!)
    }
}