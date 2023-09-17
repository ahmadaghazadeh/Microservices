package com.github.ahmadahghazadeh.users.ui.model


data class CreateUserResponseModel(
    var firstName: String,
    var lastName:String,
    var userId:String,
    var email:String
){
    protected constructor() : this("", "", "", "", )
}