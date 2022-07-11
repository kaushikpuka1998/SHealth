package com.kgstrivers.shealth.RetroDetails

import com.kgstrivers.shealth.Models.Loginuser
import com.kgstrivers.shealth.Models.LoginuserResponse
import com.kgstrivers.shealth.Models.UserResponse
import com.kgstrivers.shealth.Models.Usersval
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("signup")
    fun createAccount(@Body params: Usersval) : Call<UserResponse>


    @POST("login")
    fun LoginAccount(@Body params: Loginuser) : Call<LoginuserResponse>
}