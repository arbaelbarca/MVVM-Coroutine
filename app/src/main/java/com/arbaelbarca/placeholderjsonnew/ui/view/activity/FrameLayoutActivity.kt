package com.arbaelbarca.placeholderjsonnew.ui.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.data.base.BaseActivityBinding
import com.arbaelbarca.placeholderjsonnew.databinding.ActivityFrameLayoutBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePost
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.albums.AlbumsDetailFragment
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.posting.PostsDetailFragment
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users.UserDetailFragment
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users.UserDetailPhotoFragment
import com.arbaelbarca.placeholderjsonnew.utils.ConstVar
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
            2 -> {
                supportActionBar?.hide()
                val getData =
                    intent.getParcelableExtra<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>(
                        ConstVar.DATA_PHOTO_ITEM
                    )
                val bundle = Bundle()
                bundle.putParcelable(ConstVar.DATA_PHOTO_ITEM, getData)
                val fragment = UserDetailPhotoFragment()
                fragment.arguments = bundle
                setFragment(fragment)
            }
            3 -> {
                val getData =
                    intent.getParcelableExtra<ResponseAlbums.ResponseAlbumsItem>(
                        ConstVar.DATA_ALBUMS_ITEM
                    )
                val bundle = Bundle()
                bundle.putParcelable(ConstVar.DATA_ALBUMS_ITEM, getData)
                val fragment = AlbumsDetailFragment()
                fragment.arguments = bundle
                setFragment(fragment)
            }
            4 -> {
                val getData =
                    intent.getParcelableExtra<ResponsePost.ResponsePostItem>(
                        ConstVar.DATA_POSTS_ITEM
                    )
                val bundle = Bundle()
                bundle.putParcelable(ConstVar.DATA_POSTS_ITEM, getData)
                val fragment = PostsDetailFragment()
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