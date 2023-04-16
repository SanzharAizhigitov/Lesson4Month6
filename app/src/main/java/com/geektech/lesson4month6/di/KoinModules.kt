package com.geektech.lesson4month6.di

import com.geektech.lesson4month6.core.network.networkModule
import com.geektech.lesson4month6.data.remote.remoteDataSource
import org.koin.core.module.Module

    val koinModules = listOf<Module>(
        repoModules,
        viewModules,
        remoteDataSource,
        networkModule
    )
