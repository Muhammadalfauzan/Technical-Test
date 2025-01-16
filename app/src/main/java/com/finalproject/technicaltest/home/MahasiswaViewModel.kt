package com.finalproject.technicaltest.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finalproject.technicaltest.core.data.Resource
import com.finalproject.technicaltest.core.domain.usecase.MahasiswaUseCase
import com.finalproject.technicaltest.core.response.Mahasiswa
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MahasiswaViewModel @Inject constructor(
    private val homeUseCase: MahasiswaUseCase
) : ViewModel() {

    private val _mahasiswaData = MutableLiveData<Resource<List<Mahasiswa>>>()
    val mahasiswaData: LiveData<Resource<List<Mahasiswa>>> get() = _mahasiswaData

    fun getAllMahasiswa() {
        _mahasiswaData.value = Resource.Loading()
        viewModelScope.launch {
            homeUseCase.getAllMahasiswa().collect { resource ->
                Log.d("MahasiswaViewModel", "Resource received: $resource")
                _mahasiswaData.value = resource
            }
        }
    }
}





