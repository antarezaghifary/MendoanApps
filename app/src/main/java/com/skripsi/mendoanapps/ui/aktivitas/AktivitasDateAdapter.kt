package com.skripsi.mendoanapps.ui.aktivitas

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.aktivitas_date.ProjectItem
import com.skripsi.mendoanapps.databinding.ItemAktivitasDateBinding


class AktivitasDateAdapter(
    private val dataKu: (ProjectItem) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemAktivitasDateBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemAktivitasDateBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemAktivitasDateBinding>, position: Int) {
        holder.binding.apply {
            tvNamaProject.text = data[position].name
            tvFullname.text = data[position].fullname
            tvText.text = data[position].text
            tvType.text = data[position].type
            tvTanggal.text = data[position].tanggal
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<ProjectItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<ProjectItem> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}