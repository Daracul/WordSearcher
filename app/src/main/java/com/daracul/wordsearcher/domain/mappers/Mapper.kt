package com.daracul.wordsearcher.domain.mappers

interface Mapper<From, To> {

    fun map(from: From): To
}