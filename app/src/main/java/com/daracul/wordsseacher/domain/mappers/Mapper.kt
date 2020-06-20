package com.daracul.wordsseacher.domain.mappers

interface Mapper<From, To> {

    fun map(from: From): To
}