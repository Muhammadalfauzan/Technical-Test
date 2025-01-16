package com.finalproject.technicaltest.core.di

import com.finalproject.technicaltest.core.MahasiswaRepositoryImpl
import com.finalproject.technicaltest.core.domain.repository.MahasiswaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMahasiswaRepository(lapanganRepositoryImpl: MahasiswaRepositoryImpl): MahasiswaRepository
}
