package com.kishorejethava.moviepilot.webservice

import com.google.gson.annotations.SerializedName

class ApiError {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("error")
    var error: String? = null


}
