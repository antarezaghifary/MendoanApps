package com.skripsi.mendoanapps.data.model.karyawan

import com.google.gson.annotations.SerializedName

data class GetKaryawanResponse(

    @field:SerializedName("karyawan")
    val getKaryawanResponse: List<GetKaryawanResponseItem>
)

data class GetKaryawanResponseItem(

	@field:SerializedName("statuspernikahan")
	val statuspernikahan: String? = null,

	@field:SerializedName("pendidikan")
	val pendidikan: String? = null,

	@field:SerializedName("resource")
	val resource: String? = null,

	@field:SerializedName("tanggalmasuk")
	val tanggalmasuk: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("identitas")
	val identitas: String? = null,

	@field:SerializedName("nikkaryawan")
	val nikkaryawan: String? = null,

	@field:SerializedName("telegram")
	val telegram: String? = null,

	@field:SerializedName("jurusan")
	val jurusan: String? = null,

	@field:SerializedName("statuskaryawan")
	val statuskaryawan: String? = null,

	@field:SerializedName("divisi")
	val divisi: String? = null,

	@field:SerializedName("tanggallahir")
	val tanggallahir: Any? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("trelloid")
	val trelloid: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("site")
	val site: String? = null,

	@field:SerializedName("institusi")
	val institusi: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("tempatlahir")
	val tempatlahir: Any? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("posisi")
	val posisi: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("createdate")
	val createdate: Any? = null,

	@field:SerializedName("createdby")
	val createdby: Any? = null,

	@field:SerializedName("day")
	val day: Int? = null,

	@field:SerializedName("role_trello")
	val roleTrello: Any? = null,

	@field:SerializedName("month")
	val month: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null
)
