package com.finalproject.technicaltest.core.data.remote

import android.util.Log
import com.finalproject.technicaltest.core.remote.ApiResponse
import com.finalproject.technicaltest.core.response.Mahasiswa
import com.finalproject.technicaltest.core.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getAllMahasiswa(): Flow<ApiResponse<List<Mahasiswa>>> = flow {
        val response = apiService.getAllMahasiswa()
        if (response.isNotEmpty()) {
            emit(ApiResponse.Success(response))
        } else {
            emit(ApiResponse.Empty)
        }
    }.catch { e ->
        emit(ApiResponse.Error("Error fetching data: ${e.localizedMessage}"))
    }.flowOn(Dispatchers.IO)
}

