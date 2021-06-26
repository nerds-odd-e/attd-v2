package com.odde.atddv2.myorder

import feign.Headers
import feign.RequestLine

@Headers("Accept: application/json", "Content-Type: application/json")
interface Api {
    @RequestLine("POST /users/login")
    fun login(user: User): User

    data class User constructor(var userName: String?, var password: String?) {
        constructor() : this(null, null)
    }
}