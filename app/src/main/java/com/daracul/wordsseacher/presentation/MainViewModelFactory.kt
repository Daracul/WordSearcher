package com.daracul.wordsseacher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsseacher.domain.usecase.GetWordsUseCase
import com.daracul.wordsseacher.presentation.main.MainViewModel

class MainViewModelFactory(
    private val getWordsUseCase: GetWordsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(getWordsUseCase) as T
    }
}