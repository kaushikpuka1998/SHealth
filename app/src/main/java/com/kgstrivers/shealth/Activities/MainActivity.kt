package com.kgstrivers.shealth.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.kgstrivers.shealth.Models.UserResponse
import com.kgstrivers.shealth.Models.Usersval
import com.kgstrivers.shealth.R
import com.kgstrivers.shealth.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    lateinit var viewmodel:MainActivityViewModel
    val bundle2 = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        analytics = FirebaseAnalytics.getInstance(this)

        bundle2.putInt("Entry_at_Sign_Up_Page",1)
        initiateviewmodel()
        signupbutton.setOnClickListener{
            bundle2.putInt("Sign_Up_Button_Clicked", 1)
            signupUser()
            analytics.logEvent("Sign_UP_page", bundle2)
        }



    }

    private fun signupUser() {
        val userval = Usersval(editTextName.text.toString(),editTextEmail.text.toString() ,edittextpassword.text.toString(),editTextPhone.text.toString())
        viewmodel.signupNewUser(userval)
    }

    private fun initiateviewmodel()
    {
        viewmodel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewmodel.getCreateNewUserObser().observe(this, Observer<UserResponse?> {
            if(it == null)
            {
                bundle2.putInt("Signup_unSuccessful", 4)
                Toast.makeText(this,"Sign Up Unsuccessful",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_LONG).show()
                bundle2.putInt("Signup_successful", 5)
            }
        })
    }
}
