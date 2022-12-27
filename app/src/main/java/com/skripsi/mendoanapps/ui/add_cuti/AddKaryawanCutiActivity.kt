package com.skripsi.mendoanapps.ui.add_cuti

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityAddKaryawanCutiBinding
import com.skripsi.mendoanapps.util.VmData
import java.util.*

class AddKaryawanCutiActivity : AppCompatActivity() {

    private val binding: ActivityAddKaryawanCutiBinding by viewBinding()
    private val viewModel: AddCutiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initListener()
        initObserver()
        setObserver()
    }

    private fun setObserver() {
        viewModel.apply {
            postCuti.observe(this@AddKaryawanCutiActivity) {
                when (it) {
                    is VmData.Loading -> toast("Loading")
                    is VmData.Success -> toast(it.data.message.toString())
                    is VmData.Failure -> toast(it.message.toString())
                    else -> {}
                }
            }
        }
    }

    private fun initObserver() {

    }

    private fun initUI() {

    }

    private fun initListener() {
        binding.apply {
            var date: String? = null
            etTanggal.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AddKaryawanCutiActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
                        date = "$dayOfMonth-$month-$year"
                        etTanggal.setText(date)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }

            btAddCuti.setOnClickListener {
                viewModel.postCuti(
                    resource_name = etFullname.text.toString(),
                    tanggal = date ?: "",
                    keterangan = etKeterangan.text.toString()
                )
            }
        }
    }
}