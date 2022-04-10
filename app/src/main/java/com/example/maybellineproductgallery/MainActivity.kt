package com.example.maybellineproductgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.maybellineproductgallery.adapter.ProductAdapter
import com.example.maybellineproductgallery.model.Post
import com.example.maybellineproductgallery.api.ProductAPI
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
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val productCrud = ProductDatabase.getProductDatabase(applicationContext).ProductCrud()
        val productRepository = ProductRepository(productCrud)

        val mainViewModel = ViewModelProvider(this , ProductViewModelFactory(productRepository)).get(
            ProductViewModel::class.java)


        val adapter = ProductAdapter()
        binding.recyclerviewUsers.adapter = adapter
        getAndSetMyData(mainViewModel)

        mainViewModel.getProduct().observe(this) {
            adapter.submitList(it)
        }
    }

    private fun getAndSetMyData(viewModel: ProductViewModel) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ProductAPI::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(
                call: Call<List<Post>?>,
                response: Response<List<Post>?>
            ) {
                val responseBody =  response.body()!!
                viewModel.insertProduct(responseBody)
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {

            }
        })
    }
}