package com.example.assessment_image_jetpackcompose.repo

import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.liveData
import com.example.assessment_image_jetpackcompose.model.UnsplashResponse
import com.example.assessment_image_jetpackcompose.retrofit.ApiService
import com.example.assessment_image_jetpackcompose.utils.UnsplashPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


const val PAGE_SIZE = 20

class ListUnsplashRepo @Inject constructor(private val unsplashApi: ApiService) {

    fun getData(): Flow<PagingData<UnsplashResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UnsplashPagingSource(postsApi = unsplashApi)
            }
        ).liveData.map {
            val imgMap = mutableSetOf<String>()
            it.filter { unsplash ->
                if (imgMap.contains(unsplash.id)) {
                    false
                } else {
                    imgMap.add(unsplash.id ?: "")
                }
            }
        }.asFlow()
    }


}