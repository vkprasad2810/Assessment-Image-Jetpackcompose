package com.example.assessment_image_jetpackcompose.utils

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assessment_image_jetpackcompose.model.UnsplashResponse
import com.example.assessment_image_jetpackcompose.repo.PAGE_SIZE

import com.example.assessment_image_jetpackcompose.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(private val postsApi: ApiService) : PagingSource<Int, UnsplashResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashResponse> {

        val page = params.key ?: STARTING_PAGE_INDEX
        val startTime: Long = System.currentTimeMillis()

        return try {

            val response = postsApi.postList(page = page, PAGE_SIZE)
            Log.d(
                "TIMING",
                "TASK took :   ${((System.currentTimeMillis() - startTime))} millisecond"
            )
            LoadResult.Page(
                data = response,
                nextKey = if (response.isEmpty()) null else page + 1,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, UnsplashResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
