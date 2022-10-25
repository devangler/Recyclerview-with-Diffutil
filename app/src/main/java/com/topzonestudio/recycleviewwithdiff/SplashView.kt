package com.topzonestudio.recycleviewwithdiff

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_view)
        supportActionBar?.hide()

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashView, PermissionActivity::class.java)
            startActivity(mainIntent)
            finish()
        },1000)
    }
}