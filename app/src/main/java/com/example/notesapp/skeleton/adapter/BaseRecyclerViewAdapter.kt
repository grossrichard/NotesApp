package com.example.notesapp.skeleton.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.BR
import com.example.notesapp.skeleton.mvvm.BaseViewModel

/**
 * Created by Richard Gross on 2020-01-15
 */
abstract class BaseRecyclerViewAdapter<T>(
    items: ObservableList<T>,
    var viewModel: BaseViewModel?
) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.BaseMvvmRecyclerViewHolder<ViewDataBinding>>() {

    var items: ObservableList<T> = items
        set(value) {
            field = value
            initOnListChangedListener()
            notifyDataSetChanged()
        }

    var lifecycleOwner: LifecycleOwner? = null

    private var onListChangedCallback: ObservableList.OnListChangedCallback<ObservableList<T>?>? =
        null

    @LayoutRes
    abstract fun getLayoutId(itemType: Int): Int

    init {
        initOnListChangedListener()
    }

    private fun initOnListChangedListener() {
        onListChangedCallback =
            object : ObservableList.OnListChangedCallback<ObservableList<T>?>() {
                override fun onChanged(sender: ObservableList<T>?) {
                    notifyDataSetChanged()
                }

                override fun onItemRangeChanged(
                    sender: ObservableList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    notifyItemRangeChanged(positionStart, itemCount)
                }

                override fun onItemRangeInserted(
                    sender: ObservableList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    notifyItemRangeInserted(positionStart, itemCount)
                }

                override fun onItemRangeMoved(
                    sender: ObservableList<T>?,
                    fromPosition: Int,
                    toPosition: Int,
                    itemCount: Int
                ) {
                    notifyDataSetChanged()
                }

                override fun onItemRangeRemoved(
                    sender: ObservableList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    notifyItemRangeRemoved(positionStart, itemCount)
                }
            }
        items.addOnListChangedCallback(onListChangedCallback)
    }

    private fun getViewHolderBinding(
        parent: ViewGroup?,
        @LayoutRes itemLayoutId: Int
    ): ViewDataBinding? {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent!!.context),
            itemLayoutId,
            parent,
            false
        )
    }

    override fun onBindViewHolder(
        holder: BaseMvvmRecyclerViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.bind(items[position], holder.binder!!)
        holder.binder?.executePendingBindings()
    }

    fun getItem(position: Int): T = items[position]

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseMvvmRecyclerViewHolder<ViewDataBinding> = BaseMvvmRecyclerViewHolder(
        LayoutInflater.from(parent.context).inflate(
            getLayoutId(viewType), parent, false
        )
    )

    inner class BaseMvvmRecyclerViewHolder<B : ViewDataBinding>(v: View) :
        RecyclerView.ViewHolder(v) {
        var binder: B? = DataBindingUtil.bind(v)

        fun bind(item: T, binder: B) {
            binder.setVariable(BR.item, item)
            binder.setVariable(BR.vm, viewModel)
            binder.lifecycleOwner = lifecycleOwner
        }
    }
}