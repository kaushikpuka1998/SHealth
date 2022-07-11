package com.kgstrivers.shealth.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.kgstrivers.shealth.Models.Loginuser
import com.kgstrivers.shealth.Models.LoginuserResponse
import com.kgstrivers.shealth.R
import com.kgstrivers.shealth.ViewModels.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    lateinit var viewmodel:LoginActivityViewModel
    lateinit var privateaccesstoken:String
    val bundle2 = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val preferences: SharedPreferences =
            applicationContext.getSharedPreferences("MY_APP", MODE_PRIVATE)
        val retrivedToken = preferences.getString("TOKEN", null)

        if(retrivedToken!=null)
        {
            var iy = Intent(applicationContext,DataActivity::class.java)
            startActivity(iy)
        }else{
            initiateviewmodel()

            loginbutton.setOnClickListener {
                loginuser();
            }
            noaacount.setOnClickListener{
                val loginintent = Intent(this@LoginActivity ,MainActivity::class.java)
                startActivity((loginintent))

            }
        }
    }

    private fun loginuser() {
        val userval =Loginuser(emailedittext.text.toString() ,passwordEditText.text.toString())
        viewmodel.loginuser(userval)
    }

    private fun initiateviewmodel()
    {
        viewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        viewmodel.loginuserobserver().observe(this, Observer<LoginuserResponse?> {
            if(it!=null)
            {
                if(it.result==true && it.message == "Already LoggedIn")
                {
                    Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
                    var iy = Intent(applicationContext,DataActivity::class.java)
                    startActivity(iy)
                }
                else if(it.message == "Logged In Successfully" ){
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_LONG).show()
                    privateaccesstoken = it.accesstoken.toString()
                    val preferences: SharedPreferences =
                       applicationContext.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
                    preferences.edit().putString("TOKEN", privateaccesstoken).apply()
                    var iy = Intent(applicationContext,DataActivity::class.java)
                    startActivity(iy)
                }
                else
                {
                    Toast.makeText(this,"Login Issue! Either Password wrong or Emaii Wrong", Toast.LENGTH_LONG).show()
                }


            }
            else{
                Toast.makeText(this,"Login In Unsuccessful", Toast.LENGTH_LONG).show()
                //bundle2.putInt("Signup_successful", 5)
            }
        })
    }
}