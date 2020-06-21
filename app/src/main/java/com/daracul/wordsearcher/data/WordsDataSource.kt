package com.daracul.wordsearcher.data

import com.daracul.wordsearcher.api.response.WordJson

interface WordsDataSource {

    fun getWords(query: String, callback: (WordsResult) -> Unit)

}

sealed class WordsResult {
    data class Success(val wordsList: List<WordJson>) : WordsResult()
    data class Error(val error: String) : WordsResult()

}