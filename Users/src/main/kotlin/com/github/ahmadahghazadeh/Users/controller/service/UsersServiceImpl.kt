package com.github.ahmadahghazadeh.users.controller.service


import com.github.ahmadahghazadeh.users.data.UserEntity
import com.github.ahmadahghazadeh.users.repository.UsersRepository
import com.github.ahmadahghazadeh.users.shared.UserDto
import org.modelmapper.ModelMapper
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UsersServiceImpl(
    private val usersRepository: UsersRepository,
    private val modelMapper: ModelMapper,
    private val encoder: BCryptPasswordEncoder,
) : UsersService {


    override fun add(user: UserDto): UserDto {

        user.userId=(UUID.randomUUID().toString())
        user.encryptedPassword=(encoder.encode(user.password))
        val userEntity = modelMapper.map(user, UserEntity::class.java)
        usersRepository.save(userEntity)
        return modelMapper.map(userEntity, UserDto::class.java)
    }

    override fun getUserDetailsByEmail(email: String): UserDto {
        val userEntity: UserEntity = usersRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return modelMapper.map(userEntity, UserDto::class.java)
    }

    override fun getUserByUserId(userId: String?): UserDto? {
        val userEntity: UserEntity = usersRepository.findByUserId(userId) ?: throw UsernameNotFoundException(userId)
        return modelMapper.map(userEntity, UserDto::class.java)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val userEntity: UserEntity = usersRepository.findByEmail(username) ?: throw UsernameNotFoundException(username)
        return User(userEntity.email, userEntity.encryptedPassword, true, true, true, true, ArrayList())
    }

}