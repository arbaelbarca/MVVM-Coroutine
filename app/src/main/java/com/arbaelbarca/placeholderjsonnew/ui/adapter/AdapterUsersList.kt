package com.arbaelbarca.placeholderjsonnew.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.utils.BaseGenericAdapter
import kotlinx.android.synthetic.main.layout_item_users.view.*

class AdapterUsersList(
    val usersList: MutableList<ResponseUsers.ResponseUsersItem>,
    val onClickItem: OnClickItem
) : BaseGenericAdapter(
    usersList.toMutableList(),
    R.layout.layout_item_users
) {
    override fun getView(view: View?): View? {
        return view
    }

    override fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?) {
        val usersItem = usersList[position]
        viewHolder.apply {
            itemView.apply {
                tvNameUsers.text = "Name : ${usersItem.name}"
                tvItemUserNameUsers.text = "Username : ${usersItem.username}"
                tvItemEmailUsers.text = "Email : ${usersItem.email}"
                tvItemCompanyUsers.text = "Company : ${usersItem.company?.name}"

                setOnClickListener {
                    onClickItem.clickItem(position, usersItem)
                }
            }
        }
    }
}