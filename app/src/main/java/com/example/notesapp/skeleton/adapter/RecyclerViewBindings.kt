package com.example.notesapp.skeleton.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.skeleton.mvvm.BaseViewModel

/**
 * Created by Richard Gross on 2020-01-15
 */

@BindingAdapter(
    "viewModel",
    "items",
    "itemLayoutId",
    "orientation",
    "lifecycleOwner",
    "divider",
    requireAll = false
)
@Suppress("unchecked_cast")
fun <T> bindViewModel(
    view: RecyclerView,
    vm: BaseViewModel?,
    items: ObservableList<T>?,
    itemLayoutId: Int?,
    orientation: Int?,
    lifecycleOwner: LifecycleOwner?,
    dividerItemDecoration: RecyclerView.ItemDecoration?
) {
    view.layoutManager = view.layoutManager
        ?: LinearLayoutManager(
            view.context, orientation
                ?: RecyclerView.VERTICAL, false
        )

    dividerItemDecoration?.let { view.addItemDecoration(it) }

    val listItems = items ?: ObservableArrayList()
    view.adapter?.let { (it as BaseRecyclerViewAdapter<T>).items = listItems }
        ?: run {
            view.adapter = itemLayoutId?.let { SingleTypeRecyclerAdapter(listItems, it, vm) }
                ?: MultiTypeRecyclerAdapter(listItems as ObservableArrayList<RecyclerItem>, vm)
        }

    (view.adapter as BaseRecyclerViewAdapter<T>).lifecycleOwner = lifecycleOwner
}