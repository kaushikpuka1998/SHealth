package com.kgstrivers.shealth.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kgstrivers.shealth.R
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        logoutbutton.setOnClickListener {



            //Accesstoken storing privately
            val preferences: SharedPreferences =
                applicationContext.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            preferences.edit().putString("TOKEN", null).apply()
            var b = Intent(this@DataActivity , LoginActivity::class.java)
            startActivity(b);
        }
    }
}