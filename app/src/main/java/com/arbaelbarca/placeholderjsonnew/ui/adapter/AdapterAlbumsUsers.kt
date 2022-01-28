package com.arbaelbarca.placeholderjsonnew.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.utils.BaseGenericAdapter
import kotlinx.android.synthetic.main.layout_item_albums_users.view.*

class AdapterAlbumsUsers(
    val listAlbumsUsers: MutableList<ResponseAlbums.ResponseAlbumsItem>,
    val onClickItem: OnClickItem
) : BaseGenericAdapter(
    listAlbumsUsers.toMutableList(),
    R.layout.layout_item_albums_users
) {

//    var selectPosition = 0

    override fun getView(view: View?): View? {
        return view
    }

    override fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?) {
        viewHolder.apply {
            itemView.apply {
                tvNameAlbumsUsers.text = listAlbumsUsers[position].title

                setOnClickListener {
//                    selectPosition = position
                    onClickItem.clickItem(position, listAlbumsUsers[position])
                }

//                if (selectPosition == position) {
//                    cvItemAlbumsUsers.setColorBackground(context, R.color.black)
//                    tvNameAlbumsUsers.setColorText(context, R.color.white)
//                } else {
//                    cvItemAlbumsUsers.setColorBackground(context, R.color.white)
//                    tvNameAlbumsUsers.setColorText(context, R.color.black)
//                }
            }
        }
    }
}