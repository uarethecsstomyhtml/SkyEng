package com.example.android.dictionary.presentation.ui.dictionary


data class DictionaryUi(
    val id: Long,
    val text: String,
    val meanings: List<MeaningUi>
)

data class MeaningUi(
    val translation: TranslationUi
)

data class TranslationUi(
    val text: String
)