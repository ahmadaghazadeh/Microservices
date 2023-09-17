package com.github.ahmadahghazadeh.users.ui.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class CreateUserRequestModel(
    @NotNull(message = "First name cannot be null")
    @Size(min = 2,message = "First name must not be less than two characters")
    var firstName: String,
    @NotNull(message = "LastName name cannot be null")
    @Size(min = 2,message = "LastName name must not be less than two characters")
    var lastName:String,
    @NotNull(message = "Password name cannot be null")
    @Size(min = 7, max = 16,message = "Password name must not be less than two characters")
    var password:String,
    @NotNull(message = "Email name cannot be null")
    @Email
    var email:String)
{
    protected constructor() : this("", "", "", "", )
}
