package models

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.distinctUntilChanged

data class Terminal(
    var nombre: String,
    val maxMuestras: Int,
    val muestrasFlow: SharedFlow<Muestra>
) {
    private var file = FileController.init()

    suspend fun leerMuestras(){
        muestrasFlow.buffer(maxMuestras).distinctUntilChanged().collect() {
            delay((1000..1500).random().toLong())
            println("Terminal: $nombre recibe: $it")
            if (it.porcentajePureza > 60){
                println("-La terminal: $nombre escribiendo en fichero...")
                file.appendText("La terminal: $nombre ha recogido la muestra: $it \n")

                println("\t --Informacion agregada de la terminal: $nombre")
            }
        }
        println("\t \t -La terminal: $nombre ha llegado a su limite.") // No salta nunca
    }
}
