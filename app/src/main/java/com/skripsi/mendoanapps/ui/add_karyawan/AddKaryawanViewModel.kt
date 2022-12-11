package com.skripsi.mendoanapps.ui.add_karyawan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.data.model.karyawan.PostKaryawanResponse
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class AddKaryawanViewModel : ViewModel() {
    val postKaryawan: MutableLiveData<VmData<PostKaryawanResponse>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun postKaryawan(data: GetKaryawanResponseItem) {
        postKaryawan.value = VmData.loading()
        repository.postKaryawan(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postKaryawan.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        postKaryawan.value = VmData.fail(it, message)
                    }
                } else {
                    postKaryawan.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}