package com.daracul.wordsseacher.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daracul.wordsseacher.domain.model.Word

class DetailsViewModel(
    word: Word
) : ViewModel() {
    private val _wordLiveData: MutableLiveData<Word> = MutableLiveData()
    val wordLiveData: LiveData<Word> = _wordLiveData

    init {
        _wordLiveData.postValue(word)
    }
}