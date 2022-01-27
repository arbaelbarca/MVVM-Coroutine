package com.arbaelbarca.placeholderjsonnew

import android.view.LayoutInflater
import com.arbaelbarca.placeholderjsonnew.data.base.BaseActivityBinding
import com.arbaelbarca.placeholderjsonnew.databinding.ActivityMainBinding
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    val arrayTab = arrayOf(
        "User List",
        "Albums List"
    )

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupView(binding: ActivityMainBinding) {
        initViewPager()
    }

    private fun initViewPager() {
        val adapter = AdapterViewPager2(
            supportFragmentManager,
            lifecycle)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = arrayTab[position]
        }.attach()
    }
}