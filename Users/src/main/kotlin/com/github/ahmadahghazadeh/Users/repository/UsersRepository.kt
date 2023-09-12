package com.github.ahmadahghazadeh.users.repository

import com.github.ahmadahghazadeh.users.data.UserEntity
import org.springframework.data.repository.CrudRepository


interface UsersRepository: CrudRepository<UserEntity, Long>