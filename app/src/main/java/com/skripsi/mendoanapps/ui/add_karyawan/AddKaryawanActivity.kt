package com.skripsi.mendoanapps.ui.add_karyawan

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.R
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.databinding.ActivityAddKaryawanBinding
import com.skripsi.mendoanapps.ui.home.HomeActivity
import com.skripsi.mendoanapps.util.VmData
import com.skripsi.mendoanapps.util.extention
import java.util.*

class AddKaryawanActivity : AppCompatActivity() {

    private val binding: ActivityAddKaryawanBinding by viewBinding()
    private val viewModel: AddKaryawanViewModel by viewModels()
    private var dataIdentitas: String? = null
    private var dataMarital: String? = null
    private var dataPendidikan: String? = null
    private var dataDivisi: String? = null
    private var dataResource: String? = null
    private var dataPosisi: String? = null
    private var dataStatusKaryawan: String? = null
    private var dataPenempatan: String? = null
    private var dataUserRole: String? = null
    private var date: String? = null
    private var dataTanggalLahir: String? = null
    private val progressDialog: extention.CustomProgressDialog by lazy {
        extention.CustomProgressDialog(this@AddKaryawanActivity)
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
            postKaryawan.observe(this@AddKaryawanActivity) {
                when (it) {
                    is VmData.Loading -> {
                        progressDialog.start("Loading")
                    }
                    is VmData.Success -> {
                        progressDialog.stop()
                        toast(it.data.message.toString())
                        if (it.data.sTATUS.equals("SUCCESS")) {
                            intent = Intent(this@AddKaryawanActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            progressDialog.stop()
                        }
                    }
                    is VmData.Failure -> {
                        progressDialog.stop()
                        toast(it.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }

    private fun initObserver() {

    }

    private fun initListener() {

        binding.apply {
            btAddKaryawan.setOnClickListener {
                val dataUserId = etUserId.text.toString().trim()
                val dataFullname = etFullname.text.toString().trim()
                val dataMarital = dataMarital //etMarital.text.toString().trim()
                val dataNik = etNik.text.toString().trim()
                val dataIdentitas = dataIdentitas //etIdentitas.text.toString().trim()
                val dataDivisi = dataDivisi //etDivisi.text.toString().trim()
                val dataTglMasuk = date//etTglMasuk.text.toString().trim()
                val dataStatusKaryawan =
                    dataStatusKaryawan //etStatusKaryawan.text.toString().trim()
                val dataEmail = etEmail.text.toString().trim()
                val dataPhone = etPhone.text.toString().trim()
                val dataAlamat = etAlamat.text.toString().trim()
                val dataPosisi = dataPosisi //etPosisi.text.toString().trim()
                val dataResource = dataResource //etResource.text.toString().trim()
                val dataSite = dataPenempatan //etSite.text.toString().trim()
                val dataTelegram = etTelegram.text.toString().trim()
                val dataPendidikan = dataPendidikan //etPendidikan.text.toString().trim()
                val dataInstitusi = etInstitusi.text.toString().trim()
                val dataTempatLahir = etTempatLahir.text.toString().trim()
                val dataTanggalLahir = dataTanggalLahir //etTanggalLahir.text.toString().trim()
                val dataStatus = dataUserRole //etStatus.text.toString().trim()
                val dataNikKaryawan = etNikKaryawan.text.toString().trim()
                val dataJurusan = etJurusan.text.toString().trim()
//                val dataTrelloId = etTrelloId.text.toString().trim()
//                val dataLamaKerja = etLamaKerja.text.toString().trim()

                val checkNullData =
                    !dataUserId.isEmpty() && !dataFullname.isEmpty() && !dataMarital?.isEmpty()!! && !dataNik.isEmpty() && !dataIdentitas?.isEmpty()!! && !dataDivisi?.isEmpty()!!
                            && !dataTglMasuk?.isEmpty()!! && !dataStatusKaryawan?.isEmpty()!! && !dataEmail.isEmpty() && !dataPhone.isEmpty() && !dataAlamat.isEmpty()
                            && !dataPosisi?.isEmpty()!! && !dataSite?.isEmpty()!! && !dataTelegram.isEmpty() && !dataPendidikan?.isEmpty()!!
                            && !dataInstitusi.isEmpty() && !dataTempatLahir.isEmpty() && !dataTanggalLahir?.isEmpty()!! && !dataStatus?.isEmpty()!! && !dataNikKaryawan.isEmpty()
                            && !dataJurusan.isEmpty()

                if (checkNullData) {
                    viewModel.postKaryawan(
                        GetKaryawanResponseItem(
                            fullname = dataFullname,
                            userId = dataUserId,
                            statuspernikahan = dataMarital,
                            nik = dataNik,
                            identitas = dataIdentitas,
                            divisi = dataDivisi,
                            tanggalmasuk = dataTglMasuk,
                            statuskaryawan = dataStatusKaryawan,
                            email = dataEmail,
                            phone = dataPhone,
                            alamat = dataAlamat,
                            posisi = dataPosisi,
                            resource = dataResource,
                            site = dataSite,
                            telegram = dataTelegram,
                            pendidikan = dataPendidikan,
                            institusi = dataInstitusi,
                            tempatlahir = dataTempatLahir,
                            tanggallahir = dataTanggalLahir,
                            status = dataStatus,
                            nikkaryawan = dataNikKaryawan,
                            jurusan = dataJurusan,
                            createdby = null,
                            trelloid = null,
                            year = null,
                            createdate = null,
                            roleTrello = null,
                            month = null,
                            day = null
                        )
                    )
                } else {
                    toast("Isi Dulu")
                }
            }
        }
    }

    private fun initUI() {
        setDataSpinnerIdentitas()
        setDataSpinnerMarital()
        setDataSpinnerPendidikanAkhir()
        setDataSpinnerDivisi()
        setDataSpinnerResource()
        setDataSpinnerPosisi()
        setDataSpinnerStatusKaryawan()
        setDataSpinnerPenempatanKerja()
        setDataSpinnerUserRole()
        getTanggal()
        getTanggalLahir()
    }

    private fun setDataSpinnerIdentitas() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.jenis_identitas)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spIdentitas.adapter = adapter

            spIdentitas.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataIdentitas = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun setDataSpinnerMarital() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.status_pernikahan)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spMarital.adapter = adapter

            spMarital.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataMarital = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


    private fun setDataSpinnerPendidikanAkhir() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.pendidikan_akhir)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spPendidikan.adapter = adapter

            spPendidikan.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataPendidikan = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


    private fun setDataSpinnerDivisi() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.divisi_or_resource)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spDivisi.adapter = adapter

            spDivisi.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataDivisi = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


    private fun setDataSpinnerResource() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.divisi_or_resource)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spResource.adapter = adapter

