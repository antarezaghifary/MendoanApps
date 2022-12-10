package com.skripsi.mendoanapps.adapter


import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.skripsi.mendoanapps.data.model.menu_app.MenuApps
import com.skripsi.mendoanapps.databinding.ItemMenuHomeBinding

class HomeAdapter(
    private val heroes: (MenuApps) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemMenuHomeBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemMenuHomeBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ItemMenuHomeBinding>, position: Int) {
        with(holder.binding) {
            tvMenu.text = data[position].menu

            holder.itemView.setOnClickListener {
                heroes(data[position])
            }
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<MenuApps>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<MenuApps> by lazy {
        ArrayList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}