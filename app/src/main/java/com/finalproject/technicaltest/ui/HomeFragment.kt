package com.finalproject.technicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalproject.technicaltest.data.Resource
import com.finalproject.technicaltest.data.response.Mahasiswa
import com.finalproject.technicaltest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mahasiswaAdapter: AdapterMahasiswa
    private val mahasiswaViewModel: MahasiswaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData from ViewModel for state changes
        mahasiswaViewModel.mahasiswaData.observe(viewLifecycleOwner) { resource ->
            handleResourceState(resource)
        }

        // Trigger data fetching when fragment is ready
        mahasiswaViewModel.getAllMahasiswa()
    }

    private fun handleResourceState(resource: Resource<List<Mahasiswa>>) {
        when (resource) {
            is Resource.Loading -> showLoading(true)
            is Resource.Success -> {
                showLoading(false)
                resource.data?.let { mahasiswaList ->
                    updateRecyclerView(mahasiswaList)
                } ?: showError("No data available")
            }
            is Resource.Error -> {
                showLoading(false)
                showError(resource.message ?: "An error occurred")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun updateRecyclerView(mahasiswaList: List<Mahasiswa>) {
        if (!::mahasiswaAdapter.isInitialized) {
            mahasiswaAdapter = AdapterMahasiswa(mahasiswaList)
            binding.recyclerView.adapter = mahasiswaAdapter
        } else {
            mahasiswaAdapter.updateData(mahasiswaList)
        }
    }
}
