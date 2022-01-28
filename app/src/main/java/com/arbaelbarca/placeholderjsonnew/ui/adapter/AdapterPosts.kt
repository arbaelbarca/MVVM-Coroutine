package com.arbaelbarca.placeholderjsonnew.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePost
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.utils.BaseGenericAdapter
import kotlinx.android.synthetic.main.layout_item_all_posts.view.*

class AdapterPosts(
    val listPost: MutableList<ResponsePost.ResponsePostItem>,
    val onClickItem: OnClickItem
) : BaseGenericAdapter(
    listPost.toMutableList(),
    R.layout.layout_item_all_posts
) {
    override fun getView(view: View?): View? {
        return view
    }

    override fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?) {
        val itemPosts = listPost[position]
        viewHolder.apply {
            itemView.apply {
                tvItemNamePosts.text = itemPosts.title
                tvItemBodyPosts.text = itemPosts.body

                setOnClickListener {
                    onClickItem.clickItem(position, itemPosts)
                }
            }
        }
    }
}