package com.geektech.lesson4month6.data.remote

import com.geektech.lesson4month6.data.remote.model.PlaylistItem
import com.geektech.lesson4month6.data.remote.model.Playlists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    suspend fun getPlayLists(
        @Query("key") key:String,
        @Query("part") part:String,
        @Query("channelId") channelId: String,
    ) : Response<Playlists>
    @GET("playlistItems")

    suspend fun getPlaylistItems(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("playlistId") channelId : String,
        @Query("maxResults") maxResults : Int
    ) : Response<PlaylistItem>
}