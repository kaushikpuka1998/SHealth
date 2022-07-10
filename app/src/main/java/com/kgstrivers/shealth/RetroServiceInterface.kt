package com.kgstrivers.shealth

import com.kgstrivers.shealth.Models.UserResponse
import com.kgstrivers.shealth.Models.Usersval
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("signup")
    fun createAccount(@Body params: Usersval) : Call<UserResponse>
}