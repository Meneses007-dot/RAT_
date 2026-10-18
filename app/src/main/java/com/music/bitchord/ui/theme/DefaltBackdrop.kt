package com.music.bitchord.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * DEDSEC // DEFALT — Custom Edition (Fase 6).
 *
 * Los colores de la identidad, medidos sobre las referencias de DEFALT_REFERENCES/
 * (ver DEDSEC_DEFALT_DESIGN.md §5). Aquí viven los que necesita el fondo; el tema
 * de Material tiene su propia copia en [DarkColors].
 */
object DedSec {
    /** Negro profundo: casi negro, con un pelín de azul. El suelo de la app. */
    val Deep = Color(0xFF04080F)

    /** Azul royal profundo (relleno del emblema en la referencia). */
    val Royal = Color(0xFF00229E)

    /** El brillo del azul royal (hasta donde sube el degradado del emblema). */
    val RoyalBright = Color(0xFF0037E6)

    /** Azul claro de los contornos de las orejas de la máscara. */
    val RoyalLight = Color(0xFF89BAED)

    /** El único acento agresivo: el rombo de la máscara. */
    val Red = Color(0xFFDC0736)

    /** Cian eléctrico: el color de "sistema" e interacción (DedSec). */
    val Cyan = Color(0xFF00E5FF)

    /** Gris tecnológico para texto secundario. */
    val Grey = Color(0xFFA9B6C5)

    /** Panel: tarjetas y superficies. */
    val Panel = Color(0xFF111820)

    /** Borde y separadores. */
    val Border = Color(0xFF26323B)
}

/**
 * El fondo de la app entera: un terminal encendido, no un lienzo en blanco.
 *
 * Se pinta en el "suelo" que hay detrás de todas las páginas (ver
 * `MainActivity.paintBackdrop`), así que cualquier pantalla que no pinte su
 * propio fondo —que son todas, salvo el reproductor— lo deja ver. El cristal de
 * la barra inferior muestrea esta misma capa, de modo que la rejilla pasa por
 * debajo del cristal en vez de cortarse en él.
 *
 * Todo es decorativo: una base oscura, un par de halos (azul royal arriba, rojo
 * abajo), una rejilla técnica muy tenue, scanlines de CRT y una viñeta que cierra
 * los bordes. Nada de esto sugiere funciones que la app no tenga.
 *
 * @param density densidad de la pantalla, para que la rejilla mida en dp y no en px.
 */
fun DrawScope.drawDefaltBackdrop(density: Float) {
    // Base. Un pelín más clara que el negro puro para que las scanlines se vean.
    drawRect(Color(0xFF070B12))

    // Halo azul royal, arriba a la izquierda: da profundidad sin iluminar el centro.
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(DedSec.Royal.copy(alpha = 0.60f), Color.Transparent),
            center = Offset(size.width * 0.14f, -size.height * 0.05f),
            radius = size.maxDimension * 0.95f,
        ),
    )

    // Aliento cian en la esquina opuesta: el "sistema" siempre encendido.
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(DedSec.Cyan.copy(alpha = 0.09f), Color.Transparent),
            center = Offset(size.width * 0.92f, size.height * 0.10f),
            radius = size.maxDimension * 0.6f,
        ),
    )

    // Rojo Defalt, abajo a la derecha, muy tenue: el "rombo" fuera de foco.
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(DedSec.Red.copy(alpha = 0.22f), Color.Transparent),
            center = Offset(size.width * 0.94f, size.height * 1.06f),
            radius = size.maxDimension * 0.85f,
        ),
    )

    // Rejilla técnica. Cada cinco líneas, una algo más marcada: se lee como
    // una cuadrícula de instrumento, no como un folio.
    val gridStep = 36f * density
    val gridColor = DedSec.Cyan.copy(alpha = 0.05f)
    val gridMajor = DedSec.Cyan.copy(alpha = 0.095f)
    var i = 0
    var gx = 0f
    while (gx <= size.width) {
        drawLine(if (i % 5 == 0) gridMajor else gridColor, Offset(gx, 0f), Offset(gx, size.height), strokeWidth = 1f)
        gx += gridStep
        i++
    }
    i = 0
    var gy = 0f
    while (gy <= size.height) {
        drawLine(if (i % 5 == 0) gridMajor else gridColor, Offset(0f, gy), Offset(size.width, gy), strokeWidth = 1f)
        gy += gridStep
        i++
    }

    // Scanlines de CRT: claras, no oscuras, o sobre un fondo casi negro no se ven.
    val scanStep = 4f * density
    val scanColor = Color.White.copy(alpha = 0.018f)
    var sy = 0f
    while (sy <= size.height) {
        drawLine(scanColor, Offset(0f, sy), Offset(size.width, sy), strokeWidth = 1f)
        sy += scanStep
    }

    // Viñeta: cierra los bordes y centra la mirada.
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f)),
            center = Offset(size.width * 0.5f, size.height * 0.42f),
            radius = size.maxDimension * 0.78f,
        ),
    )
}
