package com.daracul.wordsseacher.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daracul.wordsseacher.domain.model.Word
import com.daracul.wordsseacher.domain.usecase.GetWordsResult
import com.daracul.wordsseacher.domain.usecase.GetWordsUseCase
import com.daracul.wordsseacher.utils.SingleEvent

class MainViewModel(
    private val getWordsUseCase: GetWordsUseCase
) : ViewModel(
) {
    private val _wordsLiveData: MutableLiveData<List<Word>> = MutableLiveData()
    private val _navigateDetailsLiveData: MutableLiveData<SingleEvent<Word>> = MutableLiveData()

    val wordsLiveData = _wordsLiveData
    val navigateDetailsLiveData = _navigateDetailsLiveData

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

    fun doClick(word: Word) {
        _navigateDetailsLiveData.postValue(SingleEvent(word))
    }

}