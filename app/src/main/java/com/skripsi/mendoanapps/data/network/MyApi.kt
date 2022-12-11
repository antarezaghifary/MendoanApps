package com.skripsi.mendoanapps.data.network

import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponse
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.data.model.karyawan.PostKaryawanResponse
import com.skripsi.mendoanapps.data.model.project.GetProjectResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MyApi {

    /**
     * Data Karyawan
     */
    @GET("karyawan")
    fun getDataKaryawan(
        @Header("accept") accept: String
    ): Single<GetKaryawanResponse?>

    @POST("karyawan")
    fun postDataKaryawan(
        @Header("accept") accept: String,
        @Body data: GetKaryawanResponseItem
    ): Single<PostKaryawanResponse>

    /**
     * Data Aktifitas Karyawan
     */
    @GET("project")
    fun getActivityKaryawan(
        @Header("accept") accept: String
    ): Single<GetProjectResponse>

}
