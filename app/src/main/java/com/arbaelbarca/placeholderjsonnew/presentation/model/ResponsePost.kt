package com.arbaelbarca.placeholderjsonnew.presentation.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ResponsePost : ArrayList<ResponsePost.ResponsePostItem>(){
    @Parcelize
    data class ResponsePostItem(
        val body: String?,
        val id: Int?,
        val title: String?,
        val userId: Int?
    ) : Parcelable
}