package com.geektech.lesson4month6.data.remote

import com.geektech.lesson4month6.BuildConfig
import com.geektech.lesson4month6.core.network.BaseDataSource
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.data.remote.model.PlaylistItem
import com.geektech.lesson4month6.data.remote.model.Playlists
import com.geektech.lesson4month6.utils.Const
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService): BaseDataSource() {

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
