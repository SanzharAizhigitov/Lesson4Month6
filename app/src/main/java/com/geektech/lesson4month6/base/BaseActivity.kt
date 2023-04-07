package com.geektech.lesson4month6.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM
    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkConnection()
        initViewModel()
        initView()
        initRV()
        initListener()
    }

    open fun initRV() {}
    open fun initViewModel() {} // инициализация вьюмодэла
    open fun checkConnection() {} // проверка на интернет
    open fun initView() {} // инициализация вьюшек
    open fun initListener() {} // обработка всех кликов
}

