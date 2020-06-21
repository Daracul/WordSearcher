package com.example.wordsseacher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daracul.wordsseacher.domain.usecase.GetWordsUseCase
import com.daracul.wordsseacher.presentation.main.MainViewModel
import getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTests {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var wordsUseCase: GetWordsUseCase

    @Before
    fun setUp() {
        wordsUseCase = FakeWordsUseCase()
        mainViewModel = MainViewModel(wordsUseCase)
    }

    @Test
    fun search_word_success_test() {
        val searchWord = "success"
        mainViewModel.searchWord(searchWord)
        val result = mainViewModel.wordsLiveData.getOrAwaitValue()
        Assert.assertEquals(searchWord, result.first().name)
    }

    @Test
    fun search_word_error_test() {
        mainViewModel.searchWord("error")
        val result = mainViewModel.showErrorLiveData.getOrAwaitValue()
        Assert.assertEquals(true, result)
    }

    @Test
    fun open_details_test() {
        val word = FakeWordsFactory.createFakeWord()
        mainViewModel.openDetails(word)
        Assert.assertEquals(
            word,
            mainViewModel.navigateDetailsLiveData.getOrAwaitValue().getContentIfNotHandled()
        )
    }

    @Test
    fun try_open_test() {
        mainViewModel.searchWord("success")
        mainViewModel.tryOpen("success")
        val word = FakeWordsFactory.createFakeWord()
        Assert.assertEquals(
            word,
            mainViewModel.navigateDetailsLiveData.getOrAwaitValue().getContentIfNotHandled()
        )
    }

}