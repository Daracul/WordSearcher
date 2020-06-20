package com.daracul.wordsseacher.presentation.main

import androidx.lifecycle.LiveData
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
    private val _progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _showErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var previousQuery = ""

    val wordsLiveData: LiveData<List<Word>> = _wordsLiveData
    val navigateDetailsLiveData: LiveData<SingleEvent<Word>> = _navigateDetailsLiveData
    val progressLiveData: LiveData<Boolean> = _progressLiveData
    val showErrorLiveData:LiveData<Boolean> = _showErrorLiveData

    fun searchWord(word: String) {
        if (previousQuery != word) {
            previousQuery = word
            _progressLiveData.postValue(true)
            getWordsUseCase.getWords(word) { getWordsResult ->
                when (getWordsResult) {
                    is GetWordsResult.Success -> {
                        _wordsLiveData.postValue(getWordsResult.words)
                        _showErrorLiveData.postValue(getWordsResult.words.isNullOrEmpty())
                        _progressLiveData.postValue(false)
                    }
                    is GetWordsResult.Error -> {
                        getWordsResult.message
                        _progressLiveData.postValue(false)
                        _showErrorLiveData.postValue(true)
                    }
                }
            }
        }
    }

    fun doClick(word: Word) {
        _navigateDetailsLiveData.postValue(SingleEvent(word))
    }

}