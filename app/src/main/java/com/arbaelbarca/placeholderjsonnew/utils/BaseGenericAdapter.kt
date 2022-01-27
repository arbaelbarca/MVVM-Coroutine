package com.arbaelbarca.placeholderjsonnew.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseGenericAdapter(
    var dataList: MutableList<Any> = ArrayList(),
    @LayoutRes val layout_id: Int
) :
    RecyclerView.Adapter<BaseGenericAdapter.MyViewHolder?>() {
    var mItemView: View? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(layout_id, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        onBindViewHold(position, viewHolder, dataList[position])
    }

    abstract fun getView(view: View?): View?

    override fun getItemCount(): Int {
        return if (dataList.size == 0) 0 else dataList.size
    }

    abstract fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?)

    inner class MyViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        init {
            mItemView = itemView
            getView(mItemView)
        }
    }

}