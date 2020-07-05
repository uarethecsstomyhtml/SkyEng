package com.example.android.dictionary.entity

data class DictionaryInfoDto(
    val id: Long,
    val text: String,
    val translation: TranslationDto,
    val images: List<ImageDto>
)

data class ImageDto(
    val url: String
)





