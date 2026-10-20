package com.music.bitchord.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform

/**
 * DEDSEC // DEFALT — letras de bloque, como las pinta una plantilla de spray.
 *
 * Defalt no escribe: etiqueta. En las referencias el logotipo es un stencil de
 * píxeles gruesos, y una marca de agua con una fuente redondeada habría sido
 * otra cosa. Así que el fondo se rotula con esta rejilla de 5 × 7 celdas, la
 * misma idea que los iconos: nada redondo, todo bloque.
 *
 * Solo se dibuja en el fondo decorativo (ver [drawDefaltBackdrop]); no sustituye
 * a ninguna tipografía de la interfaz.
 */
private val PIXEL_GLYPHS: Map<Char, List<String>> = mapOf(
    'D' to listOf(
        "1111.",
        "1...1",
        "1...1",
        "1...1",
        "1...1",
        "1...1",
        "1111.",
    ),
    'E' to listOf(
        "11111",
        "1....",
        "1....",
        "1111.",
        "1....",
        "1....",
        "11111",
    ),
    'S' to listOf(
        ".1111",
        "1....",
        "1....",
        ".111.",
        "....1",
        "....1",
        "1111.",
    ),
    'U' to listOf(
        "1...1",
        "1...1",
        "1...1",
        "1...1",
        "1...1",
        "1...1",
        ".111.",
    ),
    'C' to listOf(
        ".1111",
        "1....",
        "1....",
        "1....",
        "1....",
        "1....",
        ".1111",
    ),
    'R' to listOf(
        "1111.",
        "1...1",
        "1...1",
        "1111.",
        "1.1..",
        "1..1.",
        "1...1",
    ),
    'A' to listOf(
        ".111.",
        "1...1",
        "1...1",
        "11111",
        "1...1",
        "1...1",
        "1...1",
    ),
    'T' to listOf(
        "11111",
        "..1..",
        "..1..",
        "..1..",
        "..1..",
        "..1..",
        "..1..",
    ),
)

/** Ancho de un glifo en celdas, y el hueco que lo separa del siguiente. */
private const val GLYPH_COLS = 5
private const val GLYPH_GAP = 1
private const val GLYPH_ROWS = 7

/** Ancho total de una palabra, en celdas. */
fun pixelWordWidthCells(text: String): Int =
    text.length * GLYPH_COLS + (text.length - 1).coerceAtLeast(0) * GLYPH_GAP

/**
 * Pinta [text] en bloques de [cell] px, con la esquina superior izquierda en
 * [topLeft], girado [rotation] grados alrededor de [pivot].
 *
 * Con [stencil] solo se pintan las celdas del borde de cada letra: es una
 * plantilla recortada, más ligera que el bloque macizo y más legible cuando hay
 * contenido encima. Las letras que no están en la tabla se saltan: esto es un
 * rótulo decorativo, no un motor de texto. El hueco se conserva para que la
 * palabra no se apelotone.
 */
fun DrawScope.drawPixelWord(
    text: String,
    cell: Float,
    topLeft: Offset,
    color: Color,
    rotation: Float = 0f,
    pivot: Offset = center,
    stencil: Boolean = false,
) {
    if (cell <= 0f || text.isEmpty()) return
    val block = Size(cell, cell)
    val neighbours = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    withTransform({ rotate(rotation, pivot) }) {
        text.uppercase().forEachIndexed { index, char ->
            val glyph = PIXEL_GLYPHS[char] ?: return@forEachIndexed
            val originX = topLeft.x + index * (GLYPH_COLS + GLYPH_GAP) * cell
            for (row in 0 until GLYPH_ROWS) {
                val line = glyph[row]
                for (col in 0 until GLYPH_COLS) {
                    if (line[col] != '1') continue
                    if (stencil) {
                        val inside = neighbours.all { (dx, dy) ->
                            val c = col + dx
                            val r = row + dy
                            r in 0 until GLYPH_ROWS && c in 0 until GLYPH_COLS && glyph[r][c] == '1'
                        }
                        if (inside) continue
                    }
                    drawRect(
                        color = color,
                        topLeft = Offset(originX + col * cell, topLeft.y + row * cell),
                        size = block,
                    )
                }
            }
        }
    }
}
