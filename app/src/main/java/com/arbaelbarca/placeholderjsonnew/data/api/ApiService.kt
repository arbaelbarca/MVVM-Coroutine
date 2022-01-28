package com.arbaelbarca.placeholderjsonnew.data.api

import com.arbaelbarca.placeholderjsonnew.presentation.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(

    ): List<ResponseUsers.ResponseUsersItem>

    @GET("users/{id_users}/albums")
    suspend fun getAlbumsUsers(
        @Path("id_users") idUsers: String
    ): List<ResponseAlbums.ResponseAlbumsItem>

    @GET("albums/{id_albums}/photos")
    suspend fun getPhotosAlbums(
        @Path("id_albums") idAlbums: String
    ): List<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>


    @GET("posts")
    suspend fun getAllPost(

    ): List<ResponsePost.ResponsePostItem>

    @GET("users/{iduser}")
    suspend fun getDetailUsers(
        @Path("iduser") iduser: String
    ): ResponseDetailUsers

    @GET("posts/{id}/comments")
    suspend fun getAllComments(
        @Path("id") id: String
    ): List<ResponseComments.ResponseCommentsItem>


}