package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.albums

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentAlbumsDetailBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterPhotosAlbums
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.ui.view.activity.FrameLayoutActivity
import com.arbaelbarca.placeholderjsonnew.utils.*
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumsDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AlbumsDetailFragment : BaseFragmentBinding<FragmentAlbumsDetailBinding>() {

    val viewmodelUsers: ViewModelUsers by viewModels()
    lateinit var albumsDetailBinding: FragmentAlbumsDetailBinding
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlbumsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumsDetailBinding
        get() = FragmentAlbumsDetailBinding::inflate

    override fun setupView(binding: FragmentAlbumsDetailBinding) {
        albumsDetailBinding = binding
        initial()
    }

    private fun initial() {
        initIntent()
        initObserver()
    }

    private fun initIntent() {
        val responseAlbumsItem =
            bundle?.getParcelable<ResponseAlbums.ResponseAlbumsItem>(ConstVar.DATA_ALBUMS_ITEM)
        if (responseAlbumsItem != null) {
            callApiPhotos(responseAlbumsItem.id!!)
        }
    }

    private fun callApiPhotos(albumsItemId: Int) {
        viewmodelUsers.getPhotosAlbums(albumsItemId.toString())
    }


    private fun initObserver() {
        viewmodelUsers.observerGetPhotosAlbums()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is UiState.Loading -> {
                        showView(albumsDetailBinding.pbList)
                        hideView(albumsDetailBinding.rvListPhotosAlbums)
                    }
                    is UiState.Success -> {
                        hideView(albumsDetailBinding.pbList)
                        showView(albumsDetailBinding.rvListPhotosAlbums)
                        val dataItem = it.data
                        initAdapterPhotos(dataItem)
                    }
                    is UiState.Failure -> {
                        hideView(albumsDetailBinding.pbList)
                        showView(albumsDetailBinding.rvListPhotosAlbums)
                        it.throwable.printStackTrace()
                    }
                }
            }
    }

    private fun initAdapterPhotos(dataItem: List<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>) {
        val adapterPhotosAlbums =
            AdapterPhotosAlbums(dataItem as MutableList<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>,
                object : OnClickItem {
                    override fun clickItem(pos: Int, any: Any) {
                        val photosItem = any as ResponsePhotosAlbums.ResponsePhotosAlbumsItem
                        val intent = Intent(requireContext(), FrameLayoutActivity::class.java)
                            .putExtra("pos", 2)
                            .putExtra(ConstVar.DATA_PHOTO_ITEM, photosItem)
                        startActivity(intent)

                    }
                })
        setRvAdapterVertikalDefault(albumsDetailBinding.rvListPhotosAlbums, adapterPhotosAlbums)
    }


}