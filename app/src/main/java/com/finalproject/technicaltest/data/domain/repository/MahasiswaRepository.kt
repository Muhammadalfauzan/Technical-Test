package com.finalproject.technicaltest.data.domain.repository

import com.finalproject.technicaltest.data.Resource
import com.finalproject.technicaltest.data.remote.ApiResponse
import com.finalproject.technicaltest.data.response.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MahasiswaRepository {
    fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>>
}
