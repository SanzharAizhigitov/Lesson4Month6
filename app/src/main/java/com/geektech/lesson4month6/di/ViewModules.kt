package com.geektech.lesson4month6.di

import com.geektech.lesson4month6.ui.player.PlayerViewModel
import com.geektech.lesson4month6.ui.playlists.PlaylistsViewModel
import com.geektech.lesson4month6.ui.videos.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel{PlaylistsViewModel(get())}
    viewModel{VideosViewModel(get())}
    viewModel{PlayerViewModel(get())}
}
