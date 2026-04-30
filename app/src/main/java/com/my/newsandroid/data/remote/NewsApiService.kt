package com.my.newsandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApiService {
    @GET("home/dev/v1/{id}")
    suspend fun getNews(@Path("id") id: String): NewsResponse

    companion object {
        const val BASE_URL = "https://articlepages-qhnaavzuha-uc.a.run.app/"
    }
}
