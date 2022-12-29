package com.skripsi.mendoanapps.data.model.lastten

import com.google.gson.annotations.SerializedName

data class LasttenResponse(

    @field:SerializedName("LastTen")
    val lastTen: List<LastTenItem>
)

data class LastTenItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
