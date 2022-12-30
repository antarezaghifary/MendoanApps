package com.skripsi.mendoanapps.ui.add_challenge_karyawan

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.skripsi.mendoanapps.R
import com.skripsi.mendoanapps.databinding.ActivityAddChallengeKaryawanBinding
import java.util.*

class AddChallengeKaryawanActivity : AppCompatActivity(), ChallengeKaryawan {

    private val binding: ActivityAddChallengeKaryawanBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initListener()
        setObserver()
    }

    private fun initUI() {

        setDataSpinnerChallenge()
        setChallengeDimulai()
        setChallengeBerakhir()

    }

    private fun initListener() {

    }

    private fun setObserver() {

    }

    override fun setDataSpinnerChallenge() {
        binding.apply {

            val positions = resources.getStringArray(R.array.divisi_or_resource)

            val adapter = ArrayAdapter(
                this@AddChallengeKaryawanActivity,
                android.R.layout.simple_spinner_item, positions
            )
            spMutasi.adapter = adapter

            spMutasi.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@AddChallengeKaryawanActivity,
                        getString(R.string.selected_item) + " " +
                                "" + positions[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    override fun setChallengeDimulai() {
        binding.apply {
            etTanggalMulai.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()

                var date: String
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AddChallengeKaryawanActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
                        date = "$dayOfMonth-$month-$year"
                        etTanggalMulai.setText(date)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
        }
    }

    override fun setChallengeBerakhir() {
        binding.apply {
            etTanggalSelesai.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()

                var date: String
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AddChallengeKaryawanActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
                        date = "$dayOfMonth-$month-$year"
                        etTanggalSelesai.setText(date)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
        }
    }
}