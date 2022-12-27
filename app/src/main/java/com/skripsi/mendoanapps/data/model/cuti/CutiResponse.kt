package com.skripsi.mendoanapps.data.model.cuti

import com.google.gson.annotations.SerializedName

data class CutiResponse(

	@field:SerializedName("Cuti")
	val cuti: List<CutiItem>
)

data class CutiItem(

	@field:SerializedName("tanggal_cuti")
	val tanggalCuti: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null
)
