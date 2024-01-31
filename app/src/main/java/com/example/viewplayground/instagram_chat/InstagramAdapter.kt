package com.example.viewplayground.instagram_chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.viewplayground.databinding.ItemInstagramBinding

class InstagramAdapter(
    private val screenHeight: Int
) : RecyclerView.Adapter<MyViewHolder>() {

    private val positionChangeList = mutableListOf<PositionCallback>()

    fun notifyItems() {
        positionChangeList.forEach {
            it.onPosChanged()
        }
    }

    override fun getItemCount(): Int = 50

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemInstagramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MyViewHolder(binding, screenHeight)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        positionChangeList.remove(holder)
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        positionChangeList.add(holder)
    }
}

class MyViewHolder(
    private val binding: ItemInstagramBinding,
    private val screenHeight: Int
) : RecyclerView.ViewHolder(binding.root), PositionCallback {

    private val color1 = InstagramColors.color1
    private val color2 = InstagramColors.color2

    fun bind(a: Int) {
        binding.label.text = a.toString()
    }

    override fun onPosChanged() {
        val intArray = IntArray(2)
        binding.root.getLocationInWindow(intArray)
        val viewHeight = binding.root.height
        val y = intArray[1]
        val beginRatio = (y.toFloat()/screenHeight).coerceIn(0.0f..1.0f)
        val endRatio = ((y.toFloat()+viewHeight)/screenHeight).coerceIn(0.0f..1.0f)
        val startColor = lerpGradient(color1, color2, beginRatio)
        val endColor = lerpGradient(color1, color2, endRatio)
        binding.cardBG.setGradient(startColor, endColor)
    }
}

interface PositionCallback {
    fun onPosChanged()
}