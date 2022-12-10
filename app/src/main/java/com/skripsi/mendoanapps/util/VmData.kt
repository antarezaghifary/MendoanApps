package com.skripsi.mendoanapps.util

sealed class VmData<T : Any> {
    class Loading<T : Any> : VmData<T>()
    class Default<T : Any> : VmData<T>()
    class Empty<T : Any> : VmData<T>()
    data class Success<T : Any>(val data: T) : VmData<T>()
    data class Failure<T : Any>(val throwable: Throwable?, val message: String?) : VmData<T>()

    companion object {
        fun <T : Any> loading(): VmData<T> = Loading()
        fun <T : Any> default(): VmData<T> = Default()
        fun <T : Any> success(data: T): VmData<T> = Success(data)
        fun <T : Any> empty(): VmData<T> = Empty()
        fun <T : Any> fail(throwable: Throwable, message: String?): VmData<T> =
            Failure(throwable, message)
    }
}
