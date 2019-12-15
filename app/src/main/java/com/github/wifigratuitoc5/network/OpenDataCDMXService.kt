package com.github.wifigratuitoc5.network

import com.github.wifigratuitoc5.model.WifiAccessPoint
import com.github.wifigratuitoc5.util.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Configuracion de retrofit, estableciendo Gson para convertir las respuestas a objetos Java
 * y el Adarter factory para usar RxJava en las respuestas de datos
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * Interfaz que define en Endpoint de la API de OpenData de la CDMX
 */
interface OpenDataCDMXService {
    /**
     * Funcion que realiza una peticion GET con los parametros establecidos
     */
    @GET("search")
    fun getWifiC5List(
        @Query("dataset") dataSet: String = "ubicacion-acceso-gratuito-internet-wifi-c5",
        @Query("facet") facetColonia: String = "colonia",
        @Query("facet") facetAlcaldia: String = "alcaldia",
        @Query("facet") facetBoton: String = "boton",
        @Query("facet") facetAltavoz: String = "altavoz",
        @Query("facet") facetTipoPoste: String = "tipo_de_poste",
        @Query("facet") facetEstatusClausura: String = "estatus_clausura",
        @Query("rows") rows: Int = 50
    ): Observable<WifiAccessPoint>
}

/**
 * Objeto que retorna la instancia generada por retrofit de la interfaz [OpenDataCDMXService]
 */
object OpenDataAPI {
    val retrofitService: OpenDataCDMXService by lazy {
        retrofit.create(OpenDataCDMXService::class.java)
    }
}