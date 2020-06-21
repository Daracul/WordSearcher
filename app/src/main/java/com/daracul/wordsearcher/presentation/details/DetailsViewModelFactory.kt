package com.daracul.wordsearcher.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsearcher.domain.model.Word

class DetailsViewModelFactory(
    private val word: Word
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(word) as T
    }
}