package com.daracul.wordsearcher.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daracul.wordsearcher.domain.model.Word
import com.daracul.wordsearcher.domain.usecase.GetWordsResult
import com.daracul.wordsearcher.domain.usecase.GetWordsUseCase
import com.daracul.wordsearcher.utils.SingleEvent

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
    val showErrorLiveData: LiveData<Boolean> = _showErrorLiveData

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

    fun openDetails(word: Word) {
        _navigateDetailsLiveData.postValue(SingleEvent(word))
    }

    fun tryOpen(word: String) {
        val wordList = _wordsLiveData.value ?: emptyList()
        val searchedWorld = wordList.firstOrNull { it.name == word }
        searchedWorld?.let { openDetails(searchedWorld) }
    }

}