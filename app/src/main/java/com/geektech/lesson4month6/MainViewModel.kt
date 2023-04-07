package com.geektech.lesson4month6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.lesson4month6.base.BaseViewModel
import com.geektech.lesson4month6.model.Playlists
import com.geektech.lesson4month6.remote.ApiService
import com.geektech.lesson4month6.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseViewModel() {
    private val apiService: ApiService = RetrofitClient.create()
    fun playLists(): LiveData<Playlists> {
        return getPlayLists()
    }

    private fun getPlayLists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getPlayLists(BuildConfig.API_KEY, "contentDetails,snippet", "UCWOA1ZGywLbqmigxE4Qlvuw")
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                            // adapter.addTasks(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    println(t.stackTrace)
                }

            })
        return data
    }
}