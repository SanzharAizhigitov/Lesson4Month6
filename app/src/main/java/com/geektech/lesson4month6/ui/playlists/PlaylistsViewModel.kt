package com.geektech.lesson4month6.ui.playlists

import androidx.lifecycle.LiveData
import com.geektech.lesson4month6.App
import com.geektech.lesson4month6.core.ui.BaseViewModel
import com.geektech.lesson4month6.data.remote.model.Playlists
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlayLists(): LiveData<Resource<Playlists>> {
        return repository.getPlayLists()
    }
}