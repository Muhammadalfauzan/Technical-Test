package com.finalproject.technicaltest.core.di

import com.finalproject.technicaltest.core.domain.usecase.MahasiswaInteractor
import com.finalproject.technicaltest.core.domain.usecase.MahasiswaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMahasiswaUseCase(mahasiswaInteractor: MahasiswaInteractor): MahasiswaUseCase
}
