package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentHomeBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.presentation.viewmodel.ViewModelUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterUsersList
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
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class UserFragment : BaseFragmentBinding<FragmentHomeBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var homeBinding: FragmentHomeBinding
    val viewmodelUsers: ViewModelUsers by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setupView(binding: FragmentHomeBinding) {
        homeBinding = binding
        initial()
    }

    private fun initial() {
        initCallApi()
        initObserver()
    }

    private fun initObserver() {
        viewmodelUsers.observerGetUsers()
            .observe(viewLifecycleOwner, {
                when (it) {
                    is UiState.Loading -> {
                        showView(homeBinding.pbList)
                    }
                    is UiState.Success -> {
                        hideView(homeBinding.pbList)
                        val dataItem = it.data
                        initAdapter(dataItem)
                    }
                    is UiState.Failure -> {
                        hideView(homeBinding.pbList)
                    }
                }
            })
    }

    private fun initAdapter(dataItem: List<ResponseUsers.ResponseUsersItem>) {
        val adapterUsersList =
            AdapterUsersList(dataItem as MutableList<ResponseUsers.ResponseUsersItem>,
                object : OnClickItem {
                    override fun clickItem(pos: Int, any: Any) {
                        val usersItem = any as ResponseUsers.ResponseUsersItem
                        val intent = Intent(requireContext(), FrameLayoutActivity::class.java)
                            .putExtra("pos", 1)
                            .putExtra(ConstVar.DATA_USERS, usersItem)
                        startActivity(intent)
                    }

                })
        setRvAdapterVertikalDefault(homeBinding.rvUserList, adapterUsersList)
    }

    private fun initCallApi() {
        viewmodelUsers.getUsersList()
    }
}