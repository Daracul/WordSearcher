package com.daracul.wordsseacher.data.mappers

import com.daracul.wordsseacher.api.response.Meaning
import com.daracul.wordsseacher.api.response.WordJson
import com.daracul.wordsseacher.domain.mappers.Mapper
import com.daracul.wordsseacher.domain.model.Word

class WordsMapper : Mapper<WordJson, Word> {
    override fun map(from: WordJson): Word {
        return with(from) {
            Word(
                id = id,
                name = text ?: "",
                translations = getMeaning(meanings),
                transcription = getTranscription(meanings),
                imageUrl = getImageUrl(meanings)
            )
        }
    }

    private fun getImageUrl(meanings: List<Meaning?>): String = meanings.first()?.previewUrl ?: ""

    private fun getMeaning(meanings: List<Meaning?>): List<String> {
        val list = mutableListOf<String>()
        meanings.forEach {
            list.add(it?.translation?.text ?: "")
        }
        return list
    }

    private fun getTranscription(meanings: List<Meaning?>): String =
        meanings.first()?.transcription ?: ""
}