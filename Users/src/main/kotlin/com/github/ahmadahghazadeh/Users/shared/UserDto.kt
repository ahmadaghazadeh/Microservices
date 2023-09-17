package com.github.ahmadahghazadeh.users.shared

data class UserDto(
    var firstName: String?,
    var lastName: String? ,
    var email: String?,
    var password: String?,
    var userId: String?="",
    var encryptedPassword: String?="" )

