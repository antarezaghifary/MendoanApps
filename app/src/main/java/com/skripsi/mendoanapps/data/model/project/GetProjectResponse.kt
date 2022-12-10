package com.skripsi.mendoanapps.data.model.project

import com.google.gson.annotations.SerializedName

data class GetProjectResponse(

	@field:SerializedName("GetProjectResponse")
	val getProjectResponse: List<GetProjectResponseItem>
)

data class GetProjectResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)
