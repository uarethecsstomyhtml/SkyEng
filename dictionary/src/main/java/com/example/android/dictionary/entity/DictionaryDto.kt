package com.example.android.dictionary.entity


data class DictionaryDto(
    val id: Long,
    val text: String,
    val meanings: List<MeaningDto>
)

data class MeaningDto(
    val translation: TranslationDto
)

data class TranslationDto(
    val text: String
)



