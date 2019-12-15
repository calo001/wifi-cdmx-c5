package com.github.wifigratuitoc5.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.wifigratuitoc5.databinding.FragmentMapBinding
import com.github.wifigratuitoc5.ui.MainViewModel
import com.github.wifigratuitoc5.util.getMainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Fragmento para mostrar un mapa y marcadores
 *
 * Obtiene la referencia del viewModel de la actividad padre [com.github.wifigratuitoc5.ui.MainActivity]
 * para utilizarlo con el proposito de cargar la lista de Wifis y obtener su localizacion
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    /**
     * ViewModel del fragmento
     */
    private var mapViewModel: MainViewModel? = null

    /**
     * Vista obtenida del layout XML
     */
    private var mMapView: MapView? = null

    /**
     * Objeto GoogleMap para agregar configuracion
     */
    private var mGoogleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Obtiene el viewModel de la actividad usando la Extension Function
         * [com.github.wifigratuitoc5.util.getMainViewModel]
         */
        mapViewModel = getMainViewModel()

        /**
         * Utiliza DataBinding para gestionar las vista del XML
         */
        val binding = FragmentMapBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = mapViewModel

        /**
         * Configuracion del mapa
         */
        mapViewModel?.startLoading()
        mMapView = binding.map
        mMapView?.onCreate(savedInstanceState)
        mMapView?.getMapAsync(this)

        return binding.root
    }

    /**
     * Configura el miembro mGoogleMap cuando el mapa ha sido cargado
     * @param googleMap para ser asignado a mGoogleMap
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        mGoogleMap?.uiSettings?.isZoomControlsEnabled = true
        mapViewModel?.doneLoading()

        setMarkerFromArgs()
    }

    /**
     * Obtiene los valores de latitud y longitud del objeto arguments del fragmento para
     * establecer un solo marcador en el mapa, si los argumentos son nulos se obtiene una exception
     * [TypeCastException] por lo que se llama al metodo setAllMarkers por defecto
     */
    private fun setMarkerFromArgs() {
        mGoogleMap?.clear()
        arguments?.let {
            try {
                val latitud = it.get("latitud") as String
                val longitud = it.get("longitud") as String
                val title = it.get("title") as String

                setMarker(latitud.toDouble(), longitud.toDouble(), title)
                setZoom(latitud.toDouble(), longitud.toDouble())
            } catch (e: TypeCastException) {
                setAllMarkers()
            }
        }
    }

    /**
     * Obtiene la lista de Wifis de mapViewModel y los agrega al mapa
     */
    private fun setAllMarkers() {
        val list = mapViewModel?.recordList?.value

        list?.let { listRecord ->
            listRecord.forEach {
                setMarker(it.fields.latitud, it.fields.longitud, it.fields.colonia)
            }

            setZoom(listRecord[0].fields.latitud, listRecord[0].fields.longitud)
        }
    }

    /**
     * Establece un marcador en el objeto mGoogleMap
     * @param latitud del marcador a agregar
     * @param longitud del marcador a agregar
     * @param title del marcador a agregar
     */
    private fun setMarker(latitud: Double, longitud: Double, title: String) {
        val marker = MarkerOptions().position(LatLng(latitud, longitud))
        mGoogleMap?.addMarker(marker)?.title = title
    }

    /**
     * Establece un nivel de zoom al mapa segun una localizacion
     * @param latitud de la localizacion a enfocar
     * @param longitud de la localizacion a enfocar
     * @param zoom de la localizacion a enfocar
     */
    private fun setZoom(latitud: Double, longitud: Double, zoom: Float = 10F) {
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(
            latitud, longitud), zoom))
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mMapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }
}