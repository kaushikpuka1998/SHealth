package com.kgstrivers.shealth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.kgstrivers.shealth.Models.UserResponse
import com.kgstrivers.shealth.Models.Usersval
import com.kgstrivers.shealth.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    lateinit var viewmodel:MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        analytics = FirebaseAnalytics.getInstance(this)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.METHOD, "method")
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)


        val bundle2 = Bundle()
        bundle2.putInt("valid_item1", 5)
        bundle2.putInt("valid_item2", 6)
        bundle2.putInt("valid_item3", 7)
        bundle2.putInt("expired_item1", 8)
        bundle2.putInt("expired_item2", 9)
        bundle2.putInt("expired_item3", 10)
        analytics.logEvent("Logged_In_State", bundle2)

        initiateviewmodel()
        signupbutton.setOnClickListener{
            signupUser()
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
                Toast.makeText(this,"Sign Up Unsuccessful",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_LONG).show()
            }
        })
    }
}
