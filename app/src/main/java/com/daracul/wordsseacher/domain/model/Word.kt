package com.daracul.wordsseacher.domain.model

data class Word(
    val id: Int,
    val name: String,
    val translations: List<String>,
    val transcription: String,
    val imageUrl: String
)