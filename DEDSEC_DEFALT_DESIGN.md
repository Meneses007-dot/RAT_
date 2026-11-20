# DEDSEC // DEFALT — Guía de estética (documento de diseño)

> Documento de referencia para la Custom Edition de BitChord.
> Basado en investigación sobre *Watch Dogs* (2014), *Watch Dogs 2* (2016), *Watch Dogs: Bad Blood* (DLC, 2014) y la dirección de arte real de Ubisoft.
> **Regla de oro:** todo es *decorativo*. Nada debe insinuar funciones de hacking reales que la app no tiene (sección 5 del brief).

---

## 0. Fuentes consultadas
- **Referencias visuales reales de Juan:** carpeta `DEFALT_REFERENCES/` (6 imágenes): emblema azul, wireframe, line-art, y el *wordmark* pixelado de "Defalt". **Es la fuente principal y manda sobre cualquier suposición.**
- Watch Dogs Wiki (Fandom): artículos *DedSec* y *JB Markowicz / Defalt*; audio-log de DedSec y del "Council of Daves".
- Búsquedas sobre la dirección gráfica de *Watch Dogs 2* (Behance, "Graphic Direction"), el cráneo DedSec en **ASCII/pixel art**, glitch y dithering.
- Referencias sobre Defalt: máscara de rata (guiño a Deadmau5), identidad de DJ, "The Defalt Condition" (Ambrose Theatre), *Bad Blood*.
- Estética ctOS/UI de *Watch Dogs* (1): blanco sobre negro, líneas de datos, cian de "sistema" y rojo de amenaza.

### Colores medidos sobre las referencias (no estimados)
| Elemento | Hex medido |
|---|---|
| Relleno del emblema (azul) | **`#00229E`** (mediana) · brillos hasta `#012FDC` |
| Rojo del rombo de la máscara | **`#DC0736`** |
| Gris del *wordmark* "Defalt" | **`#707070`** |
| Azul claro de los contornos de las orejas | ≈ `#89BAED` |
| Fondo | **`#000000` / `#080B10`** |

---

## 1. Principios rectores
1. **Un terminal vivo.** La interfaz no solo se ve oscura: *habla* al usuario con mensajes técnicos ("SYSTEM ONLINE", "AUDIO STREAM"). El aparato parece estar ejecutando un sistema clandestino.
2. **Coherencia.** Un único lenguaje visual en toda la app: mismos colores, tipografía, motivos y ritmo de animación. Nada de adornos sueltos.
3. **Funcional primero.** La estética nunca sacrifica la legibilidad ni la función. El cian guía la interacción; el azul marca lo Defalt; el rojo enfatiza; nada más.
4. **Original, no copia.** Recreamos el *espíritu* (formas, glitches, tipografías libres), no recursos oficiales de Ubisoft (sección 9 del brief).

---

## 2. DEDSEC — la base (estructura, tecnología, colectivo)
DedSec es un colectivo hacktivista: oscuro, técnico, anónimo, con vocación de "vigilante". Su voz es informativa y algo subversiva (los audio-logs son casi manifiestos).

**Rasgos visuales:**
- **Cráneo pixelado / ASCII** como emblema (boom, el "Byte Off" skull). Se "imprime" línea a línea en las terminales.
- **Glitch, datamosh, dithering, halftone, scanlines**, ruido sutil.
- **Rejillas (grid), líneas de datos, coordenadas, hex**, marcos con esquinas ("corner brackets").
- **Estética de terminal**: fuente monoespaciada, prompts `>`, cursores parpadeantes, texto que se auto-escribe.
- **Etiquetas de sistema**: `CTOS`, `DEDSEC NETWORK`, `ACCESS GRANTED`, `TRACE`, `PACKET`, `SIGNAL`, `%`.
- Anonimato: capuchas, máscaras, siluetas.

**Paleta DedSec:** negro/azul profundo + blanco hueso + **cian eléctrico** (color de "sistema"/interacción). Es ya nuestra paleta base.

---

## 3. DEFALT — el acento (caos, ego, música, juego mental)
Defalt (JB Markowicz) es un **DJ/hacker** con **máscara de rata** y capucha (guiño a Deadmau5), voz distorsionada, arrogante y narcisista. Fue **rechazado** por el consejo de DedSec ("los Daves") por buscar atención, y se volvió un hacker de sombrero negro. En *Watch Dogs* apenas habla; en *Bad Blood* sí, y ahí **maneja los hilos** con engaños y trampas. Su misión, "The Defalt Condition", es un **club (Ambrose Theatre)** con luces de neón y **juegos mentales** (un reto con preguntas; equivocarse cuesta caro).

