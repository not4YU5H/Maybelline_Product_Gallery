package com.example.maybellineproductgallery.repository

import androidx.lifecycle.LiveData
import com.example.maybellineproductgallery.room.RoomDao
import com.example.maybellineproductgallery.model.Post

class ProductRepository(private val RoomDao:RoomDao) {

    suspend fun insertProduct (post: Post){
        RoomDao.insertProduct(post)

    }

    fun getProduct(): LiveData<List<Post>> {
        return RoomDao.getProduct()
    }
}