package com.skripsi.mendoanapps.data.repositories

import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.data.model.project.GetProjectResponseItem
import com.skripsi.mendoanapps.data.network.Api
import io.reactivex.Single

class UserRepository {
    fun dataKaryawan(): Single<List<GetKaryawanResponseItem>> {
        return Api.getApi().getDataKaryawan(
            accept = "application/json"
        ).map {
            it.getKaryawanResponse
        }
    }

    fun activityKaryawan(): Single<List<GetProjectResponseItem>> {
        return Api.getApi().getActivityKaryawan(
            accept = "application/json"
        ).map {
            it.getProjectResponse
        }
    }
}