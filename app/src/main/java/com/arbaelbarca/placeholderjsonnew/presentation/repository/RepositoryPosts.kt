package com.arbaelbarca.placeholderjsonnew.presentation.repository

import com.arbaelbarca.placeholderjsonnew.data.api.ApiService
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseComments
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePost
import javax.inject.Inject

class RepositoryPosts @Inject constructor(
    val apiService: ApiService
) {

    suspend fun callApiAllPosts(): List<ResponsePost.ResponsePostItem> {
        return apiService.getAllPost()
    }

    suspend fun callApiAllCommentsPosts(id: String): List<ResponseComments.ResponseCommentsItem> {
        return apiService.getAllComments(id)
    }
}