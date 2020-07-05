package com.example.android.dictionary.presentation.ui.dictionary.details

import com.example.android.dictionary.presentation.ui.dictionary.TranslationUi


data class DictionaryInfoUi(
    val id: Long,
    val text: String,
    val translation: TranslationUi,
    val images: List<ImageUi>
)

data class ImageUi(
    val url: String
)