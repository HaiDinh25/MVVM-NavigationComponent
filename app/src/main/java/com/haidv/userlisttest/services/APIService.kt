package com.haidv.userlisttest.services

import com.haidv.userlisttest.user.data.User
import com.haidv.userlisttest.userdetail.data.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/users")
    suspend fun getUser(): List<User>

    @GET("users/{ID}")
    suspend fun getUserDetail(@Path("ID") id: Int): UserDetail
}