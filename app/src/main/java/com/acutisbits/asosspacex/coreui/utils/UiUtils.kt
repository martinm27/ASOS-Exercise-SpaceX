package com.acutisbits.asosspacex.coreui.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

open class DiffUtilCallback<T : ListItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}

open class ListItem(open val id: Any? = null)
