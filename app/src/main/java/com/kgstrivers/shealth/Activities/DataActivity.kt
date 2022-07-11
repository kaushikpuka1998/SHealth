package com.kgstrivers.shealth.Activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kgstrivers.shealth.Models.Loginuser
import com.kgstrivers.shealth.Models.LoginuserResponse
import com.kgstrivers.shealth.Models.LogoutResponse
import com.kgstrivers.shealth.R
import com.kgstrivers.shealth.ViewModels.LoginActivityViewModel
import com.kgstrivers.shealth.ViewModels.LogoutViewModel
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_login.*

class DataActivity : AppCompatActivity() {
    lateinit var viewmodel: LogoutViewModel
    lateinit var  progressDialog: ProgressDialog

    private var backPressedtime: Long = 0
    override fun onBackPressed() { //Double Back Pressed  and Exit Function
        if (backPressedtime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            val intnt = Intent(Intent.ACTION_MAIN)
            intnt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intnt.addCategory(Intent.CATEGORY_HOME)
            startActivity(intnt)

        } else {
            Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressedtime = System.currentTimeMillis()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        progressDialog = ProgressDialog(this@DataActivity)
        progressDialog.setTitle("Logging Out...")
        progressDialog.setMessage("please wait")

        initiateviewmodel();

        logoutbutton.setOnClickListener {


            progressDialog.show()
            logoutuser()

        }
    }

    private fun logoutuser() {
        viewmodel.logout()
    }
    private fun initiateviewmodel()
    {
        viewmodel = ViewModelProvider(this).get(LogoutViewModel::class.java)
        viewmodel.loginuserobserver().observe(this, Observer<LogoutResponse?> {
            if(it!=null)
            {
                if(it.success == true)
                {
                    Toast.makeText(this@DataActivity, it.message, Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@DataActivity, it.message+" through API", Toast.LENGTH_SHORT).show()
                }

                val preferences: SharedPreferences =
                    applicationContext.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
                preferences.edit().putString("TOKEN", null).apply()
                progressDialog.hide()
                //Accesstoken removing privately
                var b = Intent(this@DataActivity , LoginActivity::class.java)
                startActivity(b);



            }
            else{
                progressDialog.hide()
                Toast.makeText(this,"Login In Unsuccessful", Toast.LENGTH_LONG).show()
                //bundle2.putInt("Signup_successful", 5)
            }
        })
    }
}