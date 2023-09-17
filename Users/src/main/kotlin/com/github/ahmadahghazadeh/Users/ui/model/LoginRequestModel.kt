package com.github.ahmadahghazadeh.users.ui.model

data class LoginRequestModel(var email: String, var password: String){
    protected constructor() : this("", "" )
}