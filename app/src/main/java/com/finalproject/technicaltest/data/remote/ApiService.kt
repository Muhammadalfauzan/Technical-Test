package com.finalproject.technicaltest.data.remote

import com.finalproject.technicaltest.data.response.Mahasiswa
import retrofit2.http.GET

interface ApiService {

    @GET("api/mahasiswa")
    suspend fun getAllMahasiswa(): List<Mahasiswa>

}
