package com.skripsi.mendoanapps.ui.aktivitas

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
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
    
    private var dateMulai: String? = null
    private var dateSelesai: String? = null

    private val dataKaryawan: ArrayList<String> = ArrayList()
    private var dataFullname: String? = null

    private val dataku: ArrayList<GetKaryawanResponseItem>? = ArrayList()
    private var idNama: String? = null

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

                val dataName = idNama
                val dataMulai = dateMulai
                val dataSelesai = dateSelesai

                val checkNullData =
                    dataMulai?.isNotEmpty() ?: true && dataSelesai?.isNotEmpty() ?: true

                if (checkNullData) {
                    viewModel.dataActivity(
                        karyawan = dataName ?: "",
                        dateassignment = dataMulai ?: "",
                        endassignment = dataSelesai ?: ""
                    )
                } else {
                    toast("Isi Dulu")
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.dataKaryawan(userRole = "2")
    }

    private fun setObserver() {
        viewModel.getActivityByDate.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    progressDialog.start("Loding")
                    binding.swipeRefresh.isRefreshing = true
                }
                is VmData.Success -> {
                    if (it.data != null) {
                        progressDialog.stop()
                        binding.swipeRefresh.isRefreshing = false
                        adapter.addAll(it.data)
                        binding.rvAktivitas.adapter = adapter
                        binding.rvAktivitas.layoutManager = LinearLayoutManager(this)
                    } else {
                        progressDialog.stop()
                        binding.swipeRefresh.isRefreshing = false
                        binding.rvAktivitas.visibility = View.GONE
                        binding.tvDataNull.visibility = View.VISIBLE
                    }

                }
                is VmData.Failure -> {
                    progressDialog.stop()
                    binding.swipeRefresh.isRefreshing = false
                    toast("${it.message}")
                }
                else -> {}
            }
        }
        viewModel.getKaryawan.observe(this@AktivitasByDateActivity) {
            when (it) {
                is VmData.Loading -> {
                    progressDialog.start("Loading")
                }
                is VmData.Success -> {
                    progressDialog.stop()
                    it.data.forEach {
                        dataKaryawan.add(it.fullname!!)
                        dataku?.addAll(listOf(it))
                        setDataSpinnerDivisi()
                    }
                }
                is VmData.Failure -> {
                    progressDialog.stop()
                }
            }
        }
    }

    private fun setDataSpinnerDivisi() {
        binding.apply {

            val adapter = ArrayAdapter(
                this@AktivitasByDateActivity,
                R.layout.simple_spinner_item, dataKaryawan
            )
            spFullname.adapter = adapter

            spFullname.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataFullname = dataku?.get(position)?.fullname //dataKaryawan[position]
                    idNama = dataku?.get(position)?.userId
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}