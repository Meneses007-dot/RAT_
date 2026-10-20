package com.music.bitchord.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.music.bitchord.R
import com.music.bitchord.ui.theme.ChakraPetch
import com.music.bitchord.ui.theme.DedSec
import com.music.bitchord.ui.theme.JetBrainsMono

/**
 * DEDSEC // DEFALT — la nota que se cuela en la pantalla.
 *
 * Cae sobre lo que haya cuando alguien insiste en el emblema (ver [HomeScreen] y
 * el toque secreto del título de Inicio). No es un aviso de la app ni pide nada:
 * es una nota de las dieciséis, con el aire de una transmisión pillada al vuelo
 * —corchetes de encuadre, rótulo monoespaciado, una luz roja— y se va sola.
 *
 * La frase se muestra en su idioma original y sin comillas tipográficas: así la
 * escribía Defalt, y así se reconoce. Lo único que se traduce es el marco.
 *
 * @param quote la frase, tal cual sale de [com.music.bitchord.ui.theme.DefaltQuotes].
 * @param onDismiss tocar en cualquier sitio.
 */
@Composable
fun DefaltIntercept(
    quote: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var shown by remember(quote) { mutableStateOf(false) }
    LaunchedEffect(quote) { shown = true }

    // Entra con un golpe corto: aparece antes de que dé tiempo a leerla, como
    // una ventana que se abre sola.
    val appear by animateFloatAsState(
        targetValue = if (shown) 1f else 0f,
        animationSpec = tween(durationMillis = 170, easing = FastOutSlowInEasing),
        label = "defaltIntercept",
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer { alpha = appear }
            .background(SCRIM_COLOR)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onDismiss,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .width(INTERCEPT_WIDTH)
                .graphicsLayer {
                    val grow = 0.94f + 0.06f * appear
                    scaleX = grow
                    scaleY = grow
                }
                .clip(RoundedCornerShape(INTERCEPT_CORNER))
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF0B1424), Color(0xFF070C15)),
                    ),
                ),
        ) {
            // Corchetes de encuadre: lo unico que insinua que esto es una
            // captura y no un mensaje de la app. Van sobre el borde del panel,
            // no dentro del texto, y metidos unos dp para que la esquina
            // redondeada no se los coma.
            Canvas(Modifier.matchParentSize()) {
                val inset = 7.dp.toPx()
                val arm = 12.dp.toPx()
                val stroke = 1.2.dp.toPx()
                val tone = DedSec.Cyan.copy(alpha = 0.55f)
                val left = inset
                val top = inset
                val right = size.width - inset
                val bottom = size.height - inset
                listOf(
                    Triple(Offset(left, top), Offset(left + arm, top), Offset(left, top + arm)),
                    Triple(Offset(right, top), Offset(right - arm, top), Offset(right, top + arm)),
                    Triple(Offset(left, bottom), Offset(left + arm, bottom), Offset(left, bottom - arm)),
                    Triple(Offset(right, bottom), Offset(right - arm, bottom), Offset(right, bottom - arm)),
                ).forEach { (corner, along, down) ->
                    drawLine(tone, corner, along, strokeWidth = stroke, cap = StrokeCap.Square)
                    drawLine(tone, corner, down, strokeWidth = stroke, cap = StrokeCap.Square)
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 16.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(DedSec.Red),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.defalt_intercept_label).uppercase(),
                        style = TextStyle(fontFamily = JetBrainsMono, 
                            fontSize = 9.sp,
                            fontWeight = FontWeight.W600,
                            letterSpacing = 1.6.sp,
                        ),
                        color = DedSec.Cyan,
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = quote,
                    style = TextStyle(fontFamily = ChakraPetch, 
                        fontSize = 21.sp,
                        fontWeight = FontWeight.W500,
                        lineHeight = 26.sp,
                    ),
                    color = Color.White,
                )

                Spacer(Modifier.height(16.dp))

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(DedSec.Cyan.copy(alpha = 0.22f)),
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "DEDSEC",
                        style = TextStyle(fontFamily = JetBrainsMono, 
                            fontSize = 9.sp,
                            fontWeight = FontWeight.W700,
                            letterSpacing = 2.4.sp,
                        ),
                        color = DedSec.Red,
                    )
                    Text(
                        text = stringResource(R.string.defalt_intercept_dismiss).uppercase(),
                        style = TextStyle(fontFamily = JetBrainsMono, fontSize = 9.sp, letterSpacing = 1.2.sp),
                        color = Color.White.copy(alpha = 0.38f),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
    }
}

private val INTERCEPT_WIDTH = 300.dp
private val INTERCEPT_CORNER = 6.dp
