package com.github.ahmadahghazadeh.users.ui.model


data class CreateUserResponseModel(
    val firstName: String,
    val lastName:String,
    val userId:String,
    val email:String
)