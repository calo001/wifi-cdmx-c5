package com.github.wifigratuitoc5.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.wifigratuitoc5.ui.MainViewModel

fun Fragment.getMainViewModel(): MainViewModel? =
    activity?.let {
        ViewModelProviders.of(it).get(MainViewModel::class.java)
    }