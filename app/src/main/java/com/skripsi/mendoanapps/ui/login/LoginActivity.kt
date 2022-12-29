package com.skripsi.mendoanapps.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.R
import com.skripsi.mendoanapps.databinding.ActivityLoginBinding
import com.skripsi.mendoanapps.ui.home.HomeActivity
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@LoginActivity)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initListener()
        initObservable()
        setObservable()

    }

    private fun initUI() {
        binding.apply {

            setSupportActionBar(toolbarLogin)

            email.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.message, 0, 0, 0)
            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password, 0, 0, 0)

            email.addTextChangedListener(loginTextWatcher)
            password.addTextChangedListener(loginTextWatcher)

            setEmail()
            setPassword()
        }
    }

    private fun setEmail() {
        binding.apply {
            email.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun afterTextChanged(s: Editable) {
                    if (s.length != 0) {
                        var drawable =
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.message,
                                null
                            ) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable,
                            ResourcesCompat.getColor(resources, R.color.colordarkblue, null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                        email.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        email.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources, R.drawable.message, null),
                            null,
                            ResourcesCompat.getDrawable(resources, R.drawable.cancel, null),
                            null
                        )
                    } else if (s.length == 0) {
                        email.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.message,
                            0, 0, 0
                        )
                        var drawable =
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.message,
                                null
                            ) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable,
                            ResourcesCompat.getColor(resources, R.color.colorDefault, null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                        email.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        email.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources, R.drawable.message, null),
                            null, null, null
                        )
                    }
                }
            })
        }
    }

    private fun setPassword() {
        binding.apply {
            password.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun afterTextChanged(s: Editable) {
                    if (s.length != 0) {
                        var drawable =
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.password,
                                null
                            ) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable!!,
                            ResourcesCompat.getColor(resources, R.color.colordarkblue, null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                        password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        password.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources, R.drawable.password, null),
                            null,
                            ResourcesCompat.getDrawable(resources, R.drawable.cancel, null),
                            null
                        )
                    } else if (s.length == 0) {
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.password,
                            0, 0, 0
                        )
                        var drawable =
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.password,
                                null
                            ) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable!!,
                            ResourcesCompat.getColor(resources, R.color.colorDefault, null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                        password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        password.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources, R.drawable.password, null),
                            null, null, null
                        )
                    }
                }
            })

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {
            email.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (email.compoundDrawables.get(2) != null) {
                        if (event.x >= email.right - email.left -
                            email.compoundDrawables.get(2).bounds.width()
                        ) {
                            if (email.text.toString() != "") {
                                email.setText("")
                            }
                        }
                    }
                }
                false
            }

            password.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (password.compoundDrawables.get(2) != null) {
                        if (event.x >= password.right - password.left -
                            password.compoundDrawables.get(2).bounds.width()
                        ) {
                            if (password.text.toString() != "") {
                                password.setText("")
                            }
                        }
                    }
                }
                false
            }

            rememberPassword.setOnClickListener {
                if (!(binding.rememberPassword.isSelected)) {
                    binding.rememberPassword.isChecked = true
                    binding.rememberPassword.isSelected = true
                } else {
                    binding.rememberPassword.isChecked = false
                    binding.rememberPassword.isSelected = false
                }
            }
        }
    }

    private var loginTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            val mUsername: String = binding.email.text.toString().trim()
            val mPassword: String = binding.password.text.toString().trim()
            val t = !mUsername.isEmpty() && !mPassword.isEmpty()
            if (t) {
                binding.loginButton.setBackgroundResource(R.color.colordarkblue)
                binding.loginButton.isEnabled = true
                binding.loginButton.setOnClickListener {
                    viewModel.postLogin(mUsername, mPassword)
                }
            } else {
                binding.loginButton.setBackgroundResource(R.color.colorwhiteblueshade)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val mUsername: String = binding.email.text.toString().trim()
        val mPassword: String = binding.password.text.toString().trim()
        val t = !mUsername.isEmpty() && !mPassword.isEmpty()
        if (t) {
            binding.loginButton.setBackgroundResource(R.color.colordarkblue)
        } else {
            binding.loginButton.setBackgroundResource(R.color.colorwhiteblueshade)
        }
    }

    private fun initObservable() {}

    private fun setObservable() {
        viewModel.postLogin.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    progressDialog.start("Loading")
                }
                is VmData.Success -> {
                    progressDialog.stop()
                    if (it.data.message.equals("Success")) {
                        gotoHomePage()
                    } else {
                        progressDialog.stop()
                        toast(it.data.message.toString())
                    }
                }
                is VmData.Failure -> {
                    progressDialog.stop()
                }
                else -> {}
            }
        }
    }

    private fun gotoHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}