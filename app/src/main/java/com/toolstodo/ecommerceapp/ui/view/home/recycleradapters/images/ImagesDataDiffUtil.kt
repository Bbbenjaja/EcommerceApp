package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.images

import androidx.recyclerview.widget.DiffUtil

class ImagesDataDiffUtil(
    private val oldList: List<String>,
    private val newList: List<String>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].contentEquals(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}