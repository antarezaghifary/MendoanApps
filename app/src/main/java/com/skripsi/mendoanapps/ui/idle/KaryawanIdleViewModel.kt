package com.skripsi.mendoanapps.ui.idle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.idle.ResourceItem
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class KaryawanIdleViewModel : ViewModel() {
    val idle: MutableLiveData<VmData<List<ResourceItem>>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun dataIdle() {
        idle.value = VmData.loading()
        repository.getDataIdle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                idle.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        idle.value = VmData.fail(it, message)
                    }
                } else {
                    idle.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}