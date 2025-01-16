package com.finalproject.technicaltest.data

import com.finalproject.technicaltest.data.domain.usecase.MahasiswaInteractor
import com.finalproject.technicaltest.data.domain.usecase.MahasiswaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    // Tambahkan binding untuk MahasiswaUseCase dan MahasiswaInteractor
    @Binds
    @ViewModelScoped
    abstract fun provideMahasiswaUseCase(mahasiswaInteractor: MahasiswaInteractor): MahasiswaUseCase
}
