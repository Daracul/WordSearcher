package com.daracul.wordsseacher.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daracul.wordsseacher.domain.model.Word
import com.daracul.wordsseacher.domain.usecase.GetWordsResult
import com.daracul.wordsseacher.domain.usecase.GetWordsUseCase

class MainViewModel(
    private val getWordsUseCase: GetWordsUseCase
) : ViewModel(
) {
    private val _wordsLiveData:MutableLiveData<List<Word>> = MutableLiveData()
    val wordsLiveData = _wordsLiveData

    fun searchWord(word: String) {
        getWordsUseCase.getWords(word) { getWordsResult ->
            when (getWordsResult) {
                is GetWordsResult.Success -> {
                    wordsLiveData.postValue(getWordsResult.words)
                }
                is GetWordsResult.Error -> {
                    getWordsResult.message
                }
            }
        }
    }

}