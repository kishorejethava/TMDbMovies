package com.kishorejethava.moviepilot.webservice

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.Response

object ApiHandle {

    fun <T> createRetrofitBase(
        observable: Observable<Response<T>>,
        apiCallback: ApiCallback<T>
    ) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<T>>() {
                override fun onComplete() {

                }

                override fun onNext(t: Response<T>) {
                    if (t.body() != null) {
                        apiCallback.onSuccess(t.body()!!)
                    }
                }

                override fun onError(e: Throwable) {
                    val responseModel = ApiError()
                    responseModel.message = e.message
                    apiCallback.onFailure(responseModel)
                }

            })

    }
}