package com.example.maybellineproductgallery.api

import com.example.maybellineproductgallery.model.Post
import retrofit2.http.GET
import retrofit2.Call

interface ProductAPI {
    @GET("api/v1/products.json?brand=maybelline")
    fun getData(): Call<List<Post>>
}