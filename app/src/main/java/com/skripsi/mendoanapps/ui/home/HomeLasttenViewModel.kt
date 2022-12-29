package com.skripsi.mendoanapps.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.lastten.LastTenItem
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class HomeLasttenViewModel : ViewModel() {
    val getLastten: MutableLiveData<VmData<List<LastTenItem>>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun dataLastter(userRole: String) {
        getLastten.value = VmData.loading()
        repository.getLastten(userRole)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getLastten.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        getLastten.value = VmData.fail(it, message)
                    }
                } else {
                    getLastten.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}