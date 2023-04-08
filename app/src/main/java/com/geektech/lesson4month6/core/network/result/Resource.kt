package com.geektech.lesson4month6.core.network.result

import retrofit2.Response

data class Resource<out T>(val status: Status,val data:T?, val message:String?, val code:Int?){
    companion object{
        fun <T> success(data:T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null, null)
        }
        //fun <T> success(data: T?, code: Int? = null):Resource<T>{}
        fun <T> loading():Resource<T> {
            return Resource(Status.LOADING, null, null, null)
        }
        fun <T> error(msg: String?, data: T?, code: Int?  ):Resource<T>{
            return Resource(Status.ERROR, data, msg, code )
        }
    }
}
