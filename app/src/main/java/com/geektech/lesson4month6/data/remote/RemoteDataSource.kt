package com.geektech.lesson4month6.data.remote

import com.geektech.lesson4month6.BuildConfig
import com.geektech.lesson4month6.core.network.BaseDataSource
import com.geektech.lesson4month6.core.network.RetrofitClient
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.data.remote.model.PlaylistItem
import com.geektech.lesson4month6.data.remote.model.Playlists
import com.geektech.lesson4month6.utils.Const

class RemoteDataSource: BaseDataSource() {

    private val apiService: ApiService = RetrofitClient.create()
    suspend fun getPlayLists(): Resource<Playlists> = getResult {
            apiService.getPlayLists( BuildConfig.API_KEY, Const.part, Const.channelId)
        }
    suspend fun getPlaylistItems(playlistId: String, itemCount: Int): Resource<PlaylistItem> {
        return getResult {
            apiService.getPlaylistItems(BuildConfig.API_KEY,
                Const.part,
                playlistId,
                itemCount)
        }
    }
    }
