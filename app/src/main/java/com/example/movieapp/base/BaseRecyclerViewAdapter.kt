package com.example.movieapp.base

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any> :
    ListAdapter<T , RecyclerView.ViewHolder>(BaseItemCallback<T>()) {

    abstract fun getViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) =
        (holder as Bind<T>).bind(getItem(position))

    interface Bind<T> {
        fun bind(item: T)
    }
}