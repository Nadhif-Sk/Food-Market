package com.example.foodmarket.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.foodmarket.R
import com.example.foodmarket.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //buat delay splash screen
        Handler().postDelayed({
           startActivity(Intent(this, AuthActivity::class.java))
            finish()
        },3000)
    }
}