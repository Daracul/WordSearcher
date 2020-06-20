package com.daracul.wordsseacher.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsseacher.domain.model.Word

class DetailsViewModelFactory(
    private val word: Word
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(word) as T
    }
}