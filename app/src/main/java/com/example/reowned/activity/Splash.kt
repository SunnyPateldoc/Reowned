package com.example.reowned.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.reowned.R

class Splash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({

            startActivity(Intent(applicationContext, UserLogin::class.java))
            finish()

        }, 2000)


    }

}