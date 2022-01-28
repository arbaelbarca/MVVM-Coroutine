package com.arbaelbarca.placeholderjsonnew

import android.view.LayoutInflater
import com.arbaelbarca.placeholderjsonnew.data.base.BaseActivityBinding
import com.arbaelbarca.placeholderjsonnew.databinding.ActivityMainBinding
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users.UserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupView(binding: ActivityMainBinding) {
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMain, UserFragment())
            .commit()
    }
}