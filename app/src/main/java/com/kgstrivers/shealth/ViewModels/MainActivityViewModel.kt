package com.kgstrivers.shealth.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kgstrivers.shealth.Models.UserResponse
import com.kgstrivers.shealth.Models.Usersval
import com.kgstrivers.shealth.RetroServiceInterface
import com.kgstrivers.shealth.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel :ViewModel() {

    lateinit var createnewUserLiveData: MutableLiveData<UserResponse?>

    init {
        createnewUserLiveData = MutableLiveData()
    }


    fun getCreateNewUserObser(): MutableLiveData<UserResponse?>{
        return createnewUserLiveData
    }


    fun signupNewUser(user: Usersval)
    {
       val retrofitInstance =  RetrofitInstance.getRetroFitInstance().create(RetroServiceInterface::class.java)
       val callInInstance =  retrofitInstance.createAccount(user)

        callInInstance.enqueue(object : Callback<UserResponse>{


            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful)
                {
                    createnewUserLiveData.postValue(response.body())
                }
                else{
                    createnewUserLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createnewUserLiveData.postValue(null)
            }


        })
    }
}