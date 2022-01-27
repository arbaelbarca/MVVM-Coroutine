package com.arbaelbarca.placeholderjsonnew.presentation.repository

import com.arbaelbarca.placeholderjsonnew.data.api.ApiService
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import javax.inject.Inject

class RepositoryUsers @Inject constructor(val apiService: ApiService) {

    suspend fun callUsersList(): List<ResponseUsers.ResponseUsersItem> {
        return apiService.getAllUsers()
    }

    suspend fun callAlbumsUsers(id: String): List<ResponseAlbums.ResponseAlbumsItem> {
        return apiService.getAlbumsUsers(id)
    }

    suspend fun callPhotosAlbums(id: String): List<ResponsePhotosAlbums.ResponsePhotosAlbumsItem> {
        return apiService.getPhotosAlbums(id)
    }
}