package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.posting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentPostingBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePost
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelPosts
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterPosts
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
 * Use the [PostingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PostingFragment(
) : BaseFragmentBinding<FragmentPostingBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var postingBinding: FragmentPostingBinding
    val viewModelPosts: ViewModelPosts by viewModels()
    var responseUsersItem: ResponseUsers.ResponseUsersItem? = null

//    init {
//        this.responseUsersItem = usersItem
//    }

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

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostingBinding
        get() = FragmentPostingBinding::inflate

    override fun setupView(binding: FragmentPostingBinding) {
        postingBinding = binding
        initial()
    }

    private fun initial() {
        initCallApi()
        initObserver()
    }

    private fun initObserver() {
        viewModelPosts.observerGetPost()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is UiState.Loading -> {
                        showView(postingBinding.pbList)
                    }
                    is UiState.Success -> {
                        hideView(postingBinding.pbList)
                        val dataItem = it.data
                        initAdapter(dataItem)
                    }
                    is UiState.Failure -> {
                        hideView(postingBinding.pbList)
                    }
                }
            }
    }

    private fun initAdapter(dataItem: List<ResponsePost.ResponsePostItem>) {
        val adapterPosts = AdapterPosts(dataItem as MutableList<ResponsePost.ResponsePostItem>,
            object : OnClickItem {
                override fun clickItem(pos: Int, any: Any) {
                    val postItem = any as ResponsePost.ResponsePostItem
                    val intent = Intent(requireContext(), FrameLayoutActivity::class.java)
                        .putExtra("pos", 4)
                        .putExtra(ConstVar.DATA_POSTS_ITEM, postItem)
                    startActivity(intent)
                }
            })
        setRvAdapterVertikalDefault(postingBinding.rvListPosts, adapterPosts)
    }

    private fun initCallApi() {
        viewModelPosts.getApiAllPost()
    }
}