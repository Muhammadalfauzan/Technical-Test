package com.finalproject.technicaltest.core

import com.finalproject.technicaltest.core.data.NetworkOnlyResource
import com.finalproject.technicaltest.core.data.Resource
import com.finalproject.technicaltest.core.data.remote.RemoteDataSource
import com.finalproject.technicaltest.core.domain.repository.MahasiswaRepository
import com.finalproject.technicaltest.core.remote.ApiResponse
import com.finalproject.technicaltest.core.response.Mahasiswa
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MahasiswaRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MahasiswaRepository {

    override fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>> {
        return object : NetworkOnlyResource<List<Mahasiswa>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<Mahasiswa>>> {
                return remoteDataSource.getAllMahasiswa()
            }
        }.asFlow()
    }
}

