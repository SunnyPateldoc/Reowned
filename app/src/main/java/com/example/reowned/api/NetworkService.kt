package com.example.reowned.api

import com.example.reowned.model.LoginRequest
import com.example.reowned.model.LoginSuccess
import com.example.reowned.model.ProductList
import com.example.reowned.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkService {

    @GET("products")
    fun getAllProduct() : Call<ProductList>

    @GET("users/{id}")
    fun getUsers(@Path("id") id : String) : Call<User>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginSuccess>

    @POST("users/add")
    fun addUser(@Body userData : User) : Call<User>

}