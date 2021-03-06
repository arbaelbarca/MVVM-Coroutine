package com.arbaelbarca.placeholderjsonnew.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.placeholderjsonnew.R
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.ui.listener.OnClickItem
import com.arbaelbarca.placeholderjsonnew.utils.BaseGenericAdapter
import com.arbaelbarca.placeholderjsonnew.utils.loadImageUrlPicasso
import kotlinx.android.synthetic.main.layout_item_photos_albums.view.*

class AdapterPhotosAlbums(
    val listPhotosAlbums: MutableList<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>,
    val onClickItem: OnClickItem
) : BaseGenericAdapter(
    listPhotosAlbums.toMutableList(),
    R.layout.layout_item_photos_albums
) {
    override fun getView(view: View?): View? {
        return view
    }

    override fun onBindViewHold(position: Int, viewHolder: RecyclerView.ViewHolder, any: Any?) {
        val photosItem = listPhotosAlbums[position]
        viewHolder.apply {
            itemView.apply {
                imgItemPhotos.loadImageUrlPicasso(photosItem.thumbnailUrl!!)
                tvItemTitlePhotos.text = photosItem.title

                setOnClickListener {
                    onClickItem.clickItem(position, photosItem)
                }
            }
        }
    }
}