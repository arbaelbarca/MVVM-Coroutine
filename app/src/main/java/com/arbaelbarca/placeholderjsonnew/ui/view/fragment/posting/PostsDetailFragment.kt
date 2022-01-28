package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.posting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentPostsDetailBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseComments
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseDetailUsers
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePost
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelPosts
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterCommentsPosts
import com.arbaelbarca.placeholderjsonnew.utils.*
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostsDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PostsDetailFragment : BaseFragmentBinding<FragmentPostsDetailBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var postsDetailBinding: FragmentPostsDetailBinding
    var bundle: Bundle? = null
    var postItem: ResponsePost.ResponsePostItem? = null
    val viewmodelUsers: ViewModelUsers by viewModels()
    val viewModelPosts: ViewModelPosts by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsDetailBinding
        get() = FragmentPostsDetailBinding::inflate

    override fun setupView(binding: FragmentPostsDetailBinding) {
        postsDetailBinding = binding
        initial()
    }

    private fun initial() {
        initGetData()
        initObserver()
    }

    private fun initObserver() {
        viewmodelUsers.observerDetailUsers()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is UiState.Loading -> {
                        showView(postsDetailBinding.pbListDetailUsers)
                    }
                    is UiState.Success -> {
                        hideView(postsDetailBinding.pbListDetailUsers)
                        showView(postsDetailBinding.llDetailUsersPosts)
                        val dataItem = it.data
                        initDataDetailUsers(dataItem)
                    }
                    is UiState.Failure -> {
                        hideView(postsDetailBinding.pbListDetailUsers)
                        it.throwable.printStackTrace()
                    }
                }
            }

        viewModelPosts.observerAllCommentsPosts()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is UiState.Loading -> {
                        showView(postsDetailBinding.pbListComments)
                    }
                    is UiState.Success -> {
                        hideView(postsDetailBinding.pbListComments)
                        val dataItem = it.data
                        initAdapterCommentsPost(dataItem)
                    }
                    is UiState.Failure -> {
                        hideView(postsDetailBinding.pbListComments)
                        it.throwable.printStackTrace()
                    }
                }
            }
    }

    private fun initAdapterCommentsPost(dataItem: List<ResponseComments.ResponseCommentsItem>) {
        val adapterCommentsPosts =
            AdapterCommentsPosts(dataItem as MutableList<ResponseComments.ResponseCommentsItem>)
        setRvAdapterVertikalDefault(postsDetailBinding.rvListComments2, adapterCommentsPosts)
    }

    private fun initDataDetailUsers(dataItem: ResponseDetailUsers) {
        postsDetailBinding.tvNameDetailUsers.text = dataItem.name
        postsDetailBinding.tvUserNameDetailUsers.text = dataItem.username
        postsDetailBinding.tvEmailDetailUsers.text = dataItem.email
        postsDetailBinding.tvCompanyDetailUsers.text = dataItem.company?.name
        postsDetailBinding.tvPhoneDetailUsers.text = dataItem.phone
        postsDetailBinding.tvWebsiteDetailsers.text = dataItem.website
        postsDetailBinding.tvAddressDetailUsers.text =
            dataItem.address?.street + ", " + dataItem.address?.city
    }

    private fun initGetData() {
        postItem = bundle?.getParcelable(ConstVar.DATA_POSTS_ITEM)

        postsDetailBinding.tvNameDetailPost.text = postItem?.title
        postsDetailBinding.tvBodyDetailPosts.text = postItem?.body

        callApiUsersPosts()
    }

    private fun callApiUsersPosts() {
        viewmodelUsers.getDetailUsers(postItem?.userId.toString())
        viewModelPosts.getApiAllCommentsPosts(postItem?.id.toString())
    }
}