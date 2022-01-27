package com.arbaelbarca.placeholderjsonnew.presentation.model

class ResponsePhotosAlbums : ArrayList<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>(){
    data class ResponsePhotosAlbumsItem(
        val albumId: Int?,
        val id: Int?,
        val thumbnailUrl: String?,
        val title: String?,
        val url: String?
    )
}