package com.arbaelbarca.placeholderjsonnew.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.albums.AlbumsFragment
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users.UserFragment

const val NUMBER = 2

class AdapterViewPager2(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return UserFragment()
            1 -> return AlbumsFragment()
            else -> return UserFragment()
        }
    }
}