package com.skripsi.mendoanapps.ui.karyawan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class DataKaryawanViewModel : ViewModel() {
    val getKaryawan: MutableLiveData<VmData<List<GetKaryawanResponseItem>>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun dataKaryawan() {
        getKaryawan.value = VmData.loading()
        repository.dataKaryawan()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getKaryawan.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        getKaryawan.value = VmData.fail(it, message)
                    }
                } else {
                    getKaryawan.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}