package com.github.ahmadahghazadeh.users.controller

import com.github.ahmadahghazadeh.users.controller.service.UsersService
import com.github.ahmadahghazadeh.users.ui.model.CreateUserRequestModel
import com.github.ahmadahghazadeh.users.shared.mapper.UserMapper
import jakarta.validation.Valid
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class  UserController(
    val usersService: UsersService,
    val environment: Environment,
    val userMapper: UserMapper
) {
    @GetMapping("/{name}")
    fun get(@PathVariable("name") name: String): ResponseEntity<String> {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("Hello $name ${environment.getProperty("local.server.port")}")
    }

    @PostMapping
    fun addUser(@Valid @RequestBody createUserRequestModel: CreateUserRequestModel,): ResponseEntity<CreateUserRequestModel>{

        //TODO: That is a framework item. I need to handle this differently. Use kotlin mapper.

       val userDto=userMapper.createUserRequestModelToUserDto(createUserRequestModel)

       usersService.add(userDto)
        // ${environment.getProperty("local.server.port")

        val returnValue=userMapper.userDtoToCreateUserRequestModel(userDto)

       return ResponseEntity.status(HttpStatus.CREATED)
            .body(returnValue)
    }

}