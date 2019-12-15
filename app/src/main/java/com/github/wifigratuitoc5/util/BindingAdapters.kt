package com.github.wifigratuitoc5.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.wifigratuitoc5.R
import com.github.wifigratuitoc5.model.Record
import com.github.wifigratuitoc5.ui.OpenDataStatus
import com.github.wifigratuitoc5.ui.home.WifiAdapter

/**
 * Actualiza la lista de un recyclerView
 * @param recyclerView vista recyclerView
 * @param listData lista de Wifis para actializar en el recyclerView usando submitList de [ListAdapter]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView?, listData: List<Record>?) {
    recyclerView?.adapter?.let {
        val adapter = it as WifiAdapter
        it.submitList(listData)
    }
}

/**
 * Actualiza el fondo y el color de texto de un textView
 * @param textView a modificar
 * @param active cadena de texto que indica el estado
 */
@BindingAdapter("activeBackground")
fun bindActiveBackground(textView: TextView, active: String) {
    when(active) {
        ACTIVE -> {
            textView.setBackgroundResource(R.drawable.back_wifi_active)
            textView.setTextColor(ContextCompat.getColor(textView.context,R.color.dark_active))
            textView.text = textView.context.getString(R.string.wifi_active)
        }
        INACTIVE -> {
            textView.setBackgroundResource(R.drawable.back_wifi_inactive)
            textView.setTextColor(ContextCompat.getColor(textView.context,R.color.dark_inactive))
            textView.text = textView.context.getString(R.string.wifi_inactive)
        }
    }
}

/**
 * Actualiza el color de un imageView
 * @param imageView a modificar* @param active cadena de texto que indica el estado
 * @param active cadena de texto que indica el estado
 */
@BindingAdapter("activeWifi")
fun bindActiveWifi(imageView: ImageView, active: String) {
    when(active) {
        ACTIVE -> {
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.dark_active))
        }
        INACTIVE -> {
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.dark_inactive))
        }
    }
}

/**
 * Cambia la visualizacion de un progressbar segun el valor de status
 * @param progressBar a modificar
 * @param status indica el estado para establecer una visivilidad
 */
@BindingAdapter("showProgress")
fun bindShowProgress(progressBar: ProgressBar, status: OpenDataStatus) {
    when (status) {
        OpenDataStatus.LOADING -> progressBar.visibility = View.VISIBLE
        OpenDataStatus.DONE    -> progressBar.visibility = View.GONE
        OpenDataStatus.ERROR   -> progressBar.visibility = View.GONE
    }
}

/**
 * Cambia la visualizacion de un progressbar segun el valor de status
 * @param imageView a modificar
 * @param status indica el estado para establecer una visivilidad
 */
@BindingAdapter("showError")
fun bindShowError(imageView: ImageView, status: OpenDataStatus) {
    when (status) {
        OpenDataStatus.LOADING -> imageView.visibility = View.GONE
        OpenDataStatus.DONE    -> imageView.visibility = View.GONE
        OpenDataStatus.ERROR   -> imageView.visibility = View.VISIBLE
    }
}