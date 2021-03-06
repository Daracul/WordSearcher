package com.daracul.wordsearcher.domain.usecase

import com.example.wordsearcher.domain.model.ResultCallback
import com.daracul.wordsearcher.domain.model.Word
import com.example.wordsearcher.domain.model.WordsRepository

interface GetWordsUseCase {
    fun getWords(query: String, callback: (GetWordsResult) -> Unit)
}

class GetWordsUseCaseImpl(
    private val wordsRepository: WordsRepository
) : GetWordsUseCase {
    override fun getWords(query: String, callback: (GetWordsResult) -> Unit) {
        wordsRepository.getWords(query, object : ResultCallback {
            override fun onSuccess(words: List<Word>) {
                callback(GetWordsResult.Success(words))
            }

            override fun onError(error: String) {
                callback(GetWordsResult.Error(error))
            }
        })
    }
}

sealed class GetWordsResult {
    data class Success(val words: List<Word>) : GetWordsResult()
    data class Error(val message: String) : GetWordsResult()
}