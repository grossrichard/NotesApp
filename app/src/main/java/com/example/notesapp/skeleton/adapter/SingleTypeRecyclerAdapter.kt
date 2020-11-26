package com.example.notesapp.skeleton.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ObservableList
import com.example.notesapp.skeleton.mvvm.BaseViewModel

class SingleTypeRecyclerAdapter<T>(
    items: ObservableList<T>, @LayoutRes private val layoutId: Int,
    viewModel: BaseViewModel?
) : BaseRecyclerViewAdapter<T>(items, viewModel) {

    override fun getLayoutId(itemType: Int): Int = layoutId
}
