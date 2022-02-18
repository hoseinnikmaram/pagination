package com.nikmaram.list.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nikmaram.list.R
import com.nikmaram.list.databinding.ActivityMainBinding
import com.nikmaram.list.util.PAGE_SIZE
import com.nikmaram.list.util.RecyclerViewAdapter
import com.nikmaram.list.util.SCROLL_DELAY
import kotlinx.coroutines.flow.collectLatest
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recyclerViewAdapter = RecyclerViewAdapter{position ->
            Toast.makeText(this,position.toString(),Toast.LENGTH_SHORT).show()
        }
        binding.recycler.adapter = recyclerViewAdapter
        viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        getData()
        automaticScroll()
    }

    private fun automaticScroll() {
        val update = Runnable {
            val itemNumber = recyclerViewAdapter.itemCount
            binding.recycler.smoothScrollToPosition(itemNumber + PAGE_SIZE)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update);
            }

        }, SCROLL_DELAY, SCROLL_DELAY)
    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest { pagingData ->
                recyclerViewAdapter.submitData(pagingData)
            }
        }
    }
}