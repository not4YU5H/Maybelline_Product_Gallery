package com.example.maybellineproductgallery.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.maybellineproductgallery.model.Post

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(post: Post)

    @Query("SELECT * FROM ProductTable")
    fun getProduct(): LiveData<List<Post>>
}