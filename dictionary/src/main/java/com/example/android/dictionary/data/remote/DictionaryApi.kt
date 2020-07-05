package com.example.android.dictionary.data.remote

import com.example.android.dictionary.domain.usecase.GetDictionaryInfoUseCase
import com.example.android.dictionary.entity.DictionaryDto
import com.example.android.dictionary.entity.DictionaryInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming

private const val DICTIONARY_BASE_URL = "/api/public/v1"

interface DictionaryApi {

    @GET("$DICTIONARY_BASE_URL/words/search")
    suspend fun getDictionaryBySearch(@Query("search") word: String): Response<List<DictionaryDto>>

    @Streaming
    @GET("$DICTIONARY_BASE_URL/meanings")
    suspend fun getWordDetails(@Query("ids") ids: String): Response<List<DictionaryInfoDto>>
}