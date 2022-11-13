package models

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Androide(
    val nombre: String,
    val cantidadMinerales: Int,

){
    private val _muestras = MutableSharedFlow<Muestra>(replay = 8, extraBufferCapacity = 8)
    val muestras: SharedFlow<Muestra> = _muestras.asSharedFlow()

    suspend fun producirMuestras(){
        for (i in 1..cantidadMinerales){
            delay(1500)
            val muestra = Muestra(i, nombre)
            println("\t$muestra producida -> $nombre")
            _muestras.emit(muestra)
        }
    }
}
