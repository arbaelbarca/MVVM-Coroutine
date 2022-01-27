package com.arbaelbarca.placeholderjsonnew.ui.adapter

import android.view.View
import androidx.core.content.ContextCompat
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

    var selectPosition = 0

    override fun getView(view: View?): View? {
        return view
    }

    override fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?) {
        viewHolder.apply {
            itemView.apply {
                tvNameAlbumsUsers.text = listAlbumsUsers[position].title

                setOnClickListener {
                    selectPosition = position
                    onClickItem.clickItem(position, listAlbumsUsers[position])
                    notifyDataSetChanged()
                }

                if (selectPosition == position) {
                    cvItemAlbumsUsers.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context, R.color.black
                        )
                    )

                    tvNameAlbumsUsers.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.white
                        )
                    )
                } else {
                    cvItemAlbumsUsers.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context, R.color.white
                        )
                    )

                    tvNameAlbumsUsers.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.black
                        )
                    )
                }
            }
        }
    }
}