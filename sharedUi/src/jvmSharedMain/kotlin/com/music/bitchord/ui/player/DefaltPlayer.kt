/*
 * DEDSEC // DEFALT — Custom Edition (Fase 6).
 *
 * Dos cosas de la pantalla de reproducción: los colores de sistema de esta
 * edición, y el visor que enmarca la pantalla.
 *
 * El visor es adorno de principio a fin. No enmarca nada que la app no
 * dibujara ya, no ocupa sitio en el reparto, no recibe tacto y no lee del
 * reproductor nada que la pantalla no tuviera a mano.
 */

package com.music.bitchord.ui.player

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Cian DedSec: el color de sistema de esta edición — lo que está encendido,
 * medido o seleccionado. El mismo que usa la barra inferior.
 *
 * Vive aquí y no en `RAT`/`DedSec` del módulo `:app` porque el reproductor es
 * código compartido y no puede ver los recursos ni los colores de la app.
 */
internal val DefaltCyan = Color(0xFF00E5FF)

/** El rojo de Defalt: lo que avisa. Casa con `AccentRed` del módulo `:app`. */
internal val DefaltRed = Color(0xFFDC0736)

/** Cuánto mide el brazo de cada corchete. */
private val HUD_ARM = 26.dp

/**
 * Lo que baja el hilo por debajo de la barra de estado. El visor se separa del
 * borde de arriba tanto como la barra de estado ocupe, más esto: si no, el
 * reloj del sistema cae dentro del corchete.
 */
internal val HUD_TOP_GAP = 6.dp

/** El grosor del hilo: el del visor, que es el mismo que el de la barra. */
private val HUD_STROKE = 1.5.dp

/**
 * DEDSEC // DEFALT — el visor de la pantalla de reproducción.
 *
 * Un hilo cian por el borde y un corchete en cada esquina, dibujados **encima**
 * de todo sin ocupar sitio: la pantalla se lee como lo que un sistema está
 * mirando, que es exactamente lo que hace la app. Es lo que en la guía son los
 * "corchetes de esquina".
 *
 * [alpha] se lee **en el dibujo**, no al construir el modificador: así el visor
 * puede apagarse con el reproductor mientras éste se pliega hacia el
 * mini-reproductor sin que la pantalla entera se recomponga en cada fotograma
 * del viaje. La leen `dockFade`/`p` allí, que ya son funciones por lo mismo.
 *
 * [inset] es lo que se separa el hilo del borde. Arriba se pasa aparte porque
 * ahí manda la barra de estado: los corchetes tienen que caer por debajo suyo,
 * o el reloj del sistema les pasa por encima.
 */
internal fun Modifier.defaltHud(
    alpha: () -> Float = { 1f },
    inset: Dp = 12.dp,
    topInset: Dp = inset,
): Modifier = drawWithContent {
    drawContent()
    val shown = alpha()
    if (shown <= 0.01f) return@drawWithContent
    drawDefaltHud(shown, inset.toPx(), topInset.toPx(), HUD_ARM.toPx(), HUD_STROKE.toPx())
}

private fun DrawScope.drawDefaltHud(
    alpha: Float,
    inset: Float,
    topInset: Float,
    arm: Float,
    stroke: Float,
) {
    // El hilo del borde, apenas ahí: dice que la superficie está encendida sin
    // llegar a enmarcar la carátula, que es la que tiene que mandar.
    val frame = DefaltCyan.copy(alpha = 0.10f * alpha)
    // Los corchetes, más presentes: son los que dan la lectura de visor.
    val mark = DefaltCyan.copy(alpha = 0.55f * alpha)

    val left = inset
    val top = topInset
    val right = size.width - inset
    val bottom = size.height - inset

    drawRect(
        color = frame,
        topLeft = Offset(left, top),
        size = Size(right - left, bottom - top),
        style = Stroke(width = stroke),
    )

    // Esquina a esquina, los dos brazos de cada una.
    fun corner(x: Float, y: Float, dx: Float, dy: Float) {
        drawLine(mark, Offset(x, y), Offset(x + dx * arm, y), stroke)
        drawLine(mark, Offset(x, y), Offset(x, y + dy * arm), stroke)
    }
    corner(left, top, 1f, 1f)
    corner(right, top, -1f, 1f)
    corner(left, bottom, 1f, -1f)
    corner(right, bottom, -1f, -1f)
}
