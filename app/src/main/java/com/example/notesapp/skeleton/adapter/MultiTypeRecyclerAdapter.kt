package com.example.notesapp.skeleton.adapter

import androidx.databinding.ObservableList
import com.example.notesapp.skeleton.mvvm.BaseViewModel

class MultiTypeRecyclerAdapter<T : RecyclerItem>(
    items: ObservableList<T>,
    viewModel: BaseViewModel?
) : BaseRecyclerViewAdapter<T>(items, viewModel) {

    override fun getLayoutId(itemType: Int): Int = itemType

    override fun getItemViewType(position: Int): Int = getItem(position).layoutId

}
