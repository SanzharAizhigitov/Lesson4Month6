package com.geektech.lesson4month6

import android.app.Application
import com.geektech.lesson4month6.repository.Repository

class App : Application() {
    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }
}