**Rasgos visuales/sonoros (verificados con las referencias de Juan, `DEFALT_REFERENCES/`):**
- **Máscara de rata geométrica**: dos **orejas hexagonales** inclinadas + una **cara en cuña** que termina en una punta afilada. Contornos finos y líneas convergentes. Capucha negra.
- **La cara está tramada con puntos (halftone / dot-matrix)** que van de **blanco a azul**, con un **rombo rojo** en el centro.
- **Emblema**: la máscara rellena de **azul royal** con **contorno blanco fino** sobre negro. Existe también en **wireframe** (solo líneas) y en line-art negro sobre claro.
- **Wordmark "Defalt"** en **tipografía pixelada / bitmap** (gris `#707070`, o blanco con contorno tipo pegatina).
- **Glitch agresivo**: interferencia, cortes, *chromatic aberration* (separación RGB), "corrupción" de texto e imagen.
- **Ecualizadores / ondas de audio / VU meters**: es un DJ; la música es su escenario.
- **Juegos mentales**: mensajes-acertijo, referencias a "default" (su nombre), firmas tipo `DEFALT WAS HERE`.

**Paleta Defalt (corregida):** negro + **blanco** + **azul royal profundo** (`#00229E`, brillos `#012FDC`) + **rojo `#DC0736`** como único acento agresivo (el rombo). **NO hay naranja**: era una suposición errónea del brief, corregida con las referencias reales.

---

## 4. Lenguaje visual compartido
- **Dualidad:** DedSec = orden/sistema (cian, grid, monoespaciada). Defalt = caos/ego (azul royal, rojo, glitch, máscara).
- **Motion:** transiciones discretas; al **cambiar de pista**, un micro-glitch + un barrido de scanline. Nada que maree (respetar "reducir animación").
- **Superficies:** negro casi puro, tarjetas gris-azuladas, bordes finos, esquinas con "corchetes".
- **Tipografía:** etiquetas y datos en **monoespaciada**; titulares en una fuente **geométrica/condensada** de aire tecnológico. (La actual SF Pro es muy "Apple" y choca con la identidad.)

---

## 5. Sistema de color (propuesta ampliada)
| Rol | Hex | Uso |
|---|---|---|
| Negro profundo | `#080B10` | Fondo principal |
| Panel | `#111820` | Tarjetas/superficies |
| Borde/separador | `#26323B` | Líneas, divisiones |
| Cian eléctrico | `#00E5FF` | Interacción/sistema (DedSec) — ya aplicado |
| Cian tenue | `#00E5FF` al 15–25% | Rellenos, "píldoras", glows |
| Gris tecnológico | `#A9B6C5` | Texto secundario, etiquetas |
| Blanco hueso | `#F2F5F7` | Texto principal (en vez de blanco puro) |
| Azul Defalt | `#00229E` (brillos `#012FDC`) | Elementos Defalt: logo, máscara, superficies "Defalt" |
| Rojo Defalt | `#DC0736` | Único acento agresivo: énfasis, "hijack", error, el rombo |
| Rojo tenue | `#DC0736` al 12–15% | Fondos de énfasis puntuales |
| Gris wordmark | `#707070` | Logotipo "Defalt" en tipografía pixelada |

**Contraste (regla 15, WCAG AA ≥ 4.5:1):** cian, azul y rojo sobre negro cumplen de sobra; blanco hueso mejora la fatiga visual frente al blanco puro; el gris `#A9B6C5` es el mínimo para texto pequeño. **Cuidado:** cian (`#00E5FF`) y azul Defalt (`#00229E`) son ambos azules → no usarlos juntos como acento en la misma zona; el azul Defalt es para zonas "Defalt" (logo, splash, máscara), no para interacción.

---

## 6. Catálogo de motivos y "flavor text" (solo decorativo)
Etiquetas/estados (inspirados en terminales y en el brief):
`DEDSEC NETWORK` · `AUDIO STREAM` · `SYSTEM ONLINE` · `CTOS LINK` · `SIGNAL OK` · `TRACE ROUTE` · `PACKET LOSS 0%` · `BUFFERING` · `DECRYPTING` · `ACCESS GRANTED` · `NODE 07` · `UPLINK` ·
Defalt: `DEFALT WAS HERE` · `SIGNAL HIJACKED` · `DEFAULT?` · `REWIRE` · `REMIX` · `VOL //` · `EGO.SYS`

