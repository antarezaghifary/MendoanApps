package com.skripsi.mendoanapps.data.network

import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponse
import com.skripsi.mendoanapps.data.model.project.GetProjectResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface MyApi {

    /**
     * Data Karyawan
     */
    @GET("karyawan")
    fun getDataKaryawan(
        @Header("accept") accept: String
    ): Single<GetKaryawanResponse>

    /**
     * Data Aktifitas Karyawan
     */
    @GET("project")
    fun getActivityKaryawan(
        @Header("accept") accept: String
    ): Single<GetProjectResponse>

}
