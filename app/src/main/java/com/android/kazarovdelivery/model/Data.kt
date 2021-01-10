package com.android.kazarovdelivery.model

data class Data<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> init(): Data<T> = Data(status = Status.INITIALIZED, data = null, message = null)

        fun <T> success(data: T): Data<T> = Data(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T? = null, message: String): Data<T> =
            Data(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T? = null): Data<T> =
            Data(status = Status.LOADING, data = data, message = null)
    }

    enum class Status {
        INITIALIZED,
        SUCCESS,
        ERROR,
        LOADING
    }
}