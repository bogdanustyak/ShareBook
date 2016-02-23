package com.leoart.sharebook.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.leoart.sharebook.Constants

import com.leoart.sharebook.R
import com.leoart.sharebook.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        val runnable = Runnable {
            if(getSharedPreferences(Constants.SHARED_KEY, MODE_PRIVATE).contains(Constants.FACEBOOK_MODELFIELD_USER_NAME)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        handler.postDelayed(runnable, 2000)
    }
}
