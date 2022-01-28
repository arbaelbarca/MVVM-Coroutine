package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.albums

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentAlbumsBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterAlbumsUsers
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.ui.view.activity.FrameLayoutActivity
import com.arbaelbarca.placeholderjsonnew.utils.ConstVar
import com.arbaelbarca.placeholderjsonnew.utils.UiState
import com.arbaelbarca.placeholderjsonnew.utils.setRvAdapterVertikalDefault
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AlbumsFragment(
    usersItem: ResponseUsers.ResponseUsersItem
) : BaseFragmentBinding<FragmentAlbumsBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val viewmodelUsers: ViewModelUsers by viewModels()
    lateinit var albumsBinding: FragmentAlbumsBinding
    var responseUsersItem: ResponseUsers.ResponseUsersItem? = null

    init {
        this.responseUsersItem = usersItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) {

        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumsBinding
        get() = FragmentAlbumsBinding::inflate

    override fun setupView(binding: FragmentAlbumsBinding) {
        albumsBinding = binding
        initial()
    }

    private fun initial() {
        initObserver()
        initCallApi()
    }

    private fun initCallApi() {
        viewmodelUsers.getAlbumsUsers(responseUsersItem?.id.toString())
    }

    private fun initObserver() {
        viewmodelUsers.observerGetAlbumsUsers()
            .observe(viewLifecycleOwner) {
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
            }
    }


    private fun initAdapter(dataItem: List<ResponseAlbums.ResponseAlbumsItem>) {
        val adapterAlbumsUsers =
            AdapterAlbumsUsers(dataItem as MutableList<ResponseAlbums.ResponseAlbumsItem>,
                object : OnClickItem {
                    override fun clickItem(pos: Int, any: Any) {
                        val albumsItem = any as ResponseAlbums.ResponseAlbumsItem
                        val intent = Intent(requireContext(), FrameLayoutActivity::class.java)
                            .putExtra("pos", 3)
                            .putExtra(ConstVar.DATA_ALBUMS_ITEM, albumsItem)
                        startActivity(intent)
                    }
                })
        setRvAdapterVertikalDefault(albumsBinding.rvListAlbumDetailUsers, adapterAlbumsUsers)
    }


}