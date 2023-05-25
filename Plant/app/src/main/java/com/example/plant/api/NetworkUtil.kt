package com.example.plant.api

import okhttp3.ResponseBody

object NetworkUtil {
    fun getErrorResponse(errorBody: ResponseBody): Error {
        return ApiClient.retrofit.responseBodyConverter<Error>(Error::class.java, Error::class.java.annotations).convert(errorBody)!!
    }

}