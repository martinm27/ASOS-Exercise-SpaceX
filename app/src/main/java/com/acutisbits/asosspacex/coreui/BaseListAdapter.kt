package com.acutisbits.asosspacex.coreui

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.acutisbits.asosspacex.coreui.utils.DiffUtilCallback
import com.acutisbits.asosspacex.coreui.utils.ListItem

abstract class BaseListAdapter<Item : ListItem, ViewHolder : BindingViewHolder<Item, out ViewBinding>> : ListAdapter<Item, ViewHolder>(
    DiffUtilCallback()
) {

    override fun onViewRecycled(holder: ViewHolder) {
        holder.clear()
        super.onViewRecycled(holder)
    }
}

abstract class BindingViewHolder<Item, Binding : ViewBinding>(protected val binding: Binding) : RecyclerView.ViewHolder(binding.root) {

    init {
        initializeView()
    }

    fun render(item: Item) = binding.render(item)

    fun clear() = binding.clear()

    private fun initializeView() = binding.initializeView()

    open fun Binding.render(item: Item) {
        // Template
    }

    open fun Binding.initializeView() {
        // Template
    }

    open fun Binding.clear() {
        // Template
    }
}
