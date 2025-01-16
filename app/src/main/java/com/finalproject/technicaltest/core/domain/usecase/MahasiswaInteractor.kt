package com.finalproject.technicaltest.core.domain.usecase

import com.finalproject.technicaltest.core.data.Resource
import com.finalproject.technicaltest.core.domain.repository.MahasiswaRepository
import com.finalproject.technicaltest.core.response.Mahasiswa
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MahasiswaInteractor @Inject constructor(
    private val bookingRepository: MahasiswaRepository
) : MahasiswaUseCase {
    override fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>> {
        return bookingRepository.getAllMahasiswa()
    }

}