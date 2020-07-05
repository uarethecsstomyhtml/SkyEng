package com.example.android.dictionary.domain.repository

import com.example.android.dictionary.domain.usecase.GetDictionaryBySearchUseCase
import com.example.android.dictionary.domain.usecase.GetDictionaryInfoUseCase
import com.example.android.dictionary.entity.DictionaryDto
import com.example.android.dictionary.entity.DictionaryInfoDto
import retrofit2.Response

interface DictionaryRepository {

    suspend fun getDictionaryBySearch(params: GetDictionaryBySearchUseCase.Params): Response<List<DictionaryDto>>

    suspend fun getDictionaryInfo(params: GetDictionaryInfoUseCase.Params): Response<List<DictionaryInfoDto>>
}