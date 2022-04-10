package com.example.maybellineproductgallery.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maybellineproductgallery.R
import com.example.maybellineproductgallery.model.Post

class ProductAdapter : ListAdapter<Post, ProductAdapter.ProductViewHolder>(DiffUtil()) {

    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id ==  newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.name)
        val brand: TextView = view.findViewById(R.id.brand)
        val desc: TextView = view.findViewById(R.id.description)
        val price: TextView = view.findViewById(R.id.price)
        val imageLink: ImageView = view.findViewById(R.id.image_link)

        fun bind(post: Post) {
            name.text = post.name
            brand.text = post.brand
            desc.text = post.description
            price.text = post.price
            Glide.with(name.context).load(post.image_link).into(imageLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_card ,parent , false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}