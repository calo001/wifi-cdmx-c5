package com.github.wifigratuitoc5.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.wifigratuitoc5.model.Record
import com.github.wifigratuitoc5.repository.OpenDataRepository

/**
 * Clase ViewModel usado por la [MainActivity] y sus fragments
 * @param recordList
 */
class MainViewModel : ViewModel() {

    /**
     * Lista de Wifi gratuito
     */
    private var _recordList = MutableLiveData<List<Record>>()

    /**
     * Backing property de la lista de wifi gratuito
     */
    val recordList: LiveData<List<Record>>
        get() = _recordList


    /**
     * Estado de la carga de datos
     */
    private var _status = MutableLiveData<OpenDataStatus>()

    /**
     * Backing property del estado de la carga de datos
     */
    val status: LiveData<OpenDataStatus>
        get() = _status

    /**
     * Wifi gratuido actual usado para [com.github.wifigratuitoc5.ui.detail.DetailFragment]
     */
    val _detailRecord = MutableLiveData<Record?>()

    /**
     * Baking property para el Wifi gratuito de detalle
     */
    val detailRecord: LiveData<Record?>
        get() = _detailRecord

    /**
     * Indicador usado para la navegacion a [com.github.wifigratuitoc5.ui.map.MapFragment]
     */
    private val _navigateMapArgs = MutableLiveData<Boolean>()

    /**
     * Baking property para el Indicador de navegacion
     */
    val navigateMapArs: LiveData<Boolean>
        get() = _navigateMapArgs

    /**
     * Bloque de inicializacion para cargar la lista de Wifis
     */
    init {
        getWifiList()
    }

    /**
     * Carga de la lista de Wifis usando el repositorio [com.github.wifigratuitoc5.repository.OpenDataRepository]
     */
    @SuppressLint("CheckResult")
    private fun getWifiList() {
        startLoading()
        OpenDataRepository.getWifiList()
            .subscribe ( { result ->
                doneLoading()
                _recordList.value = result.records
            }, { error ->
                errorLoading()
            })
    }

    /**
     * Actualiza el valor de [_detailRecord]
     */
    fun setDetailRecord(record: Record) {
        _detailRecord.value = record
    }

    /**
     * Actualiza el valor de [_status] a [OpenDataStatus.LOADING]
     */
    fun startLoading() {
        _status.value = OpenDataStatus.LOADING
    }

    /**
     * Actualiza el valor de [_status] a [OpenDataStatus.DONE]
     */
    fun doneLoading() {
        _status.value = OpenDataStatus.DONE
    }

    /**
     * Actualiza el valor de [_status] a [OpenDataStatus.ERROR]
     */
    private fun errorLoading() {
        _status.value = OpenDataStatus.ERROR
    }

    /**
     * Actualiza el valor de [_navigateMapArgs] a true
     */
    fun onNavigateToMapArgs() {
        _navigateMapArgs.value = true
    }

    /**
     * Actualiza el valor de [_navigateMapArgs] a false
     */
    fun onNavigateMapArgsDone() {
        _navigateMapArgs.value = false
    }
}

/**
 * Define los estados de la carga de datos
 */
enum class OpenDataStatus { LOADING, ERROR, DONE }