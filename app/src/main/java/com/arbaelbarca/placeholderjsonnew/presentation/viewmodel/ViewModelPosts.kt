package com.arbaelbarca.placeholderjsonnew.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseComments
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePost
import com.arbaelbarca.placeholderjsonnew.presentation.repository.RepositoryPosts
import com.arbaelbarca.placeholderjsonnew.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPosts @Inject constructor(
    val respositoryPosts: RepositoryPosts
) : ViewModel() {
    val stateAllPost = MutableLiveData<UiState<List<ResponsePost.ResponsePostItem>>>()
    val stateAllCommentsPosts =
        MutableLiveData<UiState<List<ResponseComments.ResponseCommentsItem>>>()

    fun observerGetPost() = stateAllPost
    fun observerAllCommentsPosts() = stateAllCommentsPosts

    fun getApiAllCommentsPosts(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                respositoryPosts.callApiAllCommentsPosts(id)
            }.onSuccess {
                stateAllCommentsPosts.value = UiState.Success(it)
            }.onFailure {
                stateAllCommentsPosts.value = UiState.Failure(it)
            }
        }
    }

    fun getApiAllPost() {
        viewModelScope.launch {
            kotlin.runCatching {
                respositoryPosts.callApiAllPosts()
            }.onSuccess {
                stateAllPost.value = UiState.Success(it)
            }.onFailure {
                stateAllPost.value = UiState.Failure(it)
            }
        }
    }
}