package com.arbaelbarca.placeholderjsonnew.ui.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.data.base.BaseActivityBinding
import com.arbaelbarca.placeholderjsonnew.databinding.ActivityFrameLayoutBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterViewPager2
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users.UserDetailFragment
import com.arbaelbarca.placeholderjsonnew.utils.ConstVar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrameLayoutActivity : BaseActivityBinding<ActivityFrameLayoutBinding>() {

    var pos = 0

    override val bindingInflater: (LayoutInflater) -> ActivityFrameLayoutBinding
        get() = ActivityFrameLayoutBinding::inflate

    override fun setupView(binding: ActivityFrameLayoutBinding) {
        initial()
    }

    private fun initial() {
        pos = intent.getIntExtra("pos", 0)
        setPosition(pos)
    }

    fun setPosition(pos: Int) {
        when (pos) {
            1 -> {
                val getDataUsers =
                    intent.getParcelableExtra<ResponseUsers.ResponseUsersItem>(ConstVar.DATA_USERS)
                val bundle = Bundle()
                bundle.putParcelable(ConstVar.DATA_USERS, getDataUsers)
                val fragment = UserDetailFragment()
                fragment.arguments = bundle
                setFragment(fragment)
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

}