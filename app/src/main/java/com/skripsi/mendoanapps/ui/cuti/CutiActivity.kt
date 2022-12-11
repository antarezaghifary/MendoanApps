package com.skripsi.mendoanapps.ui.cuti

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.databinding.ActivityCutiBinding
import java.util.*


class CutiActivity : AppCompatActivity() {

    private val binding: ActivityCutiBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initListener()
        setObserver()
    }

    private fun initUI() {
        setCalender()
    }

    private fun setCalender() {
        binding.apply {
            etTanggal.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()

                var date: String
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@CutiActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
                        date = "$dayOfMonth-$month-$year"
                        etTanggal.setText(date)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
        }
    }

    private fun initListener() {

    }

    private fun setObserver() {
    }
}