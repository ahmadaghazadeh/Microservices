package com.github.ahmadahghazadeh.users.data

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Int?,

    @Column(nullable=false, length = 50)
    var firstName: String?,
    @Column(nullable=false, length = 50)
    var lastName:String?,
    @Column(nullable=false, length = 120, unique = true)
    var email:String?,
    @Column(nullable=false, unique = true)
    var userId:String?,
    @Column(nullable=false)
    var encryptedPassword:String?,
) {
    protected constructor() : this(null, "", "", "", "", "")
}