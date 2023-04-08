package com.geektech.lesson4month6.repository

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.lesson4month6.BuildConfig
import com.geektech.lesson4month6.core.network.RetrofitClient
import com.geektech.lesson4month6.core.network.isOnline.ConnectionLiveData
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.data.remote.ApiService
import com.geektech.lesson4month6.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService = RetrofitClient.create()

    fun getPlayLists(): MutableLiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()
        data.value = Resource.loading()
        apiService.getPlayLists(
            BuildConfig.API_KEY,
            "contentDetails,snippet",
            "UCWOA1ZGywLbqmigxE4Qlvuw"
        ).enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }
                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    data.value = Resource.error(t.message, null, t.hashCode())
                }
            })
        return data
    }
}