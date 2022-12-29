package com.skripsi.mendoanapps.data.model.idle

import com.google.gson.annotations.SerializedName

data class KaryawanIdleResponse(

    @field:SerializedName("Resource")
    val resource: List<ResourceItem>
)

data class ResourceItem(

    @field:SerializedName("keterangan")
    val keterangan: Any? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null
)
