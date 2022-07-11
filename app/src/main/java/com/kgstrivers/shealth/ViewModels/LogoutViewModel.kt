package com.kgstrivers.shealth.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kgstrivers.shealth.Models.LoginuserResponse
import com.kgstrivers.shealth.Models.LogoutResponse
import com.kgstrivers.shealth.RetroDetails.RetroServiceInterface
import com.kgstrivers.shealth.RetroDetails.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogoutViewModel:ViewModel() {

    lateinit var logoutuserLiveData: MutableLiveData<LogoutResponse?>


    init {
        logoutuserLiveData = MutableLiveData()
    }

    fun loginuserobserver() : MutableLiveData<LogoutResponse?>
    {
        return logoutuserLiveData
    }

    fun logout()
    {
        val retrofitInstance =  RetrofitInstance.getRetroFitInstance().create(RetroServiceInterface::class.java)
        val callInInstance =  retrofitInstance.LogOutAccount();

        callInInstance.enqueue(object : Callback<LogoutResponse> {


            override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                if(response.isSuccessful)
                {
                    logoutuserLiveData.postValue(response.body())
                }
                else{
                    logoutuserLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                logoutuserLiveData.postValue(null)
            }


        })
    }

}