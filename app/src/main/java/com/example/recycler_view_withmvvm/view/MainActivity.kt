package com.example.recycler_view_withmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_view_withmvvm.adapter.MainAdapter
import com.example.recycler_view_withmvvm.R
import com.example.recycler_view_withmvvm.apicall.RetrofitService
import com.example.recycler_view_withmvvm.databinding.ActivityMainBinding
import com.example.recycler_view_withmvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
       // MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.recyclerView.adapter = adapter
        viewModel.getAllMovies()
        viewModel.movieList.observe(this, Observer {
            Log.d(TAG,"OnCreate : $it")

            adapter.setMovieList(it)
            adapter.notifyDataSetChanged()
        })
            viewModel.errorMessage.observe(this, Observer {
            })
    }
}