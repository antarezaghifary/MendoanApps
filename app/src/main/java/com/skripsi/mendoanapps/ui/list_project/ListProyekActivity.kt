package com.skripsi.mendoanapps.ui.list_project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.databinding.ActivityListProyekBinding
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention

class ListProyekActivity : AppCompatActivity() {

    private val binding: ActivityListProyekBinding by viewBinding()
    private val viewModel: ListProyekViewModel by viewModels()
    private val adapter: ListProyekAdapter by lazy {
        ListProyekAdapter {}
    }
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@ListProyekActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initListener()
        initObserver()
        setObserver()
    }

    private fun setObserver() {
        viewModel.apply {
            listProyek.observe(this@ListProyekActivity) {
                when (it) {
                    is VmData.Loading -> {
                        progressDialog.start("Loading")
                        binding.swipeRefresh.isRefreshing = true
                    }
                    is VmData.Success -> {
                        progressDialog.stop()
                        binding.swipeRefresh.isRefreshing = false
                        adapter.addAll(it.data)
                        binding.rvListProyek.adapter = adapter
                        binding.rvListProyek.layoutManager =
                            LinearLayoutManager(this@ListProyekActivity)
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

    private fun initUI() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.dataListProyek()

                adapter.notifyItemRemoved(0)
                adapter.notifyDataSetChanged()

                swipeRefresh.isRefreshing = false
            }
        }

    }

    private fun initListener() {

    }

    private fun initObserver() {
        viewModel.dataListProyek()
    }
}