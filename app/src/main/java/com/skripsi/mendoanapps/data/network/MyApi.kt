package com.skripsi.mendoanapps.data.network

import com.skripsi.mendoanapps.data.model.cuti.CutiResponse
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponse
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.data.model.karyawan.KaryawanResponse
import com.skripsi.mendoanapps.data.model.login.LoginResponse
import com.skripsi.mendoanapps.data.model.project.GetProjectResponse
import io.reactivex.Single
import retrofit2.http.*

interface MyApi {


    /**
     * Login
     */

    @FormUrlEncoded
    @POST("auth/rlogin")
    fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<LoginResponse>

    /**
     * Data Karyawan
     */
    @GET("karyawan/list")
    fun getDataKaryawan(
        @Header("accept") accept: String,
        @Query("user_role") user_role: String
    ): Single<GetKaryawanResponse>

    @POST("karyawan/add")
    fun postDataKaryawan(
        @Header("accept") accept: String,
        @Body data: GetKaryawanResponseItem
    ): Single<KaryawanResponse>

    /**
     * Data Aktifitas Karyawan
     */
    @GET("project")
    fun getActivityKaryawan(
        @Header("accept") accept: String
    ): Single<GetProjectResponse>

    /**
     * Cuti
     */
    @GET("karyawan/listcuti")
    fun getListCuti(
        @Header("accept") accept: String
    ): Single<CutiResponse>

    /**
     * Post Cuti
     */

    @POST("karyawan/cuti")
    fun postCuti(
        @Header("accept") accept: String,
        @Query("resource_name") resource_name: String,
        @Query("tanggal") tanggal: String,
        @Query("keterangan") keterangan: String,
    ): Single<KaryawanResponse>

}
