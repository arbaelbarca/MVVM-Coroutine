package com.arbaelbarca.placeholderjsonnew.presentation.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ResponseComments : ArrayList<ResponseComments.ResponseCommentsItem>(){
    @Parcelize
    data class ResponseCommentsItem(
        val body: String?,
        val email: String?,
        val id: Int?,
        val name: String?,
        val postId: Int?
    ) : Parcelable
}