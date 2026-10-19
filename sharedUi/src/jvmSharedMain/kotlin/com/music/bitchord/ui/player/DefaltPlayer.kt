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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
internal val HUD_ARM = 26.dp

/** El paso de la rejilla técnica, el mismo que el fondo de la app. */
private val GRID_STEP = 36.dp

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

/**
 * DEDSEC // DEFALT — la rejilla técnica sobre el fondo de la carátula.
 *
 * La misma malla que lleva el fondo de la app (`DefaltBackdrop.kt`, módulo
 * `:app`): una línea cada 36dp y una más marcada cada cinco. Aquí es algo más
 * presente que allí a propósito — aquel fondo es casi negro y éste lleva los
 * colores de la portada, que se comen una línea al 5%.
 *
 * Se aplica **antes** que la carátula en el árbol, así que la rejilla sólo se
 * ve donde asoma el mesh: alrededor y por debajo del vinilo, nunca por encima.
 */
internal fun Modifier.defaltGrid(step: Dp = GRID_STEP): Modifier = drawBehind {
    val minor = DefaltCyan.copy(alpha = 0.075f)
    val major = DefaltCyan.copy(alpha = 0.14f)
    val px = step.toPx()
    var index = 0
    var x = 0f
    while (x <= size.width) {
        drawLine(if (index % 5 == 0) major else minor, Offset(x, 0f), Offset(x, size.height), 1f)
        x += px
        index++
    }
    index = 0
    var y = 0f
    while (y <= size.height) {
        drawLine(if (index % 5 == 0) major else minor, Offset(0f, y), Offset(size.width, y), 1f)
        y += px
        index++
    }
}

/**
 * DEDSEC // DEFALT — la firma del visor.
 *
 * `RAT // PLAYER` apoyada en la esquina de abajo a la izquierda, dentro del
 * visor y en la monoespaciada de los rótulos. No lleva ningún dato: es la firma,
 * no un instrumento — la pantalla ya dice lo que tiene que decir.
 *
 * [alpha] se lee en la capa gráfica, no en la composición: la firma se va con el
 * reproductor cuando éste se pliega, sin recomponer la pantalla en el viaje.
 */
@Composable
internal fun DefaltHudSignature(
    alpha: () -> Float,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "RAT // PLAYER",
        modifier = modifier.graphicsLayer { this.alpha = alpha() },
        style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 9.sp,
            letterSpacing = 1.4.sp,
            fontWeight = FontWeight.Medium,
            // Sobre una carátula clara, un cian al 75% sin sombra se pierde.
            shadow = Shadow(Color.Black.copy(alpha = 0.55f), Offset(0f, 1f), 4f),
        ),
        color = DefaltCyan.copy(alpha = 0.75f),
        maxLines = 1,
    )
}
