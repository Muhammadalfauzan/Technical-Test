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
        emit(Resource.Loading()) // Emit loading state

        // Proses API call dan tangani hasilnya
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data)) // Jika sukses, emit data
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("No data found")) // Jika kosong, emit pesan kesalahan
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage)) // Emit pesan kesalahan dari API
            }
        }
    }.catch { e ->
        emit(Resource.Error("An unexpected error occurred: ${e.localizedMessage}")) // Tangkap kesalahan lainnya
    }.flowOn(Dispatchers.IO)

    // Fungsi abstrak untuk memanggil API
    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>
}


