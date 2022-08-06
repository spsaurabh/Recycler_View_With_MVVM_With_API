package com.example.recycler_view_withmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycler_view_withmvvm.Movie
import com.example.recycler_view_withmvvm.apicall.RetrofitService
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel()
{
    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

//    fun getAllMovies(){
//        val response = repository.getAllMovies()
//        response.enqueue(object : Callback<List<Movie>>, retrofit2.Callback<List<Movie>> {
//            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                movieList.postValue(response.body())
//            }
//            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                    errorMessage.postValue(t.message)
//            }
//        })
//    }
    fun getAllMovies(){
     //   val response = repository.getAllMovies()
    val response = RetrofitService.getInstance().getAllMovies()
    response.enqueue(object : retrofit2.Callback<List<Movie>?> {
        override fun onResponse(call: Call<List<Movie>?>, response: Response<List<Movie>?>) {
            if (response.isSuccessful)
            {
                movieList.postValue(response.body())
                Log.i("saurabh","body"+response.body())
            }else
                Log.i("saurabh","null"+response.body())
        }

        override fun onFailure(call: Call<List<Movie>?>, t: Throwable) {

            Log.i("rajesh",t.localizedMessage)
            errorMessage.postValue(t.message)
        }
    })
//    response.enqueue(object : retrofit2.Callback<List<Movie>>{
//
//        override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//            movieList.postValue(response.body())
//            Log.i("Main",""+response)
//
//        }
//        override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//            errorMessage.postValue(t.message)
//            Log.i("Main","error"+t.localizedMessage)
//        }
//    })
    }
}
