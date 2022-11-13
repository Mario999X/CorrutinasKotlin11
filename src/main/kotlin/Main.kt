import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.Androide
import models.Terminal

// Caso con SharedFlow para aplicar un buffer limitado al productor
// No termina nunca, y esta recomendando para 1-M, el dato transmitido es el mismo para los consumidores.
fun main() = runBlocking {

    FileController.resetFile()

    val androide = Androide("R2D2", 15)

    val terminales = listOf(Terminal("Luke", 5, androide.muestras), Terminal("Leia", 5, androide.muestras))

    launch { androide.producirMuestras() }

    terminales.forEach {
        launch {
            it.leerMuestras()
        }
    }
}