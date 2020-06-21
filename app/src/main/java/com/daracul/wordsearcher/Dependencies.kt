package com.daracul.wordsearcher

import com.example.wordsearcher.api.ApiDataSource
import com.daracul.wordsearcher.data.WordsDataSource
import com.daracul.wordsearcher.data.WordsRepositoryImpl
import com.daracul.wordsearcher.data.mappers.WordsMapper
import com.daracul.wordsearcher.domain.model.Word
import com.example.wordsearcher.domain.model.WordsRepository
import com.daracul.wordsearcher.domain.usecase.GetWordsUseCase
import com.daracul.wordsearcher.domain.usecase.GetWordsUseCaseImpl
import com.daracul.wordsearcher.presentation.details.DetailsViewModelFactory
import com.daracul.wordsearcher.presentation.main.MainViewModelFactory

object Dependencies {

    val viewModelFactory by lazy {
        createViewModelFactory(getWordsUseCase)
    }


    private fun createViewModelFactory(wordsUseCase: GetWordsUseCase): MainViewModelFactory {
        return MainViewModelFactory(
            wordsUseCase
        )
    }

    fun createDetailsViewModelFactory(word:Word):DetailsViewModelFactory{
        return DetailsViewModelFactory(word)
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