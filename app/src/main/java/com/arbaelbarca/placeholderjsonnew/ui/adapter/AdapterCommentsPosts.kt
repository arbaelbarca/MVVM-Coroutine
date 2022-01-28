package com.arbaelbarca.placeholderjsonnew.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseComments
import com.arbaelbarca.placeholderjsonnew.utils.BaseGenericAdapter
import kotlinx.android.synthetic.main.layout_item_comments.view.*

class AdapterCommentsPosts(
    val commentsList: MutableList<ResponseComments.ResponseCommentsItem>
) : BaseGenericAdapter(
    commentsList.toMutableList(),
    R.layout.layout_item_comments
) {
    override fun getView(view: View?): View? {
        return view
    }

    override fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?) {
        val commenstItem = commentsList[position]
        viewHolder.apply {
            itemView.apply {
                tvItemNameComments.text = commenstItem.name
                tvItemEmailComments.text = commenstItem.email
                tvItemBodyComments.text = commenstItem.body
            }
        }
    }

}