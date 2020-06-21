package com.example.wordsearcher.api

import com.daracul.wordsearcher.api.NetworkService
import com.daracul.wordsearcher.api.response.WordJson
import com.daracul.wordsearcher.data.WordsDataSource
import com.daracul.wordsearcher.data.WordsResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSource : WordsDataSource {

    override fun getWords(query: String, callback: (WordsResult) -> Unit) {
        NetworkService.api.getWords(query).enqueue(object : Callback<List<WordJson>> {
            override fun onFailure(call: Call<List<WordJson>>, t: Throwable) {
                callback(WordsResult.Error(t.message ?: "Network error"))
            }

            override fun onResponse(
                call: Call<List<WordJson>>,
                response: Response<List<WordJson>>
            ) {
                if (response.code() == 200) {
                    callback(WordsResult.Success(response.body() ?: emptyList()))
                } else callback(WordsResult.Error("Server side error"))

            }
        })

    }
}