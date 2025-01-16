package com.finalproject.technicaltest.data.domain.usecase

import com.finalproject.technicaltest.data.Resource
import com.finalproject.technicaltest.data.domain.repository.MahasiswaRepository
import com.finalproject.technicaltest.data.remote.ApiResponse
import com.finalproject.technicaltest.data.response.Mahasiswa
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MahasiswaInteractor @Inject constructor(
    private val bookingRepository: MahasiswaRepository
) : MahasiswaUseCase {
    override fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>> {
        return bookingRepository.getAllMahasiswa()
    }

}