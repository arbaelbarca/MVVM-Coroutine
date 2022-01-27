package com.arbaelbarca.placeholderjsonnew.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ResponseUsers : ArrayList<ResponseUsers.ResponseUsersItem>() {
    @Parcelize
    data class ResponseUsersItem(
        val address: Address?,
        val company: Company?,
        val email: String?,
        val id: Int?,
        val name: String?,
        val phone: String?,
        val username: String?,
        val website: String?
    ) : Parcelable {
        @Parcelize
        data class Address(
            val city: String?,
            val geo: Geo?,
            val street: String?,
            val suite: String?,
            val zipcode: String?
        ) : Parcelable {
            @Parcelize
            data class Geo(
                val lat: String?,
                val lng: String?
            ) : Parcelable
        }

        @Parcelize
        data class Company(
            val bs: String?,
            val catchPhrase: String?,
            val name: String?
        ) : Parcelable
    }
}