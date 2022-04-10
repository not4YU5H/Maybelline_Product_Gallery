package com.example.maybellineproductgallery.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.maybellineproductgallery.model.Post

@Database(entities = [Post::class] , version = 1 )
abstract class ProductDatabase: RoomDatabase() {
    abstract fun ProductCrud(): RoomDao

    companion object{
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getProductDatabase(context: Context): ProductDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, ProductDatabase::class.java, "ProductDB")
                        .build()
                }
                return INSTANCE!!
            }
        }

    }
}