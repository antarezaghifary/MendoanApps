package com.skripsi.mendoanapps.ui.add_cuti

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.databinding.ActivityAddKaryawanCutiBinding
import com.skripsi.mendoanapps.ui.home.HomeActivity
import com.skripsi.mendoanapps.util.SharedPreference
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention
import java.util.*

class AddKaryawanCutiActivity : AppCompatActivity() {

    private val binding: ActivityAddKaryawanCutiBinding by viewBinding()
    private val viewModel: AddCutiViewModel by viewModels()
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@AddKaryawanCutiActivity)
    }
    private var dataFullname: String? = null
    private val sharedPref: SharedPreference by lazy {
        SharedPreference(this@AddKaryawanCutiActivity)
    }
    private val dataKaryawan: ArrayList<String> = ArrayList()
    private val dataku: ArrayList<GetKaryawanResponseItem>? = ArrayList()
    private var idNama: String? = null


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
                    is VmData.Loading -> {
                        progressDialog.start("Loading")
                    }
                    is VmData.Success -> {
                        progressDialog.stop()
                        toast(it.data.message.toString())
                        if (it.data.sTATUS.equals("SUCCESS")) {
                            intent = Intent(this@AddKaryawanCutiActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    is VmData.Failure -> {
                        progressDialog.stop()
                        toast(it.message.toString())
                    }
                    else -> {}
                }
            }

            viewModel.getKaryawan.observe(this@AddKaryawanCutiActivity) {
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
    }

    private fun initObserver() {
        viewModel.dataKaryawan(userRole = "2")
    }

    private fun initUI() {

    }

    private fun setDataSpinnerDivisi() {
        binding.apply {

            val adapter = ArrayAdapter(
                this@AddKaryawanCutiActivity,
                android.R.layout.simple_spinner_item, dataKaryawan
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
//                    dataFullname = dataKaryawan[position]

                    dataFullname = dataku?.get(position)?.fullname
                    idNama = dataku?.get(position)?.userId

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
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
                    resource_name = dataFullname!!, //etFullname.text.toString(),
                    tanggal = date ?: "",
                    keterangan = etKeterangan.text.toString()
                )
            }
        }
    }
}