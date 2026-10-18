package com.music.bitchord.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.music.bitchord.R

// Defalt's signature red. No longer the primary accent, but kept for the
// spots (Replay's rank badge) that want that specific red regardless of theme.
val AccentRed = Color(0xFFDC0736)

// DEDSEC // DEFALT — Custom Edition.
// Paleta personalizada: cian eléctrico como color de interacción, naranja Defalt
// para error/énfasis, y negros/grises técnicos para fondos y superficies.
// Referencia del proyecto: PROGRESS.md / DEDSEC_DEFALT_BitChord_brief.md.
private val DarkColors = darkColorScheme(
    primary = Color(0xFF00E5FF),          // Cian eléctrico (botones, controles activos)
    onPrimary = Color(0xFF080B10),        // Texto sobre cian (oscuro = contraste)
    background = Color(0xFF080B10),       // Negro profundo (fondo principal)
    onBackground = Color(0xFFFFFFFF),     // Texto principal
    surface = Color(0xFF111820),          // Tarjetas y paneles
    onSurface = Color(0xFFFFFFFF),        // Texto sobre tarjetas
    surfaceVariant = Color(0xFF26323B),   // Separadores y bordes
    onSurfaceVariant = Color(0xFFA9B6C5), // Gris tecnológico (texto secundario)
    outline = Color(0xFF26323B),          // Divisiones
    error = Color(0xFFFF4D2E),            // Naranja Defalt (alertas/énfasis)
)

private val LightColors = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF7F7F9),
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFF2F2F7),
    onSurfaceVariant = Color(0xFF6E6E73),
    outline = Color(0xFFE5E5EA),
)

/**
 * RAT // PLAYER — Custom Edition: la voz tipográfica de la identidad.
 *
 * Dos familias, una por papel (ver DEDSEC_DEFALT_DESIGN.md):
 *
 * - [ChakraPetch] — titulares, cuerpo y botones. Cuadrada, con las esquinas
 *   cortadas: la cara de las interfaces técnicas que persigue esta edición, y
 *   la que sustituye a **SF Pro Display** (la de Apple Music) con la que venía
 *   la app.
 * - [JetBrainsMono] — rótulos, datos y todo lo que va en mayúsculas: tiempos,
 *   cabeceras de sección, chips, etiquetas de la barra. Es el "terminal".
 *
 * Ambas son **SIL Open Font License 1.1**: se pueden incluir y redistribuir con
 * la app, y esto hace de aviso de atribución. SF Pro no lo permitía; se retiró
 * junto con ella (y de paso se fueron 11 MB del APK).
 */
val ChakraPetch = FontFamily(
    Font(R.font.chakra_petch_regular, FontWeight.W400),
    Font(R.font.chakra_petch_medium, FontWeight.W500),
    Font(R.font.chakra_petch_semibold, FontWeight.W600),
    Font(R.font.chakra_petch_bold, FontWeight.W700),
)

val JetBrainsMono = FontFamily(
    Font(R.font.jetbrains_mono_regular, FontWeight.W400),
    Font(R.font.jetbrains_mono_medium, FontWeight.W500),
    Font(R.font.jetbrains_mono_semibold, FontWeight.W600),
    Font(R.font.jetbrains_mono_bold, FontWeight.W700),
)

/**
 * El estilo de las etiquetas de la barra de pestañas.
 *
 * Monoespaciado como el resto de los rótulos, pero **más pequeño y sin
 * interletraje**: en monoespaciada una palabra como "Biblioteca" no entra en un
 * cuarto de barra a los 11sp de `labelSmall` con su tracking, y salía cortada.
 * La barra mantiene la voz de terminal, solo que apretada.
 */
val TabLabelStyle = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.W500,
    fontSize = 10.sp,
)

// Heavy, tight typography — the backbone of the Apple Music look.
// Ahora con Chakra Petch, que no lleva el apretado negativo de SF Pro: sus
// trazos son rectos y con tracking negativo se pegan unos a otros. Cero, y los
// rótulos (en monoespaciada) un pelo abiertos para que se lean como etiquetas.
private val BitChordTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.W700, fontSize = 34.sp),
    headlineLarge = TextStyle(fontWeight = FontWeight.W700, fontSize = 30.sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.W700, fontSize = 22.sp),
    titleLarge = TextStyle(fontWeight = FontWeight.W600, fontSize = 20.sp),
    titleMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 16.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.W400, fontSize = 16.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.W400, fontSize = 14.sp),
    labelMedium = TextStyle(fontWeight = FontWeight.W500, fontSize = 12.sp, letterSpacing = 0.6.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.W500, fontSize = 11.sp, letterSpacing = 0.8.sp),
).withFamily(ChakraPetch, JetBrainsMono)

/**
 * Applies [family] to every style in the scale, so nothing is left on Roboto,
 * and [mono] to the two label tiers — the ones that carry data rather than prose.
 */
private fun Typography.withFamily(family: FontFamily, mono: FontFamily) = Typography(
    displayLarge = displayLarge.copy(fontFamily = family),
    displayMedium = displayMedium.copy(fontFamily = family),
    displaySmall = displaySmall.copy(fontFamily = family),
    headlineLarge = headlineLarge.copy(fontFamily = family),
    headlineMedium = headlineMedium.copy(fontFamily = family),
    headlineSmall = headlineSmall.copy(fontFamily = family),
    titleLarge = titleLarge.copy(fontFamily = family),
    titleMedium = titleMedium.copy(fontFamily = family),
    titleSmall = titleSmall.copy(fontFamily = family),
    bodyLarge = bodyLarge.copy(fontFamily = family),
    bodyMedium = bodyMedium.copy(fontFamily = family),
    bodySmall = bodySmall.copy(fontFamily = family),
    labelLarge = labelLarge.copy(fontFamily = family),
    labelMedium = labelMedium.copy(fontFamily = mono),
    labelSmall = labelSmall.copy(fontFamily = mono),
)

@Composable
fun BitChordTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = BitChordTypography,
        content = content,
    )
}

/**
 * Draws the status and navigation bar glyphs dark or light.
 *
 * `enableEdgeToEdge()` decides this from the *system* dark-mode setting, which
 * is the wrong input the moment the in-app theme disagrees with it: Light theme
 * on a phone in dark mode left white icons on a white bar, invisible. The bars
 * have to follow the content the app is actually painting. The player supplies
 * its own stable light-icon value and paints contrast behind it; every other
 * surface follows the theme. Hence a parameter rather than reading it here.
 */
@Composable
fun SystemBarIcons(dark: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) return
    val window = findWindow(view) ?: return
    SideEffect {
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = dark
            isAppearanceLightNavigationBars = dark
        }
    }
}

