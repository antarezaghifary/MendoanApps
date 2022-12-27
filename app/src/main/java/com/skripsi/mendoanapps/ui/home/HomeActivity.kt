package com.skripsi.mendoanapps.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.adapter.HomeAdapter
import com.skripsi.mendoanapps.data.model.menu_app.MenuApps
import com.skripsi.mendoanapps.databinding.ActivityHomeBinding
import com.skripsi.mendoanapps.ui.add_cuti.AddKaryawanCutiActivity
import com.skripsi.mendoanapps.ui.add_karyawan.AddKaryawanActivity
import com.skripsi.mendoanapps.ui.cuti.CutiActivity
import com.skripsi.mendoanapps.ui.karyawan.DataKaryawanActivity


class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by viewBinding()
    private val adapter: HomeAdapter by lazy {
        HomeAdapter {
            detailMenu(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenuApp()
        initListener()
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

            adapter.addAll(listHeroes)

            rvMain.adapter = adapter
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
        }
    }
}