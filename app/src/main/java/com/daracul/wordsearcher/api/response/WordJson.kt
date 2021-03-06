package com.daracul.wordsearcher.api.response


data class WordJson(
    val id: Int,
    val text: String?,
    val meanings: List<Meaning>
)

data class Meaning(
    val id: Int,
    val partOfSpeechCode: String?,
    val translation: Translation?,
    val previewUrl: String?,
    val imageUrl: String?,
    val transcription: String?,
    val soundUrl: String
)

data class Translation(
    val text: String?,
    val note: String?
)


