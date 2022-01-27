package com.arbaelbarca.placeholderjsonnew.presentation.model

class ResponseAlbums : ArrayList<ResponseAlbums.ResponseAlbumsItem>(){
    data class ResponseAlbumsItem(
        val id: Int?,
        val title: String?,
        val userId: Int?
    )
}