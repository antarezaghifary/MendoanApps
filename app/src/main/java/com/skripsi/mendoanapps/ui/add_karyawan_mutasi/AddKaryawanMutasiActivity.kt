package com.skripsi.mendoanapps.ui.add_karyawan_mutasi

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.R
import com.skripsi.mendoanapps.databinding.ActivityAddKaryawanMutasiBinding

class AddKaryawanMutasiActivity : AppCompatActivity() {

    private val binding: ActivityAddKaryawanMutasiBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initListener()
        setObserver()
    }

    private fun initUI() {
        setDataSpinner()
    }

    private fun setDataSpinner() {
        binding.apply {

            val languages = resources.getStringArray(R.array.posisi)

            val adapter = ArrayAdapter(
                this@AddKaryawanMutasiActivity,
                android.R.layout.simple_spinner_item, languages
            )
            spMutasi.adapter = adapter

            spMutasi.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@AddKaryawanMutasiActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun initListener() {

    }

    private fun setObserver() {

    }
}