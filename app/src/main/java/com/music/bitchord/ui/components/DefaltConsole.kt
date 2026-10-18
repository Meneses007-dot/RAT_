/*
 * DEDSEC // DEFALT — Custom Edition (Fase 6).
 *
 * Las piezas de "consola" de la barra inferior y del mini-reproductor: el filo
 * cian que enmarca el cristal, el acento con el que se marca lo seleccionado, y
 * el ecualizador de tres barras que dice que algo está sonando.
 *
 * Todo es decorativo. Ninguna de estas piezas lee del reproductor nada que la
 * barra no le diera ya (`isPlaying`), y ninguna toca lo que hacen los controles.
 */

package com.music.bitchord.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** El grosor del filo de consola: un hilo, no un marco. */
private val RIM_WIDTH = 1.dp

/** Cian DedSec: lo que se enciende. */
private val SYSTEM_CYAN = Color(0xFF00E5FF)

/** Azul royal: lo mismo, pero legible sobre cristal claro. */
private val ROYAL = Color(0xFF0037E6)

@Composable
private fun onLightSurface(): Boolean = MaterialTheme.colorScheme.surface.luminance() > 0.5f

/**
 * El color del filo cian que llevan el cristal de la barra y el del mini
 * reproductor. Se ajusta al tema por la misma razón que [glassContentColor]:
 * la superficie es translúcida y el tema no manda en lo que hay detrás.
 */
@Composable
internal fun defaltRimColor(): Color =
    if (onLightSurface()) ROYAL.copy(alpha = 0.30f) else SYSTEM_CYAN.copy(alpha = 0.32f)

/**
 * El filo de consola: un hilo cian sobre el borde del cristal.
 *
 * El cristal ya trae su propio brillo (el [Highlight] del material, que en
 * oscuro es blanco al 10%); esto es otra cosa — la línea de "sistema" que dice
 * que la superficie está encendida. Se distingue del brillo por el color, no por
 * el grosor, así que no engorda la barra.
 */
@Composable
fun Modifier.defaltRim(shape: Shape = RectangleShape): Modifier =
    border(RIM_WIDTH, defaltRimColor(), shape)

/**
 * El color de lo seleccionado: la pestaña activa y el indicador que la sigue.
 *
 * Antes la pestaña activa era la misma tinta que las demás (blanco) y solo el
 * indicador —un negro al 50%— decía cuál estaba. En cian, la pestaña encendida
 * y su pastilla dicen lo mismo a la vez.
 */
@Composable
internal fun defaltSelectedColor(): Color =
    if (onLightSurface()) Color(0xFF00229E) else SYSTEM_CYAN

/** El relleno del indicador de pestaña: el acento, muy diluido. */
@Composable
internal fun defaltSelectColor(): Color =
    if (onLightSurface()) ROYAL.copy(alpha = 0.14f) else SYSTEM_CYAN.copy(alpha = 0.16f)

/** El acento suelto (ecualizador, etiquetas de sistema). */
@Composable
internal fun defaltAccentColor(): Color =
    if (onLightSurface()) Color(0xFF00229E) else SYSTEM_CYAN

/**
 * El ecualizador de tres barras que marca lo que suena.
 *
 * Decorativo de principio a fin: no lee la onda ni el nivel, solo late mientras
 * [isPlaying] sea cierto. Parado se queda en tres barras bajas, que es lo que
 * se ve en pausa.
 */
@Composable
internal fun DefaltEq(
    isPlaying: Boolean,
    color: Color,
    modifier: Modifier = Modifier,
    barWidth: Dp = 2.dp,
    height: Dp = 10.dp,
) {
    if (isPlaying) {
        DefaltEqBars(color = color, modifier = modifier, barWidth = barWidth, height = height)
    } else {
        DefaltEqStatic(color = color, modifier = modifier, barWidth = barWidth, height = height)
    }
}

@Composable
private fun DefaltEqBars(
    color: Color,
    modifier: Modifier,
    barWidth: Dp,
    height: Dp,
) {
    // Tres periodos distintos, para que las barras no suban y bajen a la vez
    // (eso sería un latido, no un ecualizador).
    val transition = rememberInfiniteTransition(label = "defaltEq")
    val periods = listOf(560, 690, 470)
    val levels = periods.mapIndexed { index, period ->
        transition.animateFloat(
            initialValue = 0.28f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = period, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "defaltEq$index",
        )
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(barWidth),
    ) {
        levels.forEach { level -> EqBar(height = height * level.value, width = barWidth, color = color) }
    }
}

@Composable
private fun DefaltEqStatic(
    color: Color,
    modifier: Modifier,
    barWidth: Dp,
    height: Dp,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(barWidth),
    ) {
        listOf(0.34f, 0.5f, 0.42f).forEach { level ->
            EqBar(height = height * level, width = barWidth, color = color.copy(alpha = 0.55f))
        }
    }
}

@Composable
private fun EqBar(height: Dp, width: Dp, color: Color) {
    Spacer(
        Modifier
            .width(width)
            .height(height)
            .border(RIM_WIDTH, color),
    )
}

/**
 * La etiqueta de sistema que va sobre el reproductor: `AUDIO_STREAM`.
 *
 * Es texto decorativo de terminal, como los rótulos del onboarding: mismo
 * alfabeto monoespaciado y mismo subrayado. No es una cadena de la interfaz y
 * no se traduce, igual que `DEDSEC_NETWORK // INIT` no se traduce.
 */
@Composable
internal fun DefaltStreamTag(color: Color, modifier: Modifier = Modifier) {
    Text(
        text = "AUDIO_STREAM",
        modifier = modifier,
        style = MaterialTheme.typography.labelSmall,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        letterSpacing = 1.4.sp,
        color = color.copy(alpha = 0.85f),
        maxLines = 1,
    )
}
