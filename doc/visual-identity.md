# Identidad visual — RAT_

Este documento explica **qué se ve** en la app y por qué está dibujado así. Es la
referencia para añadir pantallas nuevas sin que se note que las hizo otra gente.

Lo que aquí se describe solo toca **apariencia**: ni una línea de lógica cambió
por culpa de esto.

---

## 1. El nombre y lo que no se llama así

| Situación | Texto |
|---|---|
| Nombre visible en toda la app | **RAT_** |
| `applicationId` (el paquete que instala Android) | `com.dev.ratplayer` |
| Paquete Kotlin y clases internas | intactos, sin tocar |

La app no lleva en ningún sitio visible el nombre del proyecto de origen. Donde
el código pide un texto que el usuario lee, ese texto es de RAT. Donde pide un
identificador interno (prefs, esquemas de enlaces, clases), se queda como estaba
—cambiarlo rompería las preferencias de quien ya la usa—.

## 2. Paleta

Todo vive en `app/src/main/java/com/music/bitchord/ui/theme/DefaltBackdrop.kt`,
dentro del objeto `DedSec`. No hay colores sueltos por la app: se cogen de ahí.

| Token | Hex | Para qué |
|---|---|---|
| `Deep` | `#04080F` | Suelo de la app: casi negro, con un punto de azul |
| `Royal` | `#00229E` | Azul royal: relleno del emblema |
| `RoyalBright` | `#0037E6` | Borde superior del degradado del emblema |
| `RoyalLight` | `#89BAED` | Contornos claros de la máscara |
| `Red` | `#DC0736` | El único acento agresivo: el rombo |
| `Cyan` | `#00E5FF` | «Sistema» e interacción |
| `Grey` | `#A9B6C5` | Texto secundario |
| `Panel` | `#111820` | Tarjetas y superficies |
| `Border` | `#26323B` | Bordes y separadores |

Regla: **rojo solo donde algo importa**. Si el rojo aparece en cinco sitios, deja
de significar nada.

## 3. Tipografía

| Fuente | Uso | Licencia |
|---|---|---|
| **Chakra Petch** | Titulares, cuerpo, todo lo que se lee largo. Tiene las esquinas cortadas | SIL OFL 1.1 |
| **JetBrains Mono** | Rótulos en mayúsculas: tiempos, cabeceras de sección, chips, notas de Defalt | SIL OFL 1.1 |

Las dos están en `app/src/main/java/com/music/bitchord/ui/theme/Theme.kt` y se
usan desde `MaterialTheme.typography`. Ninguna fuente propietaria: la fuente que
traía la app de origen se retiró por no permitirlo (y con ella se fueron 11 MB).

## 4. Iconos

Familia propia de **32 iconos** en
`sharedUi/src/jvmSharedMain/kotlin/com/music/bitchord/ui/icons/BitChordIcons.kt`,
con los nombres públicos intactos (quien los usa no se entera de que el dibujo
cambió). Se generan desde `/tmp/opencode/icons/gen.py` y se emiten con
`/tmp/opencode/icons/emit.py`; la lámina de referencia es `/tmp/opencode/icons/familia.png`.

### La gramática (esto es lo que hace que sean "de aquí")

1. **Contorno cerrado con un hueco de ~1 unidad.** Nada de rellenos macizos: el
   hueco es lo que separa el trazo y evita que se vea pastoso.
2. **Faceta y píxel.** Las formas se resuelven con diagonales y bloques rectos;
   esquinas vivas, nada de redondeos.
3. **Lápiz 2,2** con `StrokeCap.Square` y `StrokeJoin.Miter`: grosor constante,
   remates y uniones a escuadra.
4. **Sin curvas** en los glifos propios. La excepción es lo que se resuelve por
   píxeles, no por arcos: el **corazón** es un sprite de **7 × 6** celdas de 3
   px —`HeartFilled` va relleno, `Heart` va erosionado un píxel por dentro y se
   dibuja como hueco con `EvenOdd`—.
5. **Todas las formas macizas de un icono van en un solo `path`.** En paths
   separados se ven costuras blancas entre celdas por el antialias.
6. **Aleatorio**: X simétrica con patas horizontales iguales a ambos lados
   (5,4) y puntas de flecha a la derecha; el cruce va sin hueco.
7. **⋮** (tres puntos apilados) y **⋮ horizontal** (`MoreHoriz`) conviven: el
   reproductor, el reproductor apaisado y la cabecera de búsqueda usan el
   horizontal.

Los iconos de sistema que la app no redibuja quedan en **`Outlined`** de Material
(los 328 que se usan), que ya va con el mismo aire de contorno.

## 5. El fondo

`drawDefaltBackdrop` pinta, en este orden, sobre una base `#070B12`:

1. **Halos**: azul royal arriba a la izquierda, cian tenue a la derecha, rojo
   abajo. Son el color: **no se apagan nunca**.
2. **Rejilla** técnica cian al 5 % cada 36 dp, con una línea fuerte cada cinco.
3. **Marca de agua «DEDSUC»**: letras de 5 × 7 bloques dibujadas a mano
   (`PixelWordmark.kt`), en **plantilla recortada** —solo el borde de cada
   letra—, cian al 7,5 %, girada −13°, a tamaño de valla.
4. **Scanlines** de CRT, blancas al 1,8 %, cada 4 dp. Van por encima de la
   marca, para que también la atraviese el tubo.
5. **Viñeta** que cierra los bordes.

En Ajustes → Apariencia → **Fondo** se elige cuánto ruido hay (`BackdropStyle`):

| Opción | Rejilla | Scanlines | Marca |
|---|---|---|---|
| **DEDSEC** | sí | sí | sí |
| **Rejilla** | sí | sí | no |
| **Terminal** | no | sí | sí |
| **Limpio** | no | no | no |

Con el tema claro no hay fondo que texturar, así que la fila se apaga.

## 6. Las notas de Defalt

Dieciséis frases suyas, en su inglés original y sin traducir —una traducida deja
de ser suya—, guardadas en
`app/src/main/java/com/music/bitchord/ui/theme/DefaltQuotes.kt`.

- **Siete toques en el emblema** de Inicio (uno cada ≤ 1,2 s) sueltan una al azar
  en una tarjeta con corchetes de encuadre (`DefaltIntercept.kt`); vibra al salir
  y se va sola a los 9 s.
- **La nota del día** espera al pie de Ajustes, sobre el colofón de versión, en
  monoespaciada y sin rótulo. La elige el **día del año**, no la hora: no baila
  mientras la app está abierta.

## 7. Lo que no se toca (ni se tocará)

- Rutas de `SharedPreferences` (cambiarlas = perder la configuración).
- El esquema de enlaces `bitchord://`.
- `Theme.BitChord` y los recursos del tema.
- `TAG` y todo lo que sale por `Log`.
- `"Unknown Artist"` de `WebDavClient` y `USER_AGENT` de `Genius.kt` (romperían
  compatibilidad con lo que ya funciona).
- **Identidades de red y de archivo**, que son contrato y no rótulo: la
  `User-Agent` (`"RAT"`, `"RAT/vX"`), el `submission_client` de ListenBrainz, la
  etiqueta `VENDOR` de los FLAC, el nombre Bluetooth del reloj y **la carpeta real
  `Music/RAT`** (`DownloadStore.FOLDER`). La app se llama RAT_ pero sigue
  guardando y firmando como RAT: cambiar eso haría que lo ya descargado se
  perdiera o que los servidores dejen de aceptarla.

## 8. Licencias

- Código: **GPL-3.0** (la app es un fork; el código de este proyecto también).
- Fuentes Chakra Petch y JetBrains Mono: **SIL OFL 1.1**, con el aviso de
  atribución en `Theme.kt`.
- Iconos, marca de agua, tarjetas y textos: redibujados a mano para este proyecto.
  No se usa ningún recurso gráfico de Ubisoft: solo su estilo, que es lo que
  manda en esta identidad.
