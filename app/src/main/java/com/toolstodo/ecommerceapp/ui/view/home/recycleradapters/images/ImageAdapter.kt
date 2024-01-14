package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerceapp.R

class ImageAdapter(private var imagesList: List<String> = emptyList()) :
    RecyclerView.Adapter<ImageHolder>() {

    fun updateList(newList: List<String>){
        val imageDataDiffUtil = ImagesDataDiffUtil(imagesList, newList)
        val result = DiffUtil.calculateDiff(imageDataDiffUtil)

        imagesList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.render(imagesList[position])
    }
}