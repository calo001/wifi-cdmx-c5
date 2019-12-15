package com.github.wifigratuitoc5.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.wifigratuitoc5.databinding.ItemWifiBinding
import com.github.wifigratuitoc5.model.Record

/**
 * Clase adaptador para mostrar la lista de Wifis en un RecyclerView
 * @property clickClickListener Funcion a ejecutarse cuando se da clic en un item
 */
class WifiAdapter(private val clickClickListener: WifiItemClickListener):
    ListAdapter<Record, WifiAdapter.WifiViewHolder>(WifiDiffCallback()) {

    /**
     * Crea un objeto viewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
        return WifiViewHolder.from(parent)
    }

    /**
     * Configura los elementos de la visa del viewHolder
     */
    override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
        val item = getItem(position) as Record
        holder.bind(item, clickClickListener)
    }

    /**
     * ViewHolder que describe la configuracion de cada item del recyclerView
     * @property binding Objeto de Databinding desde la vista XML
     */
    class WifiViewHolder private constructor(private val binding: ItemWifiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Objeto que proporciona una instancia de [WifiViewHolder]
         */
        companion object {
            fun from(parent: ViewGroup): WifiViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWifiBinding.inflate(layoutInflater, parent, false)
                return WifiViewHolder(binding)
            }
        }

        /**
         * Configura las variables usadas en el DataBinding
         * @param item item actual
         * @param clickClickListener funcion a ejecutar al dar clic
         */
        fun bind(item: Record, clickClickListener: WifiItemClickListener) {
            binding.wifi = item
            binding.clickListener = clickClickListener
        }
    }
}

/**
 * Clase usada para el ListAdapter que ayuda a identificar los elementos de la lista para
 * actualizar su estado en el recyclerView
 */
class WifiDiffCallback: DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.recordid == newItem.recordid
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return  oldItem == newItem
    }
}

/**
 * Establece el uso del clickListener en cada Item
 * @property clickListener metodo a recibir
 */
class WifiItemClickListener(val clickListener: (Record) -> Unit) {
    fun onClick(record: Record) = clickListener(record)
}