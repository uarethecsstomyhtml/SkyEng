package com.example.android.dictionary.data.repository

import com.example.android.dictionary.data.remote.DictionaryApi
import com.example.android.dictionary.domain.repository.DictionaryRepository
import com.example.android.dictionary.domain.usecase.GetDictionaryBySearchUseCase
import com.example.android.dictionary.domain.usecase.GetDictionaryInfoUseCase
import com.example.android.dictionary.entity.DictionaryDto
import com.example.android.dictionary.entity.DictionaryInfoDto
import retrofit2.Response

class DefaultDictionaryRepository(private val dictionaryApi: DictionaryApi) : DictionaryRepository {

    override suspend fun getDictionaryBySearch(params: GetDictionaryBySearchUseCase.Params): Response<List<DictionaryDto>> {
        return dictionaryApi.getDictionaryBySearch(params.word)
    }

    override suspend fun getDictionaryInfo(params: GetDictionaryInfoUseCase.Params): Response<List<DictionaryInfoDto>> {
        return dictionaryApi.getWordDetails(params.ids.toString())
    }

}