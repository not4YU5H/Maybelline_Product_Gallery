package com.example.maybellineproductgallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductTable")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val brand: String,
    val image_link: String,
    val description: String,
    val price: String
)