            spResource.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataResource = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


    private fun setDataSpinnerPosisi() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.posisi)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spPosisi.adapter = adapter

            spPosisi.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataPosisi = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


    private fun setDataSpinnerStatusKaryawan() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.status_karyawan)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spStatusKaryawan.adapter = adapter

            spStatusKaryawan.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataStatusKaryawan = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


    private fun setDataSpinnerPenempatanKerja() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.penempatan_kerja)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spPenempatan.adapter = adapter

            spPenempatan.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataPenempatan = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun setDataSpinnerUserRole() {
        binding.apply {

            val identitas = resources.getStringArray(R.array.user_role)

            val adapter = ArrayAdapter(
                this@AddKaryawanActivity,
                android.R.layout.simple_spinner_item, identitas
            )
            spUserRole.adapter = adapter

            spUserRole.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@AddKaryawanActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + identitas[position], Toast.LENGTH_SHORT
//                    ).show()
                    dataUserRole = identitas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun getTanggal() {
        binding.apply {
            etTglMasuk.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AddKaryawanActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
                        date = "$dayOfMonth-$month-$year"
                        etTglMasuk.setText(date)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
        }
    }


    private fun getTanggalLahir() {
        binding.apply {
            etTanggalLahir.setOnClickListener {
                val mcurrentDate: Calendar = Calendar.getInstance()
                val mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)
                val mMonth = mcurrentDate.get(Calendar.MONTH)
                val mYear = mcurrentDate.get(Calendar.YEAR)
                val datePickerDialog = DatePickerDialog(
                    this@AddKaryawanActivity,
                    { view, year, month, dayOfMonth ->
                        var month = month
                        month = month + 1
                        dataTanggalLahir = "$dayOfMonth-$month-$year"
                        etTanggalLahir.setText(dataTanggalLahir)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
        }
    }
}