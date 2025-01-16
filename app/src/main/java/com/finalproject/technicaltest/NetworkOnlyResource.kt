package com.finalproject.technicaltest

import com.finalproject.technicaltest.data.Resource
import com.finalproject.technicaltest.data.remote.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
abstract class NetworkOnlyResource<ResultType> {

    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        try {
            when (val apiResponse = createCall().first()) { // Create call returns Flow<ApiResponse<ResultType>>
                is ApiResponse.Success -> {
                    emit(Resource.Success(apiResponse.data))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error("Data not found"))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

    // Fungsi abstrak untuk memanggil API, tanpa menyimpan hasilnya ke database
    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>
}

