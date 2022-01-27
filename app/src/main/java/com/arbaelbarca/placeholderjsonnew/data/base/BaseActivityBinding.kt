package com.arbaelbarca.placeholderjsonnew.data.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityBinding<T : ViewBinding> : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> T

    //    var layoutToolbarV1Binding: LayoutToolbarV1Binding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setupView(binding)
    }


    abstract fun setupView(binding: T)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}