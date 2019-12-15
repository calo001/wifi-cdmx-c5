package com.github.wifigratuitoc5.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.github.wifigratuitoc5.databinding.FragmentDetailBinding
import com.github.wifigratuitoc5.ui.MainViewModel
import com.github.wifigratuitoc5.util.getMainViewModel

/**
 * Fragmento donde se muestra la informacion de un punto Wifi
 *
 * Obtiene la referencia del viewModel de la actividad padre [com.github.wifigratuitoc5.ui.MainActivity]
 * para utilizarlo con el proposito de cargar la informacion del Wifi actual
 */
class DetailFragment : Fragment() {
    /**
     * ViewModel del fragmento
     */
    private var detailViewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Obtiene el viewModel de la actividad usando la Extension Function
         * [com.github.wifigratuitoc5.util.getMainViewModel]
         */
        detailViewModel = getMainViewModel()

        /**
         * Configura el observador de la navegacion
         */
        setupObservers()

        /**
         * Utiliza DataBinding para gestionar las vista del XML
         */
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        return binding.root
    }

    /**
     * Observador para el LiveData navigateMapArgs, cuando es true utiliza el NavController para
     * cambiar al MapFragment pasandole los argumentos de latitud, longitud y titulo, despues se llama
     * al metodo onDetach() para descoplar de la actividad el fragmento
     */
    private fun setupObservers() {
        detailViewModel?.navigateMapArs?.observe(this, Observer { navigateToMap ->
            if (navigateToMap) {
                detailViewModel?.detailRecord?.value?.let { record ->
                    findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToNavigationMap(
                        record.fields.latitud.toString(),
                        record.fields.longitud.toString(),
                        record.fields.colonia
                    ))
                    onDetach()
                }
            }
        })
    }

    /**
     * Se cambia el valor de detailRecord desde el metodo onNavigateMapArgsDone para
     * regresarlo a su valor inicial.
     */
    override fun onDetach() {
        super.onDetach()
        detailViewModel?.onNavigateMapArgsDone()
    }
}
