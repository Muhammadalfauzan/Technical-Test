package com.finalproject.technicaltest.core.data

import com.finalproject.technicaltest.core.remote.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class NetworkOnlyResource<ResultType> {

    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("No data found"))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }.catch { e ->
        emit(Resource.Error("An unexpected error occurred: ${e.localizedMessage}"))
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>
}


