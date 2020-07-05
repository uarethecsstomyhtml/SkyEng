package com.example.android.dictionary.domain.usecase

import com.example.android.dictionary.domain.repository.DictionaryRepository
import com.example.android.dictionary.entity.DictionaryDto
import com.example.android.network_components.BaseUseCase
import com.example.android.network_components.Either
import com.example.android.network_components.Failure

class GetDictionaryBySearchUseCase(
    private val dictionaryRepository: DictionaryRepository
): BaseUseCase<List<DictionaryDto>, GetDictionaryBySearchUseCase.Params>() {


    override suspend fun run(params: Params): Either<Failure, List<DictionaryDto>> {
        return try {
            val dictionary = dictionaryRepository.getDictionaryBySearch(params)
            if (dictionary.isSuccessful) Either.Success(dictionary.body()!!) else Either.Failure(Failure.ServerError)
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Failure(Failure.FeatureFailure(e))
        }
    }

    data class Params(
        val word: String
    )
}