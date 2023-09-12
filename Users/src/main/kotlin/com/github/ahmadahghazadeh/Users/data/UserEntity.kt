package com.github.ahmadahghazadeh.users.data

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Int?,

    @Column(nullable=false, length = 50)
    val firstName: String,
    @Column(nullable=false, length = 50)
    val lastName:String,
    @Column(nullable=false, length = 120, unique = true)
    val email:String,
    @Column(nullable=false, unique = true)
    val userid:String,
    @Column(nullable=false)
    var encryptedPassword:String,
) {
    protected constructor() : this(null, "", "", "", "", "")
}