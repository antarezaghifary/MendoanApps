package com.skripsi.mendoanapps.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.adapter.HomeAdapter
import com.skripsi.mendoanapps.data.model.menu_app.MenuApps
import com.skripsi.mendoanapps.databinding.ActivityHomeBinding
import com.skripsi.mendoanapps.ui.add_cuti.AddKaryawanCutiActivity
import com.skripsi.mendoanapps.ui.add_karyawan.AddKaryawanActivity
import com.skripsi.mendoanapps.ui.aktivitas.AktivitasByDateActivity
import com.skripsi.mendoanapps.ui.cuti.CutiActivity
import com.skripsi.mendoanapps.ui.idle.KaryawanIdleActivity
import com.skripsi.mendoanapps.ui.karyawan.DataKaryawanActivity
import com.skripsi.mendoanapps.ui.list_project.ListProyekActivity
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention


class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by viewBinding()
    private val viewModel: HomeLasttenViewModel by viewModels()
    private val adapterHome: HomeAdapter by lazy {
        HomeAdapter {
            detailMenu(it)
        }
    }
    private val adapterLastten: HomeLasttenAdapter by lazy {
        HomeLasttenAdapter {}
    }
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@HomeActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initObserver()
        setObserver()
        getMenuApp()
        initListener()
    }

    private fun initUI() {

    }

    private fun initObserver() {
        viewModel.dataLastter("2")
    }

    private fun setObserver() {
        viewModel.getLastten.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    progressDialog.start("Loading")
                }
                is VmData.Success -> {
                    progressDialog.stop()
                    adapterLastten.addAll(it.data)
                    binding.rvLastten.adapter = adapterLastten
                    binding.rvLastten.layoutManager = LinearLayoutManager(this)
                    binding.rvLastten.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    binding.rvLastten.isNestedScrollingEnabled = true
                    binding.rvLastten.setHasFixedSize(true)
                }
                is VmData.Failure -> {
                    progressDialog.stop()
                }
                else -> {}
            }
        }
    }

    private fun initListener() {
        binding.apply {
            ivLogout.setOnClickListener {
                Toast.makeText(this@HomeActivity, "Logout", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getMenuApp() {
        binding.apply {
            val listHeroes = listOf(
                MenuApps("Daftar Karyawan"),
                MenuApps("Tambah Karyawan"),
                MenuApps("Daftar Cuti"),
                MenuApps("Tambah Cuti"),
                MenuApps("Daftar Proyek"),
                MenuApps("Aktifitas berdasarkan tanggal"),
                MenuApps("Karyawan Idle"),
                MenuApps("Timesheet")
            )

            adapterHome.addAll(listHeroes)

            rvMain.adapter = adapterHome
            rvMain.apply {
                layoutManager = GridLayoutManager(this@HomeActivity, 2)
            }
        }
    }

    private fun detailMenu(menu: MenuApps) {
        if (menu.menu == "Daftar Karyawan") {
            val intent = Intent(this@HomeActivity, DataKaryawanActivity::class.java)
            startActivity(intent)
        } else if (menu.menu == "Tambah Karyawan") {
            val intent = Intent(this@HomeActivity, AddKaryawanActivity::class.java)
            startActivity(intent)
        } else if (menu.menu == "Tambah Cuti") {
            val intent = Intent(this@HomeActivity, AddKaryawanCutiActivity::class.java)
            startActivity(intent)
        } else if (menu.menu == "Daftar Cuti") {
            val intent = Intent(this@HomeActivity, CutiActivity::class.java)
            startActivity(intent)
        } else if (menu.menu == "Karyawan Idle") {
            val intent = Intent(this@HomeActivity, KaryawanIdleActivity::class.java)
            startActivity(intent)
        } else if (menu.menu == "Aktifitas berdasarkan tanggal") {
            val intent = Intent(this@HomeActivity, AktivitasByDateActivity::class.java)
            startActivity(intent)
        } else if (menu.menu == "Daftar Proyek") {
            val intent = Intent(this@HomeActivity, ListProyekActivity::class.java)
            startActivity(intent)
        }
    }
}