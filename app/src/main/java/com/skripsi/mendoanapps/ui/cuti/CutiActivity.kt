package com.skripsi.mendoanapps.ui.cuti

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityCutiBinding
import com.skripsi.mendoanapps.util.VmData


class CutiActivity : AppCompatActivity() {

    private val binding: ActivityCutiBinding by viewBinding()
    private val viewModel: CutiViewModel by viewModels()
    private val adapter: CutiAdapter by lazy {
        CutiAdapter {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initObserver()
        initListener()
        setObserver()
    }

    private fun initObserver() {
        viewModel.dataCuti()
    }

    private fun initUI() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.dataCuti()

                adapter.notifyItemRemoved(0)
                adapter.notifyDataSetChanged()

                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initListener() {

    }

    private fun setObserver() {
        viewModel.apply {
            cuti.observe(this@CutiActivity) {
                when (it) {
                    is VmData.Loading -> {
                        binding.swipeRefresh.isRefreshing = true
                        toast("Loading")
                    }
                    is VmData.Success -> {
                        toast("Success")
                        binding.swipeRefresh.isRefreshing = false
                        adapter.addAll(it.data)
                        binding.rvCuti.adapter = adapter
                        binding.rvCuti.layoutManager = LinearLayoutManager(this@CutiActivity)
                    }
                    is VmData.Failure -> {
                        binding.swipeRefresh.isRefreshing = true
                        toast(it.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }
}