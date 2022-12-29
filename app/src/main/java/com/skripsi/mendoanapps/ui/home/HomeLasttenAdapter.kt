package com.skripsi.mendoanapps.ui.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.lastten.LastTenItem
import com.skripsi.mendoanapps.databinding.ItemLasttenBinding

class HomeLasttenAdapter(
    private val dataKu: (LastTenItem) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemLasttenBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemLasttenBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemLasttenBinding>, position: Int) {
        with(holder.binding) {
            tvNamaProject.text = data[position].name
            tvFullname.text = data[position].fullname
            tvType.text = data[position].type
            tvTanggal.text = data[position].tanggal
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<LastTenItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<LastTenItem> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }
}
