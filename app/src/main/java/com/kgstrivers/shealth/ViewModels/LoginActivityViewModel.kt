package com.kgstrivers.shealth.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kgstrivers.shealth.Models.Loginuser
import com.kgstrivers.shealth.Models.LoginuserResponse
import com.kgstrivers.shealth.RetroDetails.RetroServiceInterface
import com.kgstrivers.shealth.RetroDetails.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel:ViewModel() {

    lateinit var loginuserLiveData: MutableLiveData<LoginuserResponse?>


    init {
        loginuserLiveData = MutableLiveData()
    }

    fun loginuserobserver() : MutableLiveData<LoginuserResponse?>
    {
        return loginuserLiveData
    }


    fun loginuser(user: Loginuser)
    {
        val retrofitInstance =  RetrofitInstance.getRetroFitInstance().create(RetroServiceInterface::class.java)
        val callInInstance =  retrofitInstance.LoginAccount(user);

        callInInstance.enqueue(object : Callback<LoginuserResponse> {


            override fun onResponse(call: Call<LoginuserResponse>, response: Response<LoginuserResponse>) {
                if(response.isSuccessful)
                {
                    loginuserLiveData.postValue(response.body())
                }
                else{
                    loginuserLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<LoginuserResponse>, t: Throwable) {
                loginuserLiveData.postValue(null)
            }


        })
    }
}