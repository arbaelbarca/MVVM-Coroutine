package com.arbaelbarca.placeholderjsonnew.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseDetailUsers
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponsePhotosAlbums
import com.arbaelbarca.placeholderjsonnew.presentation.model.ResponseUsers
import com.arbaelbarca.placeholderjsonnew.presentation.repository.RepositoryUsers
import com.arbaelbarca.placeholderjsonnew.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUsers @Inject constructor(val repositoryUsers: RepositoryUsers) : ViewModel() {

    val stateGetUsers = MutableLiveData<UiState<List<ResponseUsers.ResponseUsersItem>>>()
    val stateGetDetailUsers = MutableLiveData<UiState<ResponseDetailUsers>>()
    val stateGetAlbumsUsers = MutableLiveData<UiState<List<ResponseAlbums.ResponseAlbumsItem>>>()
    val stateGetPhotosAlbums =
        MutableLiveData<UiState<List<ResponsePhotosAlbums.ResponsePhotosAlbumsItem>>>()

    fun observerGetUsers() = stateGetUsers
    fun observerGetAlbumsUsers() = stateGetAlbumsUsers
    fun observerGetPhotosAlbums() = stateGetPhotosAlbums
    fun observerDetailUsers() = stateGetDetailUsers

    fun getDetailUsers(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repositoryUsers.callDetailUsers(id)
            }.onSuccess {
                stateGetDetailUsers.value = UiState.Success(it)
            }.onFailure {
                stateGetDetailUsers.value = UiState.Failure(it)
            }
        }
    }

    fun getUsersList() {
        viewModelScope.launch {
            kotlin.runCatching {
                repositoryUsers.callUsersList()
            }.onSuccess {
                stateGetUsers.value = UiState.Success(it)
            }.onFailure {
                stateGetUsers.value = UiState.Failure(it)
            }
        }
    }

    fun getAlbumsUsers(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repositoryUsers.callAlbumsUsers(id)
            }.onSuccess {
                stateGetAlbumsUsers.value = UiState.Success(it)
            }.onFailure {
                stateGetAlbumsUsers.value = UiState.Failure(it)
            }
        }
    }

    fun getPhotosAlbums(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repositoryUsers.callPhotosAlbums(id)
            }.onSuccess {
                stateGetPhotosAlbums.value = UiState.Success(it)
            }.onFailure {
                stateGetPhotosAlbums.value = UiState.Failure(it)
            }
        }
    }
}