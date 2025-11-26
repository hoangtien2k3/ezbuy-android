package com.ezbuy.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = onInflateView(layoutInflater)
        setContentView(binding.root)
        setupData(savedInstanceState)
    }

    protected abstract fun onInflateView(inflater: LayoutInflater): B

    protected abstract fun setupData(savedInstanceState: Bundle?)
}
