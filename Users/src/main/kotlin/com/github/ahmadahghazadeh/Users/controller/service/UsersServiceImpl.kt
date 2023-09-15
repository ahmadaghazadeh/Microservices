package com.github.ahmadahghazadeh.users.controller.service


import com.github.ahmadahghazadeh.users.data.UserEntity
import com.github.ahmadahghazadeh.users.repository.UsersRepository
import com.github.ahmadahghazadeh.users.shared.UserDto
import com.github.ahmadahghazadeh.users.shared.mapper.UserMapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UsersServiceImpl(private val usersRepository: UsersRepository,
                       private val userMapper: UserMapper,
                       private val bCryptPasswordEncoder: BCryptPasswordEncoder) : UsersService {


    override fun add(user: UserDto): UserEntity {
        user.apply {
            userId = UUID.randomUUID().toString()
        }
        val userEntity = userMapper.userDtoToUserEntity(user)
        userEntity.encryptedPassword =bCryptPasswordEncoder.encode(user.password)
        usersRepository.save(userEntity);
        return userMapper.userDtoToUserEntity(user)
    }
}