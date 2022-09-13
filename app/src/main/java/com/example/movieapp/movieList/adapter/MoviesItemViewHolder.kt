package com.example.movieapp.movieList.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.base.BaseRecyclerViewAdapter
import com.example.movieapp.data.entity.MoviesListItem
import com.example.movieapp.databinding.MovieListItemBinding

class MoviesItemViewHolder(
    private val binding: MovieListItemBinding ,
) : RecyclerView.ViewHolder(binding.root) , BaseRecyclerViewAdapter.Bind<MoviesListItem> {

    override fun bind(item: MoviesListItem) = item.bind(binding)
}