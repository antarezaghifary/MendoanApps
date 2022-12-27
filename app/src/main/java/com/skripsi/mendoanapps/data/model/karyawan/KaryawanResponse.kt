package com.skripsi.mendoanapps.data.model.karyawan

import com.google.gson.annotations.SerializedName

data class KaryawanResponse(

	@field:SerializedName("STATUS")
	val sTATUS: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
