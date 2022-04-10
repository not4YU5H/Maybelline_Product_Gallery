package com.example.maybellineproductgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.maybellineproductgallery.adapter.ProductAdapter
import com.example.maybellineproductgallery.model.Post
import com.example.maybellineproductgallery.databinding.ActivityMainBinding
import com.example.maybellineproductgallery.utils.Constants.Companion.BASE_URL
import com.example.maybellineproductgallery.repository.ProductRepository
import com.example.maybellineproductgallery.room.ProductDatabase
import com.example.maybellineproductgallery.viewmodel.ProductViewModel
import com.example.maybellineproductgallery.viewmodel.ProductViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomDao = ProductDatabase.getProductDatabase(applicationContext).roomDao()
        val productRepository = ProductRepository(roomDao)
        val mainViewModel = ViewModelProvider(this , ProductViewModelFactory(productRepository)).get(
            ProductViewModel::class.java)
        val adapter = ProductAdapter()
        binding.recyclerviewUsers.adapter = adapter
        dataHandle(mainViewModel)

        mainViewModel.getProduct().observe(this) {
            adapter.submitList(it)
        }
    }

    private fun dataHandle(viewModel: ProductViewModel) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
            .create(ProductAPI::class.java)


        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Post>?> {

            override fun onResponse(
                call: Call<List<Post>?>,
                response: Response<List<Post>?>) {
                val responseBody =  response.body()!!
                viewModel.insertProduct(responseBody)
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                //To be implemented
            }
        })
    }
}