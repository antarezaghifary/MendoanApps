package com.skripsi.mendoanapps.ui.aktivitas

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityAktivitasByDateBinding
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention
import java.util.*

class AktivitasByDateActivity : AppCompatActivity() {

    private val binding: ActivityAktivitasByDateBinding by viewBinding()
    private val viewModel: AktivitasDateViewModel by viewModels()
    private val adapter: AktivitasDateAdapter by lazy {
        AktivitasDateAdapter {}
    }
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@AktivitasByDateActivity)
    }

    lateinit var fullname: String
    lateinit var dateMulai: String
    lateinit var dateSelesai: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initListener()
        initObserver()
        setObserver()
    }

    private fun initUI() {
        binding.apply {
            swipeRefresh.isRefreshing = false
            swipeRefresh.isEnabled = false
        }
    }

    private fun initListener() {
        binding.apply {
            etTanggalMulai.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AktivitasByDateActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
//                        dateMulai = "$dayOfMonth-$month-$year"
                        dateMulai = "$year-$month-$dayOfMonth"
                        etTanggalMulai.setText(dateMulai)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }

            etTanggalSelesai.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AktivitasByDateActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
//                        dateSelesai = "$dayOfMonth-$month-$year"
                        dateSelesai = "$year-$month-$dayOfMonth"
                        etTanggalSelesai.setText(dateSelesai)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }

            btAddCuti.setOnClickListener {
                fullname = etFullname.text.toString()
                viewModel.dataActivity(
                    karyawan = fullname,
                    dateassignment = dateMulai,
                    endassignment = dateSelesai
                )
            }
        }
    }

    private fun initObserver() {

    }

    private fun setObserver() {
        viewModel.getActivityByDate.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    progressDialog.start("Loding")
                    binding.swipeRefresh.isRefreshing = true
                }
                is VmData.Success -> {
                    progressDialog.stop()
                    binding.swipeRefresh.isRefreshing = false
                    adapter.addAll(it.data)
                    binding.rvAktivitas.adapter = adapter
                    binding.rvAktivitas.layoutManager = LinearLayoutManager(this)
                }
                is VmData.Failure -> {
                    progressDialog.stop()
                    binding.swipeRefresh.isRefreshing = false
                    toast("${it.message}")
                }
                else -> {}
            }
        }
    }
}