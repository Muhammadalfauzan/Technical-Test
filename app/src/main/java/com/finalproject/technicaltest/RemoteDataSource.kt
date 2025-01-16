package com.finalproject.technicaltest

import com.finalproject.technicaltest.data.remote.ApiResponse
import com.finalproject.technicaltest.data.remote.ApiService
import com.finalproject.technicaltest.data.response.Mahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getAllMahasiswa(): Flow<ApiResponse<List<Mahasiswa>>> {
        return flow {
            try {
                val response = apiService.getAllMahasiswa()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }
}