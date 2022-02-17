package com.nikmaram.list.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.nikmaram.list.R
import com.nikmaram.list.databinding.ActivityMainBinding
import com.nikmaram.list.util.PAGE_SIZE
import com.nikmaram.list.util.RecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val handler = Handler(Looper.getMainLooper())
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recyclerViewAdapter = RecyclerViewAdapter{
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }
        binding.recycler.adapter = recyclerViewAdapter
        viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
         val  update =  Runnable {
             getData()
             binding.recycler.smoothScrollToPosition(i* PAGE_SIZE)
             i++
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update);
            }

        }, 0, 5000)
    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest { pagingData ->
                recyclerViewAdapter.submitData(pagingData)
            }
        }
    }
}