package com.music.bitchord.ui.theme

import java.util.Calendar

/**
 * DEDSEC // DEFALT — las notas que Defalt dejó por la ciudad.
 *
 * En el primer Watch Dogs las pistas del rastro de Defalt no eran mensajes
 * formales: eran dieciséis notas suyas, con su manera de escribir —las U por
 * los YOU, los números por las letras, los caretos— y la mitad se reían del
 * propio DedSec. Son las que quedan aquí, tal cual, en su idioma original: una
 * nota traducida deja de ser una nota.
 *
 * Este objeto solo guarda el texto y elige cuál toca. Que se lea o no, y dónde,
 * lo decide la interfaz (el toque secreto del emblema, la nota del día).
 */
object DefaltQuotes {

    /**
     * Las dieciséis, en el orden en que aparecen en el juego.
     *
     * Se quedan las burdas: son parte del personaje, y esta es una app personal.
     */
    val all: List<String> = listOf(
        "Don't QQ if U can't find me.",
        "**\\0/* U found one! **\\0/*",
        "Can U find D rest?",
        "BadBoy17? Bad, BOY, 17? Really?",
        "G1gg1L3s... 1L... IL... Get it?",
        "Its all 4 d GIGGLES!",
        "Hey DedSec! \u250C\u2229\u2510(\u25E3_\u25E2)\u250C\u2229\u2510",
        "DedSec can suck my 8=====>",
        "o_O O.O O.o O_\u00F3",
        "U must werk out. >_<",
        "How U finding these? \u0CA0_\u0CA0",
        "(^._.^) U can findz QR codes!",
        "DedSec? More like DedSUC!",
        "U R getting WARMER",
        "Mark IV style mothafucker!",
        "~~(8:> will U be my BFF? 4 realz!",
    )

    /** Una cualquiera, para el emblema. */
    fun random(): String = all.random()

    /**
     * La que toca hoy.
     *
     * Sale del día del año, no de la hora: así la nota no cambia mientras la app
     * está abierta, y mañana es otra. [dayOfYear] se puede pasar en las pruebas;
     * por defecto es hoy.
     */
    fun ofDay(dayOfYear: Int = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)): String =
        all[Math.floorMod(dayOfYear, all.size)]
}
