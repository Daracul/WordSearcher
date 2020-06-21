package com.example.wordsearcher

import com.daracul.wordsearcher.domain.model.Word

object FakeWordsFactory {
    fun createFakeWord(): Word = Word(
        id = 1,
        name = "success",
        translation = "",
        otherTranslations = emptyList(),
        transcription = "",
        imageUrl = ""
    )
}