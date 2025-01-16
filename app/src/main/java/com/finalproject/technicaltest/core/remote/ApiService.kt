package com.finalproject.technicaltest.core.remote

import com.finalproject.technicaltest.core.response.Mahasiswa
import retrofit2.http.GET

interface ApiService {

    @GET("api/mahasiswa")
    suspend fun getAllMahasiswa(): List<Mahasiswa>

}
