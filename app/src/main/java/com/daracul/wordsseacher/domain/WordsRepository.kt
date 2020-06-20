package com.example.wordsseacher.domain.model

import com.daracul.wordsseacher.domain.model.Word

interface WordsRepository {
    fun getWords(query:String, callback: ResultCallback)
}

interface ResultCallback {
    fun onSuccess(words: List<Word>)
    fun onError(error: String)
}