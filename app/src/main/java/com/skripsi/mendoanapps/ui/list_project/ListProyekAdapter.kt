package com.skripsi.mendoanapps.ui.list_project

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.all_board.BoardsItem
import com.skripsi.mendoanapps.databinding.ItemListProyekBinding

class ListProyekAdapter(
    private val dataKu: (BoardsItem) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemListProyekBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemListProyekBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemListProyekBinding>, position: Int) {
        with(holder.binding) {
            tvNamaProyek.text = data[position].name
            tvAktifitas.text = data[position].lastactivity
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<BoardsItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<BoardsItem> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}