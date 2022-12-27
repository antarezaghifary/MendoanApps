package com.skripsi.mendoanapps.ui.add_cuti

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.karyawan.KaryawanResponse
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class AddCutiViewModel : ViewModel() {

    val postCuti: MutableLiveData<VmData<KaryawanResponse>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun postCuti(resource_name: String, tanggal: String, keterangan: String) {
        postCuti.value = VmData.loading()
        repository.postCuti(resource_name, tanggal, keterangan)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postCuti.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        postCuti.value = VmData.fail(it, message)
                    }
                } else {
                    postCuti.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}