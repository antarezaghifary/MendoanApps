package com.skripsi.mendoanapps.ui.karyawan

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityDataKaryawanBinding
import com.skripsi.mendoanapps.util.VmData

class DataKaryawanActivity : AppCompatActivity() {

    private val binding: ActivityDataKaryawanBinding by viewBinding()
    private val viewModel: DataKaryawanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initListener()
        initObserver()
        setObserver()
    }

    private fun initUI() {
        binding.apply {

        }
    }

    private fun initListener() {

    }

    private fun initObserver() {
        viewModel.dataKaryawan()
    }

    private fun setObserver() {
        viewModel.getKaryawan.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    toast("Loading...")
                }
                is VmData.Success -> {
                    Log.e("TAG", "Get data karyawan: ${it.data}")
                }
                is VmData.Failure -> {
                    toast("${it.message}")
                }
            }
        }
    }
}