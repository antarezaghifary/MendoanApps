package com.skripsi.mendoanapps.ui.idle

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.idle.ResourceItem
import com.skripsi.mendoanapps.databinding.ItemIdleBinding

class KaryawanIdleAdapter(
    private val dataKu: (ResourceItem) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemIdleBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemIdleBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemIdleBinding>, position: Int) {
        with(holder.binding) {
            tvNamaKaryawan.text = data[position].fullname
            tvKeterangan.text = if(data[position].keterangan == null) "-" else data[position].keterangan.toString()
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<ResourceItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<ResourceItem> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}