package com.example.assessment_image_jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.assessment_image_jetpackcompose.repo.ListUnsplashRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(repository: ListUnsplashRepo) : ViewModel() {

    val unsplashImg =
        repository.getData().cachedIn(viewModelScope)


}