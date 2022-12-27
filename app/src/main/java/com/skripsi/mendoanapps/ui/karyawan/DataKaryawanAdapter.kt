package com.skripsi.mendoanapps.ui.karyawan

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.karyawan.GetKaryawanResponseItem
import com.skripsi.mendoanapps.databinding.ItemDataKaryawanBinding

class DataKaryawanAdapter(
    private val dataKu: (GetKaryawanResponseItem) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemDataKaryawanBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemDataKaryawanBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemDataKaryawanBinding>, position: Int) {
        with(holder.binding) {
            tvNamaKaryawan.text = data[position].fullname
            tvNIK.text = data[position].nik
            val lamaBekerja =
                "${data[position].year.toString()} tahun ${data[position].month.toString()} bulan ${data[position].day.toString()} hari"
            tvLamaBekerja.text = lamaBekerja
            tvPosisi.text = data[position].posisi
            tvJurusan.text = data[position].jurusan
            tvPendidikan.text = data[position].pendidikan
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<GetKaryawanResponseItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<GetKaryawanResponseItem> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}