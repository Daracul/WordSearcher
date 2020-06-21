package com.daracul.wordsearcher.data.mappers

import com.daracul.wordsearcher.api.response.Meaning
import com.daracul.wordsearcher.api.response.WordJson
import com.daracul.wordsearcher.domain.mappers.Mapper
import com.daracul.wordsearcher.domain.model.Word

private const val HTTPS = "https:"

class WordsMapper : Mapper<WordJson, Word> {
    override fun map(from: WordJson): Word {
        return with(from) {
            Word(
                id = id,
                name = text ?: "",
                translation = getFirstTranslation(meanings),
                otherTranslations = getMeaning(meanings),
                transcription = getTranscription(meanings),
                imageUrl = getImageUrl(meanings)
            )
        }
    }

    private fun getFirstTranslation(meanings: List<Meaning?>) =
        meanings.first()?.translation?.text ?: ""

    private fun getImageUrl(meanings: List<Meaning?>): String {
        var url = meanings.first()?.imageUrl ?: ""
        if (url.isNotEmpty()) url = HTTPS + url
        return url
    }

    private fun getMeaning(meanings: List<Meaning?>): List<String> {
        val list = mutableListOf<String>()
        val firstTranslation = getFirstTranslation(meanings)
        meanings.forEach {
            val translation = it?.translation?.text ?: ""
            if (translation != firstTranslation) list.add(translation)
        }
        return list
    }

    private fun getTranscription(meanings: List<Meaning?>): String =
        meanings.first()?.transcription ?: ""
}