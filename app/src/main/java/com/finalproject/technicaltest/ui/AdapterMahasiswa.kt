package com.finalproject.technicaltest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproject.technicaltest.data.response.Mahasiswa
import com.finalproject.technicaltest.databinding.ItemListMahasiswaBinding

class AdapterMahasiswa(private var mahasiswaList: List<Mahasiswa>) : RecyclerView.Adapter<AdapterMahasiswa.MahasiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val binding = ItemListMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MahasiswaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = mahasiswaList[position]
        holder.bind(mahasiswa)
    }

    override fun getItemCount(): Int = mahasiswaList.size

    inner class MahasiswaViewHolder(private val binding: ItemListMahasiswaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mahasiswa: Mahasiswa) {
            binding.tvName.text = mahasiswa.name
            binding.tvAddress.text = mahasiswa.address
            // Gunakan library Glide untuk memuat gambar
            Glide.with(binding.ivPhoto.context)
                .load(mahasiswa.photo)
                .into(binding.ivPhoto)
        }
    }

    // Fungsi untuk memperbarui data di Adapter
    fun updateData(newList: List<Mahasiswa>) {
        mahasiswaList = newList
        notifyDataSetChanged() // Notifikasi perubahan data agar RecyclerView memperbarui tampilannya
    }
}
