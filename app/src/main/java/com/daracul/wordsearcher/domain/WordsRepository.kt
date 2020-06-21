package com.example.wordsearcher.domain.model

import com.daracul.wordsearcher.domain.model.Word

interface WordsRepository {
    fun getWords(query:String, callback: ResultCallback)
}

interface ResultCallback {
    fun onSuccess(words: List<Word>)
    fun onError(error: String)
}