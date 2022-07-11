package com.kgstrivers.shealth.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kgstrivers.shealth.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        noaacount.setOnClickListener{
            val loginintent = Intent(this@LoginActivity ,MainActivity::class.java)
            startActivity((loginintent))

        }
    }
}