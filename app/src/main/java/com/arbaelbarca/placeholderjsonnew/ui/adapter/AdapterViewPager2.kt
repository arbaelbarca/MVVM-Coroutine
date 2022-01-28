package com.arbaelbarca.placeholderjsonnew.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnFragmentViewPager

const val NUMBER = 2

class AdapterViewPager2(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val onFragmentViewPager: OnFragmentViewPager
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return onFragmentViewPager.setFragmentViewPager(position)
    }
}