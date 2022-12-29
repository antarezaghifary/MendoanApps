package com.skripsi.mendoanapps.data.model.aktivitas_date

import com.google.gson.annotations.SerializedName

data class AktivitasDateResponse(

    @field:SerializedName("Project")
    val project: List<ProjectItem>
)

data class ProjectItem(

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
