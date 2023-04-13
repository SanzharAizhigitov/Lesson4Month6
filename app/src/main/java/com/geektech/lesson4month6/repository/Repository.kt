package com.geektech.lesson4month6.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.data.remote.RemoteDataSource
import com.geektech.lesson4month6.data.remote.model.PlaylistItem
import com.geektech.lesson4month6.data.remote.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repository {
    private val dataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlayLists(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlayLists()
        emit(
            response
        )
    }
    fun getVideos(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistItem>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistItems(playlistId, itemCount)
            emit(response)
        }
}