Iconos: cráneo pixelado (DedSec), máscara de rata (Defalt), waveforms, corchetes de esquina, cruces técnicas, glitch bars.

### 6.1 Emblema Defalt — recreación vectorial (trazada de las referencias)
- Trazado desde `DEFALT_REFERENCES/Imagen pegada.png` con un rastreador propio (contorno de Moore + simplificación RDP): **23 vértices**, caja 690×797 px (ratio ≈ 0,87), nítido a cualquier tamaño.
- Estructura: oreja hexagonal izquierda → puente superior del rostro → oreja hexagonal derecha → cuña del rostro → **punta inferior afilada** en el centro.
- Vértices normalizados (x,y en 0..100), en orden:
  `(11.17,0) (12.92,0.13) (33.5,6.02) (36.39,20.08) (29.36,29.86) (57.97,29.86) (50.69,19.7) (53.58,6.15) (74.53,0.25) (75.53,0.5) (86.57,21.46) (84.57,24.84) (64.37,34.63) (69.39,48.31) (59.47,54.83) (43.54,100) (42.79,99) (27.1,54.45) (17.57,48.56) (22.33,34.88) (2.13,25.09) (0,22.08) (0.63,19.95)`
- Relleno `#00229E`, contorno blanco fino. `pathData` guardado en `/tmp/opencode/defalt-path.txt` (se rehará al crear los recursos definitivos).

---

## 7. Mapa concreto: dónde aplicar en BitChord
| Pantalla / zona | Archivo base | Idea DEDSEC // DEFALT |
|---|---|---|
| **Tema global** | `app/.../ui/theme/Theme.kt` | Paleta (hecho) + tipografía técnica + `scrim`/superficies. |
| **Onboarding** | `app/.../ui/onboarding/OnboardingScreen.kt` | ✅ Hecho con paletas DedSec/Defalt. **Corregir el naranja → azul Defalt `#00229E`.** |
| **Barra inferior / nav** | `app/.../ui/components/GlassNavBar.kt`, `FloatingTabBar.kt` | Estilo consola: borde cian fino, indicador tipo "cursor"; cian ya activo. |
| **Mini-reproductor** | `app/.../ui/components/MiniPlayer.kt` | Barra de progreso cian + etiqueta `AUDIO STREAM`; glitch mínimo al cambiar pista. |
| **Pantalla de reproducción** | `sharedUi/.../ui/player/*` (`NowPlayingScreen`, `PlayerControls`, `ThinSlider`…) | Carátula destacada con marco técnico; barras cian; waveform; etiquetas `SYSTEM ONLINE`; al pulsar play, micro-glitch. Aquí brilla Defalt: máscara + VU meters. |
| **Fondo del reproductor** | `sharedUi/.../ui/player/MeshGradient.kt` | Paletas `MeshPalette` hacia cian/azul Profundo (no morado). `FallbackColors` de DedSec. |
| **Pantallas** | `sharedUi/.../ui/screens/*`, `app/.../ui/screens/*` | Encabezados tipo terminal (mono + corchetes), grid sutil, etiquetas de sección en mayúsculas con líneas. |
| **Icono de la app** | `app/src/main/res/mipmap-*` | Cráneo pixelado (DedSec) o máscara de rata (Defalt). |
| **Nombre** | `values*/strings.xml` + build config | "DEDSEC // DEFALT" (regla 13). |
| **Sonido/feedback** | — | Opcional: sonidos cortos de "glitch"/beep en acciones (requiere permisos/recursos; decidir con Juan). |

---

## 8. Qué evitar
- No usar logos/marcas oficiales de *Watch Dogs* (Ubisoft). Recrear formas originales.
- No sugerir "hacking" real ni funciones inexistentes.
- No sacrificar legibilidad ni saturar de glitch (debe cansar poco).
- No romper funciones ni la lógica (principios 1–3 del brief).

---

## 9. Orden de aplicación sugerido (Fase 6)
1. **Onboarding** (lo primero que se ve) → quitar morado, meter DedSec.
2. **Barra inferior + mini-reproductor** → "consola".
3. **Pantalla de reproducción** → el corazón (carátula, barras, etiquetas).
4. **Pantallas** (Inicio/Biblioteca/Buscar) → encabezados y grids.
5. **Icono + nombre** (regla 13).
6. **Pulido**: animaciones, glitches, detalles Defalt.
