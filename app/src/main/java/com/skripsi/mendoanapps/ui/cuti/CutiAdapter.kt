package com.skripsi.mendoanapps.ui.cuti

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.cuti.CutiItem
import com.skripsi.mendoanapps.databinding.ItemCutiBinding

class CutiAdapter(
    private val dataKu: (CutiItem) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemCutiBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemCutiBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemCutiBinding>, position: Int) {
        with(holder.binding) {
            tvNamaKaryawan.text = data[position].fullname
            tvTanggalCuti.text = data[position].tanggalCuti
            tvUserId.text = data[position].userId
            tvKeterangan.text = data[position].keterangan
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<CutiItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<CutiItem> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}