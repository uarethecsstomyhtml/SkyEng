package com.example.android.dictionary.presentation.ui.dictionary.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.dictionary.domain.usecase.GetDictionaryBySearchUseCase
import com.example.android.dictionary.domain.usecase.GetDictionaryInfoUseCase
import com.example.android.dictionary.entity.*
import com.example.android.dictionary.presentation.ui.dictionary.MeaningUi
import com.example.android.dictionary.presentation.ui.dictionary.TranslationUi
import com.example.android.network_components.Failure
import com.example.android.ui_components.ResultState
import com.example.android.ui_components.setError
import com.example.android.ui_components.setLoading
import com.example.android.ui_components.setSuccess
import timber.log.Timber

class DictionaryDetailsViewModel(private val getDictionaryInfoUseCase: GetDictionaryInfoUseCase) : ViewModel() {

    private val _dictionaryInfo = MutableLiveData<ResultState<List<DictionaryInfoUi>>>()
    val dictionaryInfo: LiveData<ResultState<List<DictionaryInfoUi>>>
        get() = _dictionaryInfo

    fun loadDictionaryInfo(id: Long) {
        Timber.i("loadDictionaryInfo, id = $id")
        _dictionaryInfo.setLoading()
        val params = GetDictionaryInfoUseCase.Params(id)
        getDictionaryInfoUseCase(viewModelScope, params) { it.either(::handleFailure, ::handleSuccess)}
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _dictionaryInfo.setError(f.exception)
    }

    private fun handleSuccess(dictionaryInfo: List<DictionaryInfoDto>) {
        Timber.i("handleSuccess, dictionaryInfo = $dictionaryInfo")
        _dictionaryInfo.setSuccess(transformToPresentation(dictionaryInfo))
    }

    private fun transformToPresentation(dictionaryInfo: List<DictionaryInfoDto>): List<DictionaryInfoUi> {
        Timber.i("transformToPresentation, dictionaryInfo = $dictionaryInfo")
        return dictionaryInfo.map {
            DictionaryInfoUi(
                it.id,
                it.text,
                transformTranslationToPresentation(it.translation),
                transformImagesToPresentation(it.images)
            )}
    }

    private fun transformTranslationToPresentation(translationDto: TranslationDto): TranslationUi {
        Timber.i("transformTranslationToPresentation, translationDto = $translationDto")
        return TranslationUi(translationDto.text)
    }

    private fun transformImagesToPresentation(images: List<ImageDto>): List<ImageUi> {
        Timber.i("transformImagesToPresentation, images = $images")
        return images.map { ImageUi(it.url) }
    }


}