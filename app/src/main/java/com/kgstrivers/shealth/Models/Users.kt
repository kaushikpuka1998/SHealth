package com.kgstrivers.shealth.Models

data class Usersval(val name:String? ,val email:String? ,val password:String?,val phone:String?)
data class UserResponse(val success:Boolean? ,val message: String? ,  val data:DataClass?)
data class DataClass(val name: String?, val email: String?,val phone: String?,val hash: String?)

data class Loginuser(val email: String?,val password: String?)
data class LoginuserResponse(val result:Boolean? , val message:String? ,val accesstoken:String?)
