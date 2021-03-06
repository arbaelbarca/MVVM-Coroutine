package com.arbaelbarca.placeholderjsonnew.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ResponsePhotosAlbums : ArrayList<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>() {
    @Parcelize
    data class ResponsePhotosAlbumsItem(
        val albumId: Int?,
        val id: Int?,
        val thumbnailUrl: String?,
        val title: String?,
        val url: String?
    ) : Parcelable
}