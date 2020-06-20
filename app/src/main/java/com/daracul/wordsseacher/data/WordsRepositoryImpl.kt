package com.daracul.wordsseacher.data

import com.daracul.wordsseacher.api.response.WordJson
import com.daracul.wordsseacher.domain.mappers.Mapper
import com.example.wordsseacher.domain.model.ResultCallback
import com.daracul.wordsseacher.domain.model.Word
import com.example.wordsseacher.domain.model.WordsRepository

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