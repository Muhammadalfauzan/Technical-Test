package com.finalproject.technicaltest.core.domain.repository

import com.finalproject.technicaltest.core.data.Resource
import com.finalproject.technicaltest.core.response.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MahasiswaRepository {
    fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>>
}
