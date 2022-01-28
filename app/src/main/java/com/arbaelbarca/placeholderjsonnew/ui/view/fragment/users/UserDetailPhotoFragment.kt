package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentUserDetailPhotoBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.utils.ConstVar
import com.arbaelbarca.placeholderjsonnew.utils.loadImageUrlPicasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailPhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailPhotoFragment : BaseFragmentBinding<FragmentUserDetailPhotoBinding>() {

    lateinit var detailPhotoBinding: FragmentUserDetailPhotoBinding
    var bundle: Bundle? = null
    var photosItem: ResponsePhotosAlbums.ResponsePhotosAlbumsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDetailPhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserDetailPhotoBinding
        get() = FragmentUserDetailPhotoBinding::inflate

    override fun setupView(binding: FragmentUserDetailPhotoBinding) {
        detailPhotoBinding = binding
        initial()
    }

    private fun initial() {
        initGetData()
    }

    private fun initGetData() {
        photosItem = bundle?.getParcelable(ConstVar.DATA_PHOTO_ITEM)

        detailPhotoBinding.imgDetailPhoto.loadImageUrlPicasso(
            photosItem?.thumbnailUrl.toString()
        )

        detailPhotoBinding.imgDetailPhoto.isZoomEnabled = true
        detailPhotoBinding.tvDetailPhoto.text = photosItem?.title
    }
}