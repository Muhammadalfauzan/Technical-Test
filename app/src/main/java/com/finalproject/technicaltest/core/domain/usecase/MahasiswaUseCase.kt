package com.finalproject.technicaltest.core.domain.usecase

import com.finalproject.technicaltest.core.data.Resource
import com.finalproject.technicaltest.core.response.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MahasiswaUseCase {
    fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>>
}