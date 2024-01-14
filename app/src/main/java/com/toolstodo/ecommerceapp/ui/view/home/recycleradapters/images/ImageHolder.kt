package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.images

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerceapp.databinding.ImageItemBinding

class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ImageItemBinding.bind(itemView)

    fun render(image: String) {
        with(binding) {
            Picasso.get().load(image).into(imgProduct)
        }
    }

}