package com.github.ahmadahghazadeh.users.controller

import com.github.ahmadahghazadeh.users.controller.service.UsersService
import com.github.ahmadahghazadeh.users.shared.UserDto
import com.github.ahmadahghazadeh.users.ui.model.CreateUserRequestModel
import jakarta.validation.Valid
import mu.KLogging
import org.modelmapper.ModelMapper
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class  UserController(
    val usersService: UsersService,
    val environment: Environment,
    private val modelMapper: ModelMapper,
) {

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/isOnline/{name}")
    fun getOnline(): ResponseEntity<String> {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("Welcome, You are online and application port is ${environment.getProperty("local.server.port")}")
    }

    companion object : KLogging()

    @PreAuthorize("anonymous()")
    @GetMapping("/{name}")
    fun get(@PathVariable("name") name: String): ResponseEntity<String> {
        logger.info("Saved course is id $name")
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("Hello $name ${environment.getProperty("local.server.port")}")
    }


    @PostMapping(consumes = [ MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE],
        produces = [ MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,])
    fun addUser(@Valid @RequestBody createUserRequestModel: CreateUserRequestModel): ResponseEntity<CreateUserRequestModel>{

        //TODO: That is a framework item. I need to handle this differently. Use kotlin mapper.

       val userDto= modelMapper.map(createUserRequestModel, UserDto::class.java)

       usersService.add(userDto)
        // ${environment.getProperty("local.server.port")
        logger.info("Saved course is id $createUserRequestModel")
        val returnValue= modelMapper.map(userDto, CreateUserRequestModel::class.java)

       return ResponseEntity.status(HttpStatus.CREATED)
            .body(returnValue)
    }

}