# Plan de diseño — identidad Defalt / DedSec

Qué se quiere conseguir, en qué orden se está haciendo y qué queda. El detalle
de *cómo* está dibujado cada cosa está en [`visual-identity.md`](visual-identity.md).

## 1. Objetivo

Dar a la app una identidad propia —**RAT_**, con la estética de Defalt
y DedSec— sin tocar ni una línea de lógica ni una función. Todo lo que se cambia
es apariencia y textos visibles, y las formas se recrean a mano.

Límites fijos desde el principio:

- **Solo Android.** Nada de escritorio ni iOS.
- **Sin recursos de Ubisoft**: estilo, no material.
- **Los nombres internos quedan como están** (prefs, `bitchord://`, clases, logs).
- **Voz de Defalt**: «cañero sobrio», y en las frases se escribe **RAT**, nunca
  el nombre completo.
- Todo lo que se redibuja respeta **GPL-3.0**.

## 2. Principios de diseño (los que rigen cada decisión)

1. **Ruido, no relleno.** El fondo aporta textura (rejilla, scanlines, marca) y
   nunca compite con el contenido de encima.
2. **Contorno cerrado con hueco**, esquinas a escuadra, sin curvas. Es la
   gramática que hace que todo parezca del mismo dibujo.
3. **Un solo color agresivo.** El rojo aparece donde algo importa y en ningún
   otro sitio.
4. **Nada de skeuomorfismo ni 3D.** Sin sombras blandas ni degradados bonitos
   por sí solos.
5. **El texto visible nunca dice BitChord** ni sugiere funciones que la app no
   tenga.
6. **Si destaca, es un error**: la marca de agua está al 7,5 %, las scanlines al
   1,8 %, la rejilla al 5 %. Se ven si las buscas, no si no.
7. **Traducir rompe la voz**: las notas de Defalt se quedan en su inglés.
8. **Todo se puede apagar.** Si un efecto molesta, tiene interruptor (ver el
   selector de fondos).

## 3. Estado por fases

| Fase | Qué | Estado |
|---|---|---|
| 1–5 | Nombre, paleta, tema, fondos, tarjetas | hechas |
| 6 | Tandas de voz en español e inglés | hecha |
| 7 | Tipografía Chakra Petch + JetBrains Mono | hecha |
| 8 | Menús, diálogos, textos de compartir | hecha |
| 9 | Iconos Material en `Outlined` | hecha |
| 10 | Rediseño de los 28 glifos propios + transporte/aleatorio/repetir | hecha |
| 11 | Corazón de píxeles y ⋮ horizontal | hecha |
| 12 | Marca de agua «DEDSUC» en plantilla | hecha |
| 13 | Las notas de Defalt (emblema + pie de Ajustes) | hecha |
| 14 | Selector de fondos en Ajustes | hecha |
| 15 | Fondo del reproductor (pendiente de decidir) | abierta |
| 16 | Voz restante: Ajustes en detalle, widget, reloj, compartir de Replay, tanda de Fuentes | abierta |

## 4. Pendientes concretos

- **Intensidad de la rejilla** en `DefaltBackdrop.kt`: se puede subir un punto si
  se quiere que se note más.
- **Tarjeta verde del Replay**: si se tiñe hacia el cian de la identidad o se
  deja como está.
- **Tamaño del botón de aleatorio** en la cabecera de álbum: el glifo es ancho y
  queda pequeño dentro del círculo.
- **Reescribir la tanda de Fuentes** (los textos de bibliotecas/estilos), que es
  la que más chirría.
- **Verificar en pantalla** que no queda ningún texto en inglés donde toque
  español.

## 5. Ideas nuevas (propuestas, sin implementar)

1. **Las notas de Defalt como QR de verdad.** En el juego eran códigos QR que
   Defalt dejaba pintados, y se recogían escaneándolos. La propuesta: cada vez
   que aparece una nota, se genera **su QR real** (el de la propia frase, con el
   rombo rojo de Defalt incrustado en el centro) y se enseña junto a la frase.
   *Salvedad honesta*: el escáner del juego reconoce los códigos del propio
   mundo del juego; un QR generado aquí casi con seguridad no cuenta dentro de la
   partida. Sí se puede escanear con la cámara del móvil, que es lo que de verdad
   se ve.
2. **La máscara de Defalt** como sello discreto: en el arranque, al firmar un
   perfil o al final de Ajustes, con el rombo rojo y el borde azul claro de las
   orejas. Pequeña, en una esquina, nunca en el centro.
3. **Glitch de apertura** para la tarjeta: dos desplazamientos de color muy
   breves (cian a la izquierda, rojo a la derecha) al aparecer la nota.
4. **Fondo del reproductor**: bajar la opacidad de la carátula hasta ~18 % y
   teñirla con su paleta propia sobre el fondo de la identidad. Requiere
   comprobar que la barra de progreso y los rótulos siguen legibles.

## 6. Cómo se comprueba cada cambio

- Compilación:
  `cd BitChord && JAVA_HOME=/usr/lib/jvm/java-17-openjdk ./gradlew --configure-on-demand :app:assembleDevDebug -Pbitchord.bundleWear=false`
- Instalación: `adb install -r app/build/outputs/apk/dev/debug/app-dev-arm64-v8a-debug.apk`
  (dispositivo `1215225443001334`; conviven la app de origen y RAT).
- Capturas por `adb exec-out screencap -p`.
- Medición de píxeles con `numpy`/`PIL`: por ejemplo, la rejilla se comprueba
  buscando el periodo de 108 px (36 dp a densidad 3) en la autocorrelación de la
  diferencia entre dos variantes.

## 7. Dónde vive cada cosa

| Qué | Dónde |
|---|---|
| Paleta y fondo | `app/.../ui/theme/DefaltBackdrop.kt` |
| Marca de agua | `app/.../ui/theme/PixelWordmark.kt` |
| Notas de Defalt | `app/.../ui/theme/DefaltQuotes.kt` y `ui/components/DefaltIntercept.kt` |
| Selector de fondos | `app/.../data/settings/AppSettings.kt` (`BackdropStyle`) |
| Iconos propios | `sharedUi/.../ui/icons/BitChordIcons.kt` |
| Fuentes | `app/.../ui/theme/Theme.kt` |
| Documentación | `doc/` (esta carpeta) |

`PROGRESS.md`, el changelog, vive **fuera** del repositorio
(`/home/ctos/myBitchord/PROGRESS.md`) y no se sube a ningún remote.
