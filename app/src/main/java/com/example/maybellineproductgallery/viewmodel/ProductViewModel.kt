package com.example.maybellineproductgallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maybellineproductgallery.repository.ProductRepository
import com.example.maybellineproductgallery.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {

    fun getProduct(): LiveData<List<Post>> {
        return productRepository.getProduct()
    }

    fun insertProduct(post: List<Post>){
        viewModelScope.launch(Dispatchers.IO) {
            for(i in post) {
                productRepository.insertProduct(i)
            }
        }
    }
}