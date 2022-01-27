package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentUserDetailBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterAlbumsUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterPhotosAlbums
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.utils.*
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UserDetailFragment : BaseFragmentBinding<FragmentUserDetailBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val viewmodelUsers: ViewModelUsers by viewModels()
    lateinit var detailBinding: FragmentUserDetailBinding
    var bundle: Bundle? = null
    var responseUsersItem: ResponseUsers.ResponseUsersItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = arguments
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserDetailBinding
        get() = FragmentUserDetailBinding::inflate

    override fun setupView(binding: FragmentUserDetailBinding) {
        detailBinding = binding
        initial()
    }

    private fun initial() {
        initBundle()
        initGetData()
        initCallApi()
        initObserver()
    }

    private fun initBundle() {
        responseUsersItem = bundle?.getParcelable(ConstVar.DATA_USERS)
    }

    private fun initGetData() {
        if (responseUsersItem != null) {
            detailBinding.tvNameDetailUsers.text = responseUsersItem?.name
            detailBinding.tvUserNameDetailUsers.text = responseUsersItem?.username
            detailBinding.tvEmailDetailUsers.text = responseUsersItem?.email
            detailBinding.tvCompanyDetailUsers.text = responseUsersItem?.company?.name
            detailBinding.tvPhoneDetailUsers.text = responseUsersItem?.phone
            detailBinding.tvWebsiteDetailsers.text = responseUsersItem?.website
            detailBinding.tvAddressDetailUsers.text = responseUsersItem?.address?.street + ", " +
                    responseUsersItem?.address?.city
        }
    }

    private fun initObserver() {
        viewmodelUsers.observerGetAlbumsUsers()
            .observe(viewLifecycleOwner, {
                when (it) {
                    is UiState.Loading -> {

                    }
                    is UiState.Success -> {
                        val dataItem = it.data
                        initAdapter(dataItem)
                    }
                    is UiState.Failure -> {
                        it.throwable.printStackTrace()
                    }
                }
            })

        viewmodelUsers.observerGetPhotosAlbums()
            .observe(viewLifecycleOwner, {
                when (it) {
                    is UiState.Loading -> {
                        showView(detailBinding.pbList)
                        hideView(detailBinding.rvListPhotosAlbums)
                    }
                    is UiState.Success -> {
                        hideView(detailBinding.pbList)
                        showView(detailBinding.rvListPhotosAlbums)
                        val dataItem = it.data
                        initAdapterPhotos(dataItem)
                    }
                    is UiState.Failure -> {
                        hideView(detailBinding.pbList)
                        showView(detailBinding.rvListPhotosAlbums)
                        it.throwable.printStackTrace()
                    }
                }
            })
    }

    private fun initAdapterPhotos(dataItem: List<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>) {
        val adapterPhotosAlbums =
            AdapterPhotosAlbums(dataItem as MutableList<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>)
        setRvAdapterVertikalDefault(detailBinding.rvListPhotosAlbums, adapterPhotosAlbums)
    }

    private fun initAdapter(dataItem: List<ResponseAlbums.ResponseAlbumsItem>) {
        val adapterAlbumsUsers =
            AdapterAlbumsUsers(dataItem as MutableList<ResponseAlbums.ResponseAlbumsItem>,
                object : OnClickItem {
                    override fun clickItem(pos: Int, any: Any) {
                        val albumsItem = any as ResponseAlbums.ResponseAlbumsItem
                        callApiPhotos(albumsItem.id!!)
                    }

                })
        setRvAdapterHorizontalDefault(detailBinding.rvListAlbumDetailUsers, adapterAlbumsUsers)
    }

    private fun callApiPhotos(albumsItemId: Int) {
        viewmodelUsers.getPhotosAlbums(albumsItemId.toString())
    }

    private fun initCallApi() {
        viewmodelUsers.getAlbumsUsers(responseUsersItem?.id.toString())
        callApiPhotos(1)
    }


}