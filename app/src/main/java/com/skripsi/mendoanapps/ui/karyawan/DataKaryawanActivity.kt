package com.skripsi.mendoanapps.ui.karyawan

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityDataKaryawanBinding
import com.skripsi.mendoanapps.util.VmData

class DataKaryawanActivity : AppCompatActivity() {

    private val binding: ActivityDataKaryawanBinding by viewBinding()
    private val viewModel: DataKaryawanViewModel by viewModels()
    private val adapter: DataKaryawanAdapter by lazy {
        DataKaryawanAdapter {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initListener()
        initObserver()
        setObserver()
    }

    private fun initUI() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.dataKaryawan(userRole = "2")

                adapter.notifyItemRemoved(0)
                adapter.notifyDataSetChanged()

                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initListener() {

    }

    private fun initObserver() {
        viewModel.dataKaryawan(userRole = "2")
    }

    private fun setObserver() {
        viewModel.getKaryawan.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    toast("Loading...")
                    binding.swipeRefresh.isRefreshing = true
                }
                is VmData.Success -> {
                    Log.e("TAG", "Get data karyawan: ${it.data}")
                    binding.swipeRefresh.isRefreshing = false
                    adapter.addAll(it.data)
                    binding.recyclerViewApprove.adapter = adapter
                    binding.recyclerViewApprove.layoutManager = LinearLayoutManager(this)
                }
                is VmData.Failure -> {
                    binding.swipeRefresh.isRefreshing = false
                    toast("${it.message}")
                }
            }
        }
    }
}