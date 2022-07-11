package com.kgstrivers.shealth.RetroDetails

import com.kgstrivers.shealth.Models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("signup")
    fun createAccount(@Body params: Usersval) : Call<UserResponse>


    @POST("login")
    fun LoginAccount(@Body params: Loginuser) : Call<LoginuserResponse>


    @POST("logout")
    fun LogOutAccount() :Call<LogoutResponse>
}