package com.skripsi.mendoanapps.ui.idle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityKaryawanIdleBinding
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention

class KaryawanIdleActivity : AppCompatActivity() {

    private val binding: ActivityKaryawanIdleBinding by viewBinding()
    private val viewModel: KaryawanIdleViewModel by viewModels()
    private val adapter: KaryawanIdleAdapter by lazy {
        KaryawanIdleAdapter {}
    }
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@KaryawanIdleActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initListener()
        initObserver()
    }

    private fun initUI() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.dataIdle()

                adapter.notifyItemRemoved(0)
                adapter.notifyDataSetChanged()

                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initListener() {
        viewModel.dataIdle()
    }

    private fun initObserver() {
        viewModel.apply {
            idle.observe(this@KaryawanIdleActivity) {
                when (it) {
                    is VmData.Loading -> {
                        progressDialog.start("Loading")
                        binding.swipeRefresh.isRefreshing = true
                    }
                    is VmData.Success -> {
                        progressDialog.stop()
                        binding.swipeRefresh.isRefreshing = false
                        adapter.addAll(it.data)
                        binding.rvIdle.adapter = adapter
                        binding.rvIdle.layoutManager =
                            LinearLayoutManager(this@KaryawanIdleActivity)
                    }
                    is VmData.Failure -> {
                        progressDialog.stop()
                        binding.swipeRefresh.isRefreshing = false
                        toast(it.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }
}