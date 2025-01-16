package com.finalproject.technicaltest.data

import com.finalproject.technicaltest.NetworkOnlyResource
import com.finalproject.technicaltest.data.domain.repository.MahasiswaRepository
import com.finalproject.technicaltest.data.remote.ApiResponse
import com.finalproject.technicaltest.data.remote.ApiService
import com.finalproject.technicaltest.data.response.Mahasiswa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MahasiswaRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MahasiswaRepository {

    // Memanggil API dan mengembalikan Flow<Resource<List<Mahasiswa>>>
    override fun getAllMahasiswa(): Flow<Resource<List<Mahasiswa>>> {
        return object : NetworkOnlyResource<List<Mahasiswa>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<Mahasiswa>>> {
                return try {
                    // Memanggil API untuk mendapatkan daftar mahasiswa
                    val response = apiService.getAllMahasiswa()
                    if (response.isNotEmpty()) {
                        // Jika data ditemukan, kirimkan respons sukses
                        flowOf(ApiResponse.Success(response))
                    } else {
                        // Jika tidak ada data, kirimkan respons kosong
                        flowOf(ApiResponse.Empty)
                    }
                } catch (e: Exception) {
                    // Tangani error dan kirimkan pesan error
                    flowOf(ApiResponse.Error("Error fetching data: ${e.localizedMessage}"))
                }
            }
        }.asFlow() // Gunakan asFlow() untuk mengubah menjadi Flow<Resource>
    }
}
