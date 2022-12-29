package com.skripsi.mendoanapps.ui.aktivitas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.skripsi.mendoanapps.data.model.aktivitas_date.ProjectItem
import com.skripsi.mendoanapps.data.repositories.UserRepository
import com.skripsi.mendoanapps.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class AktivitasDateViewModel : ViewModel() {
    val getActivityByDate: MutableLiveData<VmData<List<ProjectItem>>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun dataActivity(
        karyawan: String,
        dateassignment: String,
        endassignment: String
    ) {
        getActivityByDate.value = VmData.loading()
        repository.getActivitybyDate(karyawan, dateassignment, endassignment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getActivityByDate.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        getActivityByDate.value = VmData.fail(it, message)
                    }
                } else {
                    getActivityByDate.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}