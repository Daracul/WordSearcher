package com.daracul.wordsearcher.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsearcher.domain.usecase.GetWordsUseCase

class MainViewModelFactory(
    private val getWordsUseCase: GetWordsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(getWordsUseCase) as T
    }
}