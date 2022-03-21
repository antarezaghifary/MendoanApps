package com.skripsi.mendoanapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.Home.HomeAdapter
import com.skripsi.mendoanapps.data.MenuApps
import com.skripsi.mendoanapps.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by viewBinding()
    private val adapter: HomeAdapter by lazy {
        HomeAdapter{

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){

            val listHeroes = listOf(
                MenuApps("List Karyawan"),
                MenuApps("Karyawan Resign"),
                MenuApps("Karyawan Cuti"),
                MenuApps("Karyawan Mutasi"),
                MenuApps("Tambah Karyawan"),
                MenuApps("Tambah Cuti"),
                MenuApps("Challenge Position"),
                MenuApps("Tambah Challange"),
                MenuApps("Edit Karyawan"),
                MenuApps("Karyawan Resign")
            )


            adapter.addAll(listHeroes)

            rvMain.adapter = adapter
            rvMain.apply {
                layoutManager = GridLayoutManager(this@HomeActivity, 2)
            }
        }
    }
}