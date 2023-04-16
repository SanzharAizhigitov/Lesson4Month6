package com.geektech.lesson4month6.di

import com.geektech.lesson4month6.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get())}
}