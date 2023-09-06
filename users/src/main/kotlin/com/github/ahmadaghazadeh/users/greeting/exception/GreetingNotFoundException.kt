package com.github.ahmadaghazadeh.users.greeting.exception

class GreetingNotFoundException(val courseId: String) : RuntimeException("Course not found $courseId") {

}
