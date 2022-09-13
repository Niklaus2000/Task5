package com.example.movieapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ProvideResources {

    fun string(@StringRes id: Int): String

    class Base(
        private val context: Context ,
    ) : ProvideResources {
        override fun string(id: Int): String = context.getString(id)
    }
}