package com.github.wifigratuitoc5.repository

import com.github.wifigratuitoc5.model.WifiAccessPoint
import com.github.wifigratuitoc5.network.OpenDataAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Objeto repositorio para obtener la lista de Wifis
 */
object OpenDataRepository {
    /**
     * Objeto que se inicializa cuando se utiliza por primera vez, se carga la implementacion
     * desde retrofit de la interfa< OpenDataAPI
     */
    private val client by lazy { OpenDataAPI.retrofitService }

    /**
     * Proporiona la lista de Wifis desde Retrofit
     * @return Valor Observable para usar con RXJava
     */
    fun getWifiList(): Observable<WifiAccessPoint> {
        return client.getWifiC5List()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}