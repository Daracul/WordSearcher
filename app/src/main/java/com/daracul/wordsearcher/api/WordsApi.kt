package com.daracul.wordsearcher.api

import com.daracul.wordsearcher.api.response.WordJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApi {
    @GET("words/search")
    fun getWords(
        @Query("search") search: String
    ): Call<List<WordJson>>
}