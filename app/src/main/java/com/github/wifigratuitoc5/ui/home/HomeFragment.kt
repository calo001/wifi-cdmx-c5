package com.github.wifigratuitoc5.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.wifigratuitoc5.databinding.FragmentHomeBinding
import com.github.wifigratuitoc5.ui.MainViewModel
import com.github.wifigratuitoc5.util.getMainViewModel

/**
 * Fragmento principal de la aplicacion donde se muestra la lista de puntos de Wifi
 *
 * Obtiene la referencia del viewModel de la actividad padre [com.github.wifigratuitoc5.ui.MainActivity]
 * para utilizarlo con el proposito de cargar la lista de Wifis y mostrarlos en una lista
 */
class HomeFragment : Fragment() {

    /**
     * ViewModel del fragmento
     */
    private var homeViewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Obtiene el viewModel de la actividad usando la Extension Function
         * [com.github.wifigratuitoc5.util.getMainViewModel]
         */
        homeViewModel = getMainViewModel()

        /**
         * Utiliza DataBinding para gestionar las vista del XML
         */
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        /**
         * Configura el adaptador del recyclerView
         */
        setupAdapter(binding.rvList)

        return binding.root
    }

    /**
     * Configura el adaptador del recyclerView y definiendo una funcion para el comportamiento del
     * clickListener
     * @param rvList el recyclerView de la vista desde DataBinding
     */
    private fun setupAdapter(rvList: RecyclerView) {
        rvList.adapter = WifiAdapter(WifiItemClickListener { record ->
            homeViewModel?.setDetailRecord(record)
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment())
        })
    }
}