package com.skripsi.mendoanapps.data.repositories

import com.skripsi.mendoanapps.data.model.aktivitas_date.ProjectItem
import com.skripsi.mendoanapps.data.model.all_board.BoardsItem
import com.skripsi.mendoanapps.data.model.cuti.CutiItem
import com.skripsi.mendoanapps.data.model.idle.ResourceItem
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.data.model.karyawan.KaryawanResponse
import com.skripsi.mendoanapps.data.model.lastten.LastTenItem
import com.skripsi.mendoanapps.data.model.login.LoginResponse
import com.skripsi.mendoanapps.data.model.project.GetProjectResponseItem
import com.skripsi.mendoanapps.data.network.Api
import io.reactivex.Single

class UserRepository {

    fun postLogin(username: String, password: String): Single<LoginResponse> {
        return Api.getApi().postLogin(
            username = username,
            password = password
        ).map {
            it
        }
    }

    fun dataKaryawan(user_role: String): Single<List<GetKaryawanResponseItem>> {
        return Api.getApi().getDataKaryawan(
            accept = "application/json",
            user_role = user_role
        ).map {
            it.getKaryawanResponse
        }
    }

    fun postKaryawan(data: GetKaryawanResponseItem): Single<KaryawanResponse> {
        return Api.getApi().postDataKaryawan(
            accept = "application/json",
            data = data
        ).map {
            it
        }
    }

    fun activityKaryawan(): Single<List<GetProjectResponseItem>> {
        return Api.getApi().getActivityKaryawan(
            accept = "application/json"
        ).map {
            it.getProjectResponse
        }
    }

    fun getCuti(): Single<List<CutiItem>> {
        return Api.getApi().getListCuti(
            accept = "application/json"
        ).map {
            it.cuti
        }
    }

    fun postCuti(
        resource_name: String,
        tanggal: String,
        keterangan: String
    ): Single<KaryawanResponse> {
        return Api.getApi().postCuti(
            accept = "application/json",
            resource_name = resource_name,
            tanggal = tanggal,
            keterangan = keterangan
        ).map {
            it
        }
    }

    fun getDataIdle(): Single<List<ResourceItem>> {
        return Api.getApi().getDataIdle(
            accept = "application/json"
        ).map {
            it.resource
        }
    }

    fun getListProyek(): Single<List<BoardsItem>> {
        return Api.getApi().getListProyek(
            accept = "application/json"
        ).map {
            it.boards
        }
    }

    fun getActivitybyDate(
        karyawan: String,
        dateassignment: String,
        endassignment: String
    ): Single<List<ProjectItem>> {
        return Api.getApi().getActivityByDate(
            accept = "application/json",
            karyawan = karyawan,
            dateassignment = dateassignment,
            endassignment = endassignment
        ).map {
            it.project
        }
    }

    fun getLastten(
        user_role: String
    ): Single<List<LastTenItem>> {
        return Api.getApi().getLastten(
            accept = "application/json",
            user_role = user_role
        ).map {
            it.lastTen
        }
    }
}