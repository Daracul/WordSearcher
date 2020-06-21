package com.daracul.wordsearcher.data

import com.daracul.wordsearcher.api.response.WordJson
import com.daracul.wordsearcher.domain.mappers.Mapper
import com.example.wordsearcher.domain.model.ResultCallback
import com.daracul.wordsearcher.domain.model.Word
import com.example.wordsearcher.domain.model.WordsRepository

class WordsRepositoryImpl(
    private val wordsDataSource: WordsDataSource,
    private val wordsMapper: Mapper<WordJson, Word>
) : WordsRepository {

    override fun getWords(query: String, callback: ResultCallback) {
        wordsDataSource.getWords(query) { result ->
            when (result) {
                is WordsResult.Success -> {
                    val wordsList = result.wordsList.map { wordJson ->
                        wordsMapper.map(wordJson)
                    }
                    callback.onSuccess(wordsList)
                }
                is WordsResult.Error -> callback.onError(result.error)
            }
        }

    }
}