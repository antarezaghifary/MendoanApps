package com.skripsi.mendoanapps.ui.add_karyawan

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.databinding.ActivityAddKaryawanBinding
import com.skripsi.mendoanapps.util.VmData


class AddKaryawanActivity : AppCompatActivity() {

    private val binding: ActivityAddKaryawanBinding by viewBinding()
    private val viewModel: AddKaryawanViewModel by viewModels()

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
                    is VmData.Loading -> toast("Loading ...")
                    is VmData.Success -> Log.e("TAG", "Post Data: ${it.data}")
                    is VmData.Failure -> toast(it.message.toString())
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
                val dataFullname = etFullname.text.toString().trim()
                val dataMarital = etMarital.text.toString().trim()
                val dataNik = etNik.text.toString().trim()
                val dataIdentitas = etIdentitas.text.toString().trim()
                val dataDivisi = etDivisi.text.toString().trim()
                val dataTglMasuk = etTglMasuk.text.toString().trim()
                val dataStatusKaryawan = etStatusKaryawan.text.toString().trim()
                val dataEmail = etEmail.text.toString().trim()
                val dataPhone = etPhone.text.toString().trim()
                val dataAlamat = etAlamat.text.toString().trim()
                val dataPosisi = etPosisi.text.toString().trim()
                val dataResource = etResource.text.toString().trim()
                val dataSite = etSite.text.toString().trim()
                val dataTelegram = etTelegram.text.toString().trim()
                val dataPendidikan = etPendidikan.text.toString().trim()
                val dataInstitusi = etInstitusi.text.toString().trim()
                val dataTempatLahir = etTempatLahir.text.toString().trim()
                val dataTanggalLahir = etTanggalLahir.text.toString().trim()
                val dataStatus = etStatus.text.toString().trim()
                val dataNikKaryawan = etNikKaryawan.text.toString().trim()
                val dataJurusan = etJurusan.text.toString().trim()
                val dataTrelloId = etTrelloId.text.toString().trim()
                val dataLamaKerja = etLamaKerja.text.toString().trim()

                val checkNullData =
                    !dataFullname.isEmpty() && !dataMarital.isEmpty() && !dataNik.isEmpty() && !dataIdentitas.isEmpty() && !dataDivisi.isEmpty()
                            && !dataTglMasuk.isEmpty() && !dataStatusKaryawan.isEmpty() && !dataEmail.isEmpty() && !dataPhone.isEmpty() && !dataAlamat.isEmpty()
                            && !dataPosisi.isEmpty() && !dataResource.isEmpty() && !dataSite.isEmpty() && !dataTelegram.isEmpty() && !dataPendidikan.isEmpty()
                            && !dataInstitusi.isEmpty() && !dataTempatLahir.isEmpty() && !dataTanggalLahir.isEmpty() && !dataStatus.isEmpty() && !dataNikKaryawan.isEmpty()
                            && !dataJurusan.isEmpty() && !dataTrelloId.isEmpty() && !dataLamaKerja.isEmpty()

                if (checkNullData) {
                    val userId = 1
                    viewModel.postKaryawan(
                        GetKaryawanResponseItem(
                            fullname = dataFullname,
                            userId = userId.toString(),
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
                            trelloid = dataTrelloId,
                            year = dataLamaKerja.toInt(),
                            createdby = null,
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

    }
}