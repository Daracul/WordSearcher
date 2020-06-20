package com.daracul.wordsseacher

import com.example.wordsseacher.api.ApiDataSource
import com.daracul.wordsseacher.data.WordsDataSource
import com.daracul.wordsseacher.data.WordsRepositoryImpl
import com.daracul.wordsseacher.data.mappers.WordsMapper
import com.example.wordsseacher.domain.model.WordsRepository
import com.daracul.wordsseacher.domain.usecase.GetWordsUseCase
import com.daracul.wordsseacher.domain.usecase.GetWordsUseCaseImpl
import com.daracul.wordsseacher.presentation.MainViewModelFactory

object Dependencies {

    val viewModelFactory by lazy {
        createViewModelFactory(getWordsUseCase)
    }

    private fun createViewModelFactory(wordsUseCase: GetWordsUseCase): MainViewModelFactory {
        return MainViewModelFactory(wordsUseCase)
    }

    private val getWordsUseCase by lazy {
        createWordsUseCase(wordsRepository)
    }

    private val wordsRepository by lazy {
        createWordsRepository(apiDataSource)
    }

    private val apiDataSource by lazy {
        createWordsDataSource()
    }

    private fun createWordsUseCase(wordsRepository: WordsRepository): GetWordsUseCase {
        return GetWordsUseCaseImpl(wordsRepository)
    }

    private fun createWordsDataSource(): WordsDataSource {
        return ApiDataSource()
    }

    private fun createWordsRepository(dataSource: WordsDataSource): WordsRepository {
        return WordsRepositoryImpl(dataSource, WordsMapper())
    }
}