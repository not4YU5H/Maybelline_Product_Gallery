package com.example.maybellineproductgallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductTable")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String,
    val description: String,
    val image_link: String,
    val name: String,
    val price: String
)
