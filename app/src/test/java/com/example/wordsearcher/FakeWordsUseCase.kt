package com.example.wordsearcher

import com.daracul.wordsearcher.domain.model.Word
import com.daracul.wordsearcher.domain.usecase.GetWordsResult
import com.daracul.wordsearcher.domain.usecase.GetWordsUseCase

class FakeWordsUseCase : GetWordsUseCase {
    private val wordsList: List<Word> = listOf(
        FakeWordsFactory.createFakeWord()
    )

    override fun getWords(query: String, callback: (GetWordsResult) -> Unit) {
        when (query) {
            "success" -> callback(GetWordsResult.Success(wordsList))
            "error" -> callback(GetWordsResult.Error("error"))
        }
    }
}