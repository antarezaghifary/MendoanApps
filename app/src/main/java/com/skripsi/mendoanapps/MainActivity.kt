package com.skripsi.mendoanapps

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            setSupportActionBar(toolbarLogin)

            email.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.message, 0, 0, 0)
            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password, 0, 0, 0)


            email.addTextChangedListener(loginTextWatcher)
            password.addTextChangedListener(loginTextWatcher)

            email.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun afterTextChanged(s: Editable) {
                    if (s.length != 0) {
                        var drawable =
                            ResourcesCompat.getDrawable(resources,R.drawable.message,null) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable,
                            ResourcesCompat.getColor(resources,R.color.colordarkblue,null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                        email.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        email.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources,R.drawable.message,null),
                            null, ResourcesCompat.getDrawable(resources,R.drawable.cancel,null), null
                        )
                    } else if (s.length == 0) {
                        email.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.message,
                            0, 0, 0
                        )
                        var drawable =
                            ResourcesCompat.getDrawable(resources,R.drawable.message,null) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable,
                            ResourcesCompat.getColor(resources,R.color.colorDefault,null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                        email.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        email.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources,R.drawable.message,null),
                            null, null, null
                        )
                    }
                }
            })

            password.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun afterTextChanged(s: Editable) {
                    if (s.length != 0) {
                        var drawable =
                            ResourcesCompat.getDrawable(resources,R.drawable.password,null) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable!!,
                            ResourcesCompat.getColor(resources,R.color.colordarkblue,null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                        password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        password.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources,R.drawable.password,null),
                            null, ResourcesCompat.getDrawable(resources,R.drawable.cancel,null), null
                        )
                    } else if (s.length == 0) {
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.password,
                            0, 0, 0
                        )
                        var drawable =
                            ResourcesCompat.getDrawable(resources,R.drawable.password,null) //Your drawable image
                        drawable = DrawableCompat.wrap(drawable!!)
                        DrawableCompat.setTint(
                            drawable!!,
                            ResourcesCompat.getColor(resources,R.color.colorDefault,null)
                        ) // Set whatever color you want
                        DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                        password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        password.setCompoundDrawablesWithIntrinsicBounds(
                            ResourcesCompat.getDrawable(resources,R.drawable.password,null),
                            null, null, null
                        )
                    }
                }
            })

            email.setOnTouchListener { v, event ->

                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (email.getCompoundDrawables().get(2) != null) {
                        if (event.x >= email.getRight() - email.getLeft() -
                            email.getCompoundDrawables().get(2).getBounds().width()
                        ) {
                            if (email.getText().toString() != "") {
                                email.setText("")
                            }
                        }
                    }
                }
                false
            }

            password.setOnTouchListener { v, event ->

                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (password.getCompoundDrawables().get(2) != null) {
                        if (event.x >= password.getRight() - password.getLeft() -
                            password.getCompoundDrawables().get(2).getBounds().width()
                        ) {
                            if (password.getText().toString() != "") {
                                password.setText("")
                            }
                        }
                    }
                }
                false
            }

            binding.rememberPassword.setOnClickListener {

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

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {


        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


        }

        override fun afterTextChanged(s: Editable) {
            val mUsername: String = binding.email.getText().toString().trim()
            val mPassword: String = binding.password.getText().toString().trim()
            val t = !mUsername.isEmpty() && !mPassword.isEmpty()
            if (t) {
                binding.loginButton.setBackgroundResource(R.color.colordarkblue)
                binding.loginButton.isEnabled = true
                binding.loginButton.setOnClickListener {
                    gotoHomePage()
                }
            } else {
                binding.loginButton.setBackgroundResource(R.color.colorwhiteblueshade)
            }

        }
    }
    override fun onStart() {
        super.onStart()
        val mUsername: String = binding.email.getText().toString().trim()
        val mPassword: String = binding.password.getText().toString().trim()
        val t = !mUsername.isEmpty() && !mPassword.isEmpty()
        if (t) {
            binding.loginButton.setBackgroundResource(R.color.colordarkblue)
        } else {
            binding.loginButton.setBackgroundResource(R.color.colorwhiteblueshade)
        }
    }

    private fun gotoHomePage(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}