package com.example.viewplayground

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewplayground.databinding.ItemMainBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val list = mutableListOf<MainItem>()

    var listener: (Intent) -> Unit = {}

    fun setItems(data: List<MainItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }
}

class MainViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: MainItem,
        listener: (Intent) -> Unit
    ) {
        binding.label.text = item.label
        binding.root.setOnClickListener {
            listener.invoke(item.intent)
        }
    }
}

data class MainItem(
    val label: String,
    val intent: Intent
)
