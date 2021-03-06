package com.arbaelbarca.placeholderjsonnew.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(url: String, context: Context) {
    Glide.with(context)
        .load(url)
//        .error(R.drawable.no_image)
//        .placeholder(R.drawable.no_image)
        .transition(DrawableTransitionOptions.withCrossFade(1000))
        .into(this)
}

fun ImageView.loadImageUrlPicasso(url: String) {
    Picasso.get()
        .load(url)
//        .error(R.drawable.no_image)
//        .placeholder(R.drawable.no_image)
        .into(this)
}

fun TextView.setColorText(context: Context, color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}

fun CardView.setColorBackground(context: Context, color: Int) {
    this.setBackgroundColor(ContextCompat.getColor(context, color))
}

fun showView(view: View) {
    view.visibility = View.VISIBLE
}

fun hideView(view: View) {
    view.visibility = View.GONE
}


fun showToast(message: String?, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun setRvAdapterVertikalDefault(
    recyclerView: RecyclerView,
    adapterDefault: BaseGenericAdapter
) {
    recyclerView.apply {
        adapter = adapterDefault
        layoutManager = LinearLayoutManager(context)
        hasFixedSize()
    }
}


fun setRvAdapterHorizontalDefault(
    recyclerView: RecyclerView,
    adapterDefault: BaseGenericAdapter
) {
    recyclerView.apply {
        adapter = adapterDefault
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hasFixedSize()
    }
}


fun setRvAdapterGridLayoutDefault(
    recyclerView: RecyclerView,
    adapterDefault: BaseGenericAdapter,
    countLayout: Int
) {
    recyclerView.apply {
        adapter = adapterDefault
        layoutManager = GridLayoutManager(context, countLayout)
        hasFixedSize()
    }
}