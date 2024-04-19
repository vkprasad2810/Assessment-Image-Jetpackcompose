package com.example.assessment_image_jetpackcompose.retrofit

import com.example.assessment_image_jetpackcompose.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("photos")
    suspend fun postList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("client_id") client_id: String = "vttKMB8rQrSbx55WNR0Mw9yPREHrMXS_JdEXCWJBF60"
    ): List<UnsplashResponse>


}
