package com.kishorejethava.moviepilot.webservice

interface ApiCallback<in T> {
    fun onSuccess(response: T)

    fun onFailure(apiError: ApiError)
}
