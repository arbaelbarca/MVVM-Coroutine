package com.arbaelbarca.placeholderjsonnew.ui.view.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arbaelbarca.placeholderjsonnew.data.base.BaseFragmentBinding
import com.arbaelbarca.placeholderjsonnew.databinding.FragmentUserDetailBinding
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.ui.adapter.AdapterViewPager2
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnFragmentViewPager
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.albums.AlbumsFragment
import com.arbaelbarca.placeholderjsonnew.ui.view.fragment.posting.PostingFragment
import com.arbaelbarca.placeholderjsonnew.utils.ConstVar
import com.google.android.material.tabs.TabLayoutMediator
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

    val arrayTab = arrayOf(
        "Albums List",
        "Posts List"
    )

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
        initViewPager()
    }

    private fun initViewPager() {
        val adapter = AdapterViewPager2(
            childFragmentManager,
            lifecycle, object : OnFragmentViewPager {
                override fun setFragmentViewPager(pos: Int): Fragment {
                    when (pos) {
                        0 -> return AlbumsFragment(responseUsersItem!!)
                        1 -> return PostingFragment()
                        else -> return AlbumsFragment(responseUsersItem!!)
                    }
                }

            })
        detailBinding.viewPager2.adapter = adapter

        TabLayoutMediator(detailBinding.tabLayout, detailBinding.viewPager2) { tab, position ->
            tab.text = arrayTab[position]
        }.attach()
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


}