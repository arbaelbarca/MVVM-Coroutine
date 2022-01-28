package com.arbaelbarca.placeholderjsonnew.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ResponseAlbums : ArrayList<ResponseAlbums.ResponseAlbumsItem>() {
    @Parcelize
    data class ResponseAlbumsItem(
        val id: Int?,
        val title: String?,
        val userId: Int?
    ) : Parcelable
}