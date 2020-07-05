package com.example.android.dictionary.presentation.ui.dictionary

import androidx.lifecycle.*
import com.example.android.dictionary.domain.usecase.GetDictionaryBySearchUseCase
import com.example.android.dictionary.entity.DictionaryDto
import com.example.android.dictionary.entity.MeaningDto
import com.example.android.dictionary.entity.TranslationDto
import com.example.android.network_components.Failure
import com.example.android.ui_components.ResultState
import com.example.android.ui_components.setError
import com.example.android.ui_components.setLoading
import com.example.android.ui_components.setSuccess
import timber.log.Timber

class DictionaryViewModel(private val getDictionaryBySearchUseCase: GetDictionaryBySearchUseCase) : ViewModel() {

    private val _dictionary = MutableLiveData<ResultState<List<DictionaryUi>>>()
    val dictionary: LiveData<ResultState<List<DictionaryUi>>>
        get() = _dictionary

    val loading = Transformations.map(dictionary) {
        it is ResultState.Loading
    }

    private val _dictionaryClick = MutableLiveData<Long>()
    val dictionaryClick: LiveData<Long>
        get() = _dictionaryClick

    fun loadDictionaryBySearch(word: String) {
        Timber.i("loadDictionaryBySearch")
        _dictionary.setLoading()
        val params = GetDictionaryBySearchUseCase.Params(word)
        getDictionaryBySearchUseCase(viewModelScope, params) { it.either(::handleFailure, ::handleSuccess)}
    }

    fun onItemClick(id: Long) {
        Timber.i("onItemClick, id = $id")
        _dictionaryClick.value = id
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _dictionary.setError(f.exception)
    }

    private fun handleSuccess(dictionary: List<DictionaryDto>) {
        Timber.i("handleSuccess, dictionary = $dictionary")
        _dictionary.setSuccess(transformToPresentation(dictionary))
    }

    private fun transformToPresentation(dictionary: List<DictionaryDto>): List<DictionaryUi> {
        Timber.i("transformToPresentation, dictionary = $dictionary")
        return dictionary.map { DictionaryUi(it.id, it.text, transformMeaningsToPresentation(it.meanings)) }
    }

    private fun transformMeaningsToPresentation(meanings: List<MeaningDto>): List<MeaningUi> {
        Timber.i("transformMeaningsToPresentation, meanings = $meanings")
        return meanings.map { MeaningUi(transformTranslationToPresentation(it.translation)) }
    }

    private fun transformTranslationToPresentation(translationDto: TranslationDto): TranslationUi {
        Timber.i("transformTranslationToPresentation, translationDto = $translationDto")
        return TranslationUi(translationDto.text)
    }
}