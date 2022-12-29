package com.skripsi.mendoanapps.ui.list_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.all_board.BoardsItem
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class ListProyekViewModel : ViewModel() {
    val listProyek: MutableLiveData<VmData<List<BoardsItem>>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun dataListProyek() {
        listProyek.value = VmData.loading()
        repository.getListProyek()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listProyek.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        listProyek.value = VmData.fail(it, message)
                    }
                } else {
                    listProyek.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}