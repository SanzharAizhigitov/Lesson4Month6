package com.geektech.lesson4month6.ui.playlists

import androidx.lifecycle.LiveData
import com.geektech.lesson4month6.App
import com.geektech.lesson4month6.core.ui.BaseViewModel
import com.geektech.lesson4month6.data.remote.model.Playlists
import com.geektech.lesson4month6.core.network.result.Resource

class PlaylistsViewModel : BaseViewModel() {
    fun getPlayLists(): LiveData<Resource<Playlists>> {
        return App.repository.getPlayLists()
    }
}