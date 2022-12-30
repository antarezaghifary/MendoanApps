package com.skripsi.mendoanapps.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.databinding.ActivitySplashScreenBinding
import com.skripsi.mendoanapps.ui.home.HomeActivity
import com.skripsi.mendoanapps.ui.login.LoginActivity
import com.skripsi.mendoanapps.util.SharedPreference

class SplashScreenActivity : AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by viewBinding()
    private var isLoggin: Boolean? = null
    private val sharedPref: SharedPreference by lazy {
        SharedPreference(this@SplashScreenActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        isLoggin = sharedPref.getValueBoolean("isLoggin", false)
        initStatusLogin()
    }

    private fun initStatusLogin() {
        Handler().postDelayed({
            if (isLoggin!!.equals(true)) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}