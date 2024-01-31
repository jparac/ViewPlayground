package com.example.viewplayground.instagram_chat

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewplayground.databinding.ActivityInstagramBinding


class InstagramActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityInstagramBinding

    private lateinit var adapter: InstagramAdapter

    private lateinit var ll: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityInstagramBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getScreenSize()
        setupUI()
        binding.gradient.setGradient(InstagramColors.color1, InstagramColors.color2)
    }

    private fun setupUI() {
        ll = LinearLayoutManager(this)
        binding.recycler.layoutManager = ll
        binding.recycler.adapter = adapter
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                adapter.notifyItems()
            }
        })
    }

    private fun getScreenSize() {
        val windowManager = application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.currentWindowMetrics
        val bounds = display.bounds
        Log.d(TAG, "getScreenSize: $bounds")
        adapter = InstagramAdapter(bounds.height())
    }
}

