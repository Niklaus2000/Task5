package com.example.movieapp.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.base.BaseRecyclerViewAdapter
import com.example.movieapp.data.entity.MoviesListItem
import com.example.movieapp.databinding.MovieListItemBinding

class MoviesAdapter : BaseRecyclerViewAdapter<MoviesListItem>() {
    override fun getViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder =
        MoviesItemViewHolder(
            MovieListItemBinding
                .inflate(
                    LayoutInflater.from(parent.context) ,
                    parent ,
                    false)
        )
}





