package com.example.wordsseacher

import com.daracul.wordsseacher.domain.model.Word

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