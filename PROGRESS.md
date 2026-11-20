# PROGRESS — DEDSEC // DEFALT — Custom Edition de BitChord

> Registro de progreso del proyecto (regla 12 del brief). No se sube a ningún remoto.
> Actualizar al terminar cada fase, para que cualquier agente pueda retomar el trabajo.

- **Usuario:** Juan (Ingeniería de Sistemas, CachyOS). Comunicación en español.
- **Objetivo:** personalizar la apariencia de BitChord con estética Watch Dogs (DedSec + Defalt), sin alterar la lógica ni las funciones.
- **Repositorio Git real:** `/home/ctos/myBitchord/BitChord`
- **Rama de trabajo:** `dedsec-defalt` (creada desde `main`, limpia).
- **Fecha de inicio:** 2026-10-17

---

## Estado actual

- **Fase en curso:** Fase 6 — Rediseño + **rebranding**. Identidad Defalt corregida con las referencias reales (**azul + rojo, sin naranja**) y **emblema recreado en vector**.
- **Fases 1–5 completadas:** SÍ ✅ (Fase 5 = paleta global, commit `8a84d3fb`).
- **Fase 6 cambio 1:** onboarding DedSec/Defalt ✅ (commit `fd727fd0`) y **corregido a azul** ✅.
- **Rebranding visible:** nombre `RAT // PLAYER` + `applicationId` propio + icono/logo nuevos ✅.
- **Textos:** **0 rastros de `BitChord`** en los 19 idiomas ni en el código visible ✅; voz de Defalt **en cuentagotas** (tras descartar el primer intento por excesivo) y español sin mezcla de idiomas ✅ (sesión 2026-10-18).
- **Referencias:** `DEFALT_REFERENCES/` (aportadas por Juan el 2026-10-18).
- **Dispositivo (sección 11, verificado por adb):** Infinix X6851B (Note 40 Pro+) · Android 15 (API 35) · arm64-v8a · parche 2026-08-01. La app original (`com.music.bitchord`) está instalada e intacta; la nuestra es **`com.dev.ratplayer`**.
- **Capturas:** `/tmp/opencode/` (onboarding con la voz: `40-voice-onboarding1.png`, `44-voice-onboarding5.png`; home `45-voice-home.png`; ajustes `46-account.png`).

---

## Sesión 2026-10-18 — Referencias reales, emblema vectorial y rebranding 🐀

### 1. Corrección de la identidad (el naranja no existía)
- **Juan aportó `DEFALT_REFERENCES/`** (6 imágenes): emblema azul, wireframe, line-art y *wordmark* pixelado "Defalt".
- **Corrección:** el **naranja** de Defalt era una **suposición errónea del brief**. Colores **medidos con código** sobre las referencias:
  - **Azul royal** `#002BCF` (arriba) → **degradado a negro** en la punta. Mediana del emblema `#00229E`.
  - **Rojo** `#DC0736` — el rombo de la máscara (único acento agresivo).
  - **Gris** `#707070` — *wordmark* "Defalt" pixelado.
  - **Negro** + **blanco** de fondo y líneas.
- `DEDSEC_DEFALT_DESIGN.md` **actualizado** (secciones 0, 1, 3, 4, 5, 6, 7) y onboarding corregido (azul `#00229E` + rojo `#DC0736`).

### 2. Emblema recreado en vector (nada copiado, todo trazado)
- **Método final (2ª versión, la buena):** el emblema se reconstruye con **dos figuras**:
  1. el **contorno exterior** (22 vértices) relleno con el **degradado azul → negro**;
  2. la **red de líneas blancas**: ese mismo contorno **más los 9 huecos** que encierra, con `android:fillType="evenOdd"`.
  Así las líneas caen **exactamente** donde están en la referencia, sin inventar ni engordar nada.
- **Cómo se obtuvo:** umbral de "blanco" sobre la imagen (canal mínimo > 150), se traza el contorno exterior con Moore + RDP, y los **huecos** se sacan buscando las regiones *no blancas* que **no tocan el borde** de la imagen (es decir, las encerradas por la red). Sale **1 contorno + 9 huecos = 65 vértices**. Script de referencia: se reproduce con numpy + PIL (sin `potrace` ni `inkscape`).
- **Lo que se descubrió** (importante para futuras ediciones):
  - El emblema son **9 paneles azules** separados por líneas blancas: oreja izq., oreja der., sus 2 solapas, frente, 2 mejillas y **2 paneles de nariz** (la nariz son 3 franjas: sliver izq., centro y sliver der.).
  - La nariz lleva **4 líneas blancas rectas** que convergen en la punta: desde x=32.75 / 37.64 / 49.18 / 53.95 (y_n=69.5) hasta **x=43.41, y_n=100**.
  - El relleno es un **degradado vertical azul `#0037E6` → negro `#04080F`**; la punta se funde con el fondo.
- **⚠️ Dos intentos fallidos que conviene no repetir** (menos mal que Juan los vio en pantalla):
  1. Trazar **solo los paneles azules** por color: al ser un degradado que se apaga, el umbral cortaba el relleno a media nariz y **inventaba una línea recta horizontal** en el pico, y **cortaba la esquina superior de las orejas** (sin borde).
  2. Dibujar las líneas **como contorno (`stroke`) de cada panel**: los paneles quedan separados ~0,9 u por el antialiasing, así que o salían **huecos negros entre paneles** o había que engordar la línea el doble que la referencia. Descartado.
- **Recursos creados** (vector, sin PNG):
  - `app/src/main/res/drawable/ic_launcher_foreground.xml` (viewPort 108, zona segura).
  - `app/src/main/res/drawable/ic_launcher_background.xml` (degradado azul-negro).
  - `app/src/main/res/drawable/ic_logo.xml` (viewPort 100×115.5) y **3 copias más**: `sharedUi/src/commonMain/composeResources/drawable/`, `desktopUi/…`, `wearos/src/main/res/drawable/`. Las de Compose Multiplatform usan **relleno plano `#1B49FF`** (su lector de vectores **no admite degradados por aapt**).
  - `ic_notification_logo.xml` (silueta blanca).

### 3. Rebranding visible
- **Nombre visible:** `RAT // PLAYER` en los **19 idiomas** (`app_name`).
- **`applicationId`:** `com.dev.ratplayer` (sabor *dev*, el de pruebas) y `com.ratplayer.app` (por defecto). El **paquete interno Kotlin sigue siendo `com.music.bitchord`** (decisión de Juan: no se toca; es invisible y evita un refactor de cientos de archivos).
- **Iconos y logo** sustituidos en los 4 módulos. Desinstalada la app vieja de prueba (`com.dev.bitchord`).
- **Ajustes de código obligados** por el cambio de proporción del logo (antes apaisado 730×484, ahora vertical 86.57:100): `HomeScreen.kt` (`LOGO_ASPECT`), `ReplayCard.kt`, `ReplayStories.kt` (y su texto fijo "BitChord" → `R.string.app_name`), `ReplayPoster.kt` (`LOGO_W`/`LOGO_H`), `OnboardingScreen.kt` (tamaño).
- **Validado en el Infinix:** lanzador (icono `RAT // PLAYER` completo, sin cortes), splash (`33-splash5.png`) y onboarding (`34-onboarding6.png`).
- **Carpeta de descargas renombrada** (decidido por Juan): `DownloadStore.FOLDER` `"BitChord"` → `"RAT"`, así que las descargas van a **`Music/RAT`**. También se cambió la etiqueta visible en los 19 idiomas y el rótulo de Ajustes (que estaban *hardcodeados* como `"Music/BitChord"` en `SettingsSheet.kt`). Las dos consultas a MediaStore que buscaban la carpeta por nombre (`"%BitChord%"`) se ajustaron a **`"%/RAT/%"`** para que no confundan otras carpetas que contengan "RAT".

### 4. Voz de Defalt en los textos (español + inglés) 🐀🗣️
- **Fuente de la voz (investigada, no inventada):** diálogos reales de Defalt (*Watch Dogs* y *Bad Blood*), recogidos de la Villains Wiki / Watch Dogs Wiki. Patrón sacado de sus líneas: **showman/DJ** (`I am Defalt. Welcome to my party.`, `For those of you playing at home…`), **grandilocuente/blasfemo** (`I am God from the Machine`, `Rats in the Walls`), **burlón y vengativo** (`Did it make you feel powerful, Ray?`), **despedida de DJ** (`So long, Ray. Bye bye! Adios!`). Su alias en el juego es **`~~(8:>`**.
- **Primer intento (descartado) — y por qué:** se aplicó la voz a **182 cadenas** del español y 89 del inglés, con **apodos** («ratón»), **pseudo-terminal decorativo** (`> biblioteca_vacía // nada que saquear todavía`), **glitch** (`[ERR]`, `[SIN RED]`), la firma `~~(8:>` y **amenaza en microcopy trivial** («Yo no borro nada», «Sí, te vigilo»). **Juan lo vio en pantalla y lo llamó "cringe", con razón.** Diagnóstico: **el problema no era la voz, era la dosis y el disfraz** — la teatralidad de Defalt está en *presentarse*, no en cada botón; el pseudo-terminal parece una skin barata; la amenaza en sitios neutros rompe la confianza y estorba. **El marco del "asalto" ya lo lleva el chrome visual** (`> DEDSEC_NETWORK // INIT`, `> DEFALT // TRACE_ACTIVE`), que no necesita que el texto lo repita.
- **Ajuste (lo que hay ahora):** **se restauró la interfaz a texto limpio y funcional** y la voz de Defalt queda **en cuentagotas**, solo donde aterriza (seis frases):
  - `onboarding_title` → «Millones de canciones en streaming. **Sin pedir permiso.**» / «Millions of songs streaming. **No permission asked.**»
  - `onboarding_connect_body` → «Tu biblioteca, tus listas, tus mixes. **Un servidor prestado y una fiesta.**» / «… A borrowed server and a party.»
  - `listen_together_share_text` → «Entra en la fiesta. Código: %1$s» / «Join the party. Code: %1$s».
  - `library_empty` → «Aquí todavía no hay nada. **Solo polvo y bits.**» / «Nothing here yet. Just dust and bits.»
  - `no_results` → «Nada. **Ni un byte.**» / «Nothing. Not a byte.»
  - `source_unrecognised` → «**Ese enlace no me dice nada.**» / «That link tells me nothing.»
  - Nada de apodos, glitch decorativo, pseudo-terminal ni amenazas en textos neutros.
- **Método (repetible):** diccionario `nombre → texto nuevo` (`/tmp/opencode/voice_v2.py`) que parte del **original**, reaplica la marca `RAT`, cambia **solo el contenido** (nombres y estructura intactos) y **valida marcadores** (`%1$s`, `%2$d`, `%%`) antes de escribir. Verificación: `name=` idénticos al original y 0 diferencias de marcadores.
- **Bloque A — limpieza de marca (19 idiomas):** `BitChord` → `RAT` (432 apariciones) + `bitchord %1$s` → `RAT %1$s`. **0 `BitChord` visibles.**
- **Traducción del tramo en inglés del fichero español** (Android Auto, Cast, ecualizador, traducción, enlaces, pipeline…): el español ya no mezcla idiomas. Es un arreglo real, sin voz.
- **Marca `RAT` en el código visible (Bloque D):** `LyricsShareCard.kt` (`BRAND`), `ReplayPoster.kt` (`drawText` y medida), `WatchInstaller.kt` (`getDeviceName`, lo que ve el reloj), `ListenBrainzManager.kt` (`submission_client` ×2), `FlacTagger.kt` (`VENDOR` + test `MediaTaggerTest`), `SourceFormats.kt` (motivo de error + test `SourceFormatsTest`), y los textos de `sharedUi` (`default_replay_holder`) y `wearos` (3 cadenas).
- **Fuera del rebranding (a propósito):** `Genius.kt` (`USER_AGENT`, va por la red pero no se ve) y `DiscordApplication.kt` (el nombre está registrado en el portal de Discord y **debe** seguir siendo "BitChord"). Nota: la app de TV (`tv/`) tiene su propio label/diálogo y la de escritorio (`desktopApp/`) su marca; **fuera de alcance** (Juan: solo Android).
- **Lo que NO se toca (a propósito, y por qué):** marca interna y funcional — rutas de `sharedpref` (`bitchord_auth.xml`…), esquema de enlaces `bitchord://`, `Theme.BitChord`, capacidad de Wear `bitchord_phone`, `TAG`/`Log` — cambiarlos rompería datos, enlaces o el reloj, y **no se ven**; y términos técnicos/estándar (Wi-Fi, Bluetooth, códecs, campos del pipeline), donde reescribir es cambiar el significado.
- **Compilado y probado:** `BUILD SUCCESSFUL` + `MediaTaggerTest` y `SourceFormatsTest` en verde. Capturas (del primer intento, ya descartado): `40-voice-onboarding1.png`, `44-voice-onboarding5.png`, `45-voice-home.png`, `46-account.png`.
- **Versión de escritorio (`desktopApp`):** **fuera de alcance** por ahora (decisión de Juan: solo Android).

### 5. Pendiente en esta fase
- [x] ~~**448 textos** todavía decían "BitChord"~~ ✅ **Resuelto**: **0** rastros de `BitChord` en los 19 `strings.xml` (Bloque A) y **español + inglés reescritos con la voz de Defalt** (Bloques B y C).
- [x] **"BitChord" visible en el código** (no `strings.xml`) ✅ **Resuelto**: sustituido por `RAT` en todo lo que se ve (ver **Fase 7**).
  - Sigue en `BitChord` **a propósito** (invisible o fuera de alcance): `TAG`/`Log.d("BitChord", …)`, el paquete Kotlin, claves de recursos y `sharedpref`, el esquema `bitchord://`, `Theme.BitChord`, el `SESSION_ID` de MediaSession, el puente JS de Spotify, nombres de assets del reloj (`BitChordWear.apk`, `CN=BitChord`) y el `applicationId` del reloj (todo esto **no se ve**).
  - `desktopApp/` y `tv/` — **fuera de alcance** por ahora (solo Android).
- Los **13 estilos de icono** que trae la app (`AppIcon.kt`: *liquid/pixel/samsung glass* × claro/oscuro/transparente) **siguen con el diseño rojo original** (son PNG en `mipmap-*/`). **Decisión de Juan: dejarlo para el final**.

---

## Punto de parada — 2026-10-17 (fin de sesión) 🛑

- **Todo guardado:** `git status` limpio; rama `dedsec-defalt` con los commits `8a84d3fb` (paleta) y `fd727fd0` (onboarding). Nada subido a ningún remoto.
- **Hecho hasta ahora:** Fases 1–5 completas + Fase 6 cambio 1 (onboarding). Validado en el Infinix.
- **En el teléfono:** la app de prueba `com.dev.bitchord` sigue instalada (junto a la original `com.music.bitchord`, intacta). Se puede desinstalar cuando queramos con `adb uninstall com.dev.bitchord`.
- **Plan de la próxima sesión (lo pidió Juan):** borrar **todo rastro de BitChord** y quedarnos con una identidad propia — *"nuestro propio hacker reproductor de la rata"* (Defalt). Es decir:
  1. **Renombrar la app:** nombre visible propio + `applicationId` propio (regla 13).
  2. **Icono propio:** máscara de rata / cráneo pixelado.
  3. **Sustituir la marca** "BitChord" en textos, logo del onboarding y splash.
  4. **Revisar licencia:** el proyecto es **GPL-3.0** (ver `LICENSE`/`README`); al ser uso personal no hay problema, pero si algún día se distribuye hay que respetar la licencia y **no** usar recursos con copyright de Ubisoft (crear formas propias).
  5. Decidir si se **quita el splash rojo** actual y se sustituye por uno DEDSEC/DEFALT.
- **Comando de compilación (recordatorio):**
  `cd /home/ctos/myBitchord/BitChord && JAVA_HOME=/usr/lib/jvm/java-17-openjdk ./gradlew --configure-on-demand :app:assembleDevDebug -Pbitchord.bundleWear=false`

---

## Guía de estética (investigación) 📚

- **Documento completo:** `DEDSEC_DEFALT_DESIGN.md` (en la raíz del workspace, junto a este archivo).
- Resume la identidad de **DedSec** (cráneo pixelado/ASCII, glitch, grid, terminal, cian) y de **Defalt** (máscara de rata, DJ, neón de club, glitches agresivos, juegos mentales, **naranja** `#FF4D2E`), la paleta ampliada, motivos, "flavor text" decorativo y el mapa de dónde aplicarlo en BitChord.
- **Fuentes:** Watch Dogs Wiki (DedSec, Defalt/JB Markowicz, audio-logs), dirección gráfica de WD2 (Behance), referencias de Defalt (máscara/DJ/Deadmau5, "The Defalt Condition", *Bad Blood*), UI ctOS de WD1.

---

## Fase 1 — Inspección del proyecto ✅

### Identidad del proyecto
- **Nombre:** BitChord — "Aesthetic YouTube Music Client" (cliente de YouTube Music).
- **Licencia:** GNU GPL v3 (`LICENSE`). Uso personal; no redistribuir (sección 9 del brief).
- **Remoto:** `https://github.com/kushagrasinghx/BitChord.git`

### Lenguaje y framework
- **Lenguaje:** Kotlin.
- **UI:** Jetpack Compose con **Material 3**.
- **Compilación:** **Gradle (Kotlin DSL)** con envoltorio `./gradlew` (**Gradle 8.11.1**).
- **AGP:** 8.10.1 · **Kotlin:** 2.3.20 · **Compose Multiplatform:** 1.10.3.
- **Módulos:** `:app` (teléfono), `:tv`, `:wearos`, `:shared`, `:sharedUi`, `:desktopUi`, `:desktopApp`.
  - `:app`/`:tv`/`:wearos` solo se incluyen si hay Android SDK detectado.

### Datos clave del módulo `:app`
- **applicationId / namespace:** `com.music.bitchord`
- **minSdk:** 26 · **targetSdk:** 36 · **compileSdk:** 37
- **versionName:** `1.8.1` (sufijo `beta1` en debug/release)
- **Sabores ya existentes:** `dev` (`com.dev.bitchord`, "BitChord Dev") y `prod`.
- **Tipos de build:** `debug`, `release`, `benchmark`.
- **Ya sabe convivir dos versiones** → reutilizable para la regla 13.
- **Código nativo:** C++ (`app/src/main/cpp/`, CMake 3.22.1) + `.so` precompiladas en `jniLibs`.

### Código de la interfaz (para la Fase 4)
- `app/src/main/java/com/music/bitchord/ui/` → `theme/`, `components/`, `screens/`, `player/`, `onboarding/`, `replay/`, `performance/`, `utils/`, `MainViewModel.kt`.
- Recursos en `app/src/main/res/` (`drawable/`, `font/`, `layout/`, `mipmap-*`, `values/`, `values-night/`, traducciones `values-es/`…).
- `shared/` y `sharedUi/` son librerías compartidas — revisar en Fase 4.

---

## Fase 2 — Preparación del entorno ✅

### Instalado por Juan (con `sudo`, confirmado por él)
- `jdk17-openjdk` (`17.0.20.1`) — el proyecto compila el Android con JDK 17 (lo confirma `.github/workflows/android.yml`).
- `android-tools` (`37.0.0p1`) — aporta `adb`.
- `sudo pacman -Sc` → caché de pacman pasó de 21 GB a casi 0. **Disco: de 8,6 GB libres a 21 GB.**

### Instalado por el agente (sin `sudo`, en `~/Android/Sdk`)
- **Android command-line tools** (build 15859902) en `~/Android/Sdk/cmdline-tools/latest`.
- **platform-tools** (`adb`) · **platforms;android-37.0** · **build-tools;37.0.0** · **cmake;3.22.1**.
- Licencias de Android aceptadas.
- `local.properties` creado en la raíz del repo con `sdk.dir=/home/ctos/Android/Sdk` (está en `.gitignore`, no se sube).

### Cómo compilar (comando clave)
```bash
cd /home/ctos/myBitchord/BitChord
JAVA_HOME=/usr/lib/jvm/java-17-openjdk ./gradlew --configure-on-demand :app:assembleDevDebug -Pbitchord.bundleWear=false
```
- `--configure-on-demand`: **obligatorio**, porque el módulo `:desktopApp` exige un **Java 21** que no está instalado; así Gradle solo configura lo del móvil.
- `-Pbitchord.bundleWear=false`: no empaqueta la app del reloj (iteración más rápida).

### Pendiente de la Fase 2 detectado (resuelto en Fase 3)
- **NDK:** el módulo `:app` tiene código C++, así que el build pide un NDK. AGP lo descargó solo: `27.0.12077973`.

---

## Fase 3 — Compilación original ✅

- **Resultado:** `BUILD SUCCESSFUL in 7m 27s` (código de salida 0). La app original compila sin tocar código.
- **APKs generadas** en `app/build/outputs/apk/dev/debug/`:
  - `app-dev-arm64-v8a-debug.apk` (92 MB) — **la que usaremos en el Infinix** (arm64).
  - `app-dev-armeabi-v7a-debug.apk` (83 MB)
  - `app-dev-x86_64-debug.apk` (98 MB)
  - `app-dev-universal-debug.apk` (191 MB)
- **Producto:** sabor `dev`, paquete `com.dev.bitchord`, nombre "BitChord Dev".

### Comando exacto de compilación
```bash
cd /home/ctos/myBitchord/BitChord
JAVA_HOME=/usr/lib/jvm/java-17-openjdk ./gradlew --configure-on-demand \
  :app:assembleDevDebug -Pbitchord.bundleWear=false
```

### Apaño de entorno necesario (NO es cambio de código)
- Google renombró la plataforma a `platforms;android-37.0` (versión menor) y AGP 8.10.1 busca `android-37`.
- Solución local: enlace simbólico `~/Android/Sdk/platforms/android-37 -> android-37.0`.
  - Si algún día se reinstala el SDK, recrearlo con:
    `ln -sfn android-37.0 ~/Android/Sdk/platforms/android-37`

---

## Fase 4 — Identificación de la interfaz ✅

Dato clave: la app usa **Material 3** y consume `MaterialTheme.colorScheme` en **48 archivos** (p. ej. `primary` aparece **174 veces**). Es decir, **cambiando la paleta en un solo sitio cambia el color de casi toda la app**.

| Zona | Archivo(s) | Qué controla |
|---|---|---|
| **Paleta + tipografía (nº1)** | `app/src/main/java/com/music/bitchord/ui/theme/Theme.kt` | `DarkColors` (l.25–35) y `LightColors` (l.37–47) = las ~9 variables de color de toda la app. `SFProDisplay` (l.54–60) + `BitChordTypography` (l.63–73) = tipografía. `BitChordTheme` (l.94) aplica todo. |
| **Fondos con degradado (mesh)** | `sharedUi/.../ui/player/MeshGradient.kt` | Los “blobs” de color detrás del reproductor. `FallbackColors` (l.51) y `WelcomeMeshPalettes` (l.228). |
| **Pantalla de reproducción** | `sharedUi/.../ui/player/` → `NowPlayingScreen.kt`, `PlayerControls.kt`, `PlayerDock.kt`, `PlayerDrawer.kt`, `PlayerQueue.kt`, `PlayerLyrics.kt`, `SyncedLyricsPanel.kt`, `ThinSlider.kt` (barra de progreso) | Todo el “Now Playing”. Parte Android en `app/.../ui/player/AndroidPlayerHost.kt` y `CanvasArtworkPlayer.kt`. |
| **Pantallas principales** | `sharedUi/.../ui/screens/` → `HomeScreen`, `LibraryScreen`, `SearchScreen`, `ExploreScreen` | Inicio, Biblioteca, Buscar, Explorar. |
| **Ajustes y detalles (Android)** | `app/.../ui/screens/` → `SettingsSheet.kt`, `DetailScreen.kt`, etc. | Ajustes, ficha de álbum/artista, etc. |
| **Barras y “chrome” (Android)** | `app/.../ui/components/` → `MiniPlayer.kt`, `GlassNavBar.kt`, `FloatingBottomBar.kt`, `FrostedTopBar.kt`, `LiquidGlass.kt`, `floatingtabbar/FloatingTabBar.kt` | Mini-reproductor y barras de navegación. |
| **Componentes compartidos** | `sharedUi/.../ui/components/` → `Common.kt`, `SearchField.kt`, `ShelfRow.kt`, `Skeletons.kt`… | Piezas reutilizables. |
| **Iconos (código)** | `sharedUi/.../ui/icons/BitChordIcons.kt` (735 l.) | Iconos dibujados a mano (estilo Telegram, trazo grueso). |
| **Iconos (recursos)** | `app/src/main/res/drawable/` y `sharedUi/.../composeResources/drawable/` | play/pause/next/prev, logo, etc. |
| **Icono de la app (launcher)** | `app/src/main/res/mipmap-*` (`ic_launcher*.webp/png`, `mipmap-anydpi/*.xml`) | El icono del móvil (muchas variantes para distintos launchers). |
| **Fuentes** | `app/src/main/res/font/` (5 pesos de SF Pro Display) | Tipografía “Apple” actual → candidata a sustituir. |
| **Textos** | `app/src/main/res/values/strings.xml` (+ `values-es/`, etc.) | Todos los textos, incluido `app_name`. |
| **Tema aplicado / entrada** | `app/src/main/java/com/music/bitchord/MainActivity.kt` (~l.382, `BitChordTheme`) y `AppSettings.themeMode` | Dónde se aplica el tema y se elige claro/oscuro. |
| **Paleta desde la carátula** | `sharedUi/.../ui/theme/ArtworkPalette.kt` + `graphics/ColorUtils.kt` | Colores que la app extrae de la portada del disco (dinámicos). Ojo: convive con nuestra paleta fija. |

**Conclusión estratégica:** el **primer cambio (Fase 5)** debe ser la paleta en `Theme.kt` — es el mayor impacto con el menor riesgo.

---

## Fase 5 — Primera personalización (en curso)

### Cambio 1: paleta oscura DedSec/Defalt ✅ (aplicado y compilado)
- **Archivo:** `app/src/main/java/com/music/bitchord/ui/theme/Theme.kt` → `DarkColors`.
- **Commit:** `8a84d3fb` — "feat(theme): paleta oscura DEDSEC // DEFALT" (1 archivo, +14 −9).
- **Colores aplicados:** `primary` = `#00E5FF`; `background` = `#080B10`; `surface` = `#111820`; `surfaceVariant`/`outline` = `#26323B`; `onSurfaceVariant` = `#A9B6C5`; `error` = `#FF4D2E`; `onPrimary` = `#080B10`.
- **Compilación:** `BUILD SUCCESSFUL in 8s` (incremental). ✅
- **Nota:** el tema por defecto es `ThemeMode.DARK` → la app usa `DarkColors`. `LightColors` sigue intacto (posible cambio futuro si Juan usa modo claro).
- **Pendiente:** validar visualmente en el Infinix (instalar APK + capturas) y revisar contraste (regla 15).

### Validación visual en el dispositivo ✅ (2026-10-17)
- Instalada la APK `app-dev-arm64-v8a-debug.apk` por `adb` (`Success`). Convive con la app original (`com.music.bitchord` intacta).
- Capturas tomadas por `adb exec-out screencap` (en `/tmp/opencode/`): `05-home-dedsec.png`, `06-explore.png`, `07-account.png`.
- **Resultado:** el tema se aplica de forma coherente en toda la interfaz:
  - Fondo azul-negro `#080B10`; tarjetas `#111820`.
  - Acentos en **cian**: pestaña activa de la barra inferior (con su píldora) y etiqueta "EN USO" en Ajustes.
  - Texto principal blanco y secundario gris `#A9B6C5`.
- **Observaciones (para fases siguientes, no son fallos de este cambio):**
  - El **onboarding** usa un degradado morado/rojo propio (no la paleta) → choca con DedSec; candidato a la Fase 6.
  - Las tarjetas de **Explorar** usan colores por género (propios de la app).
  - El icono/nombre de la app siguen siendo "BitChord" → regla 13 pendiente.
  - La pantalla de reproducción (Now Playing) no se pudo ver aún: requiere iniciar sesión o música local.

---

## Fase 6 — Rediseño (en curso)

### Cambio 1: onboarding DedSec/Defalt ✅ (aplicado, compilado y validado)
- **Archivo:** `app/src/main/java/com/music/bitchord/ui/onboarding/OnboardingScreen.kt` (solo aspecto; **sin tocar la lógica**).
- **Antes:** degradado morado/rojo tipo Apple Music, texto blanco y botones blancos.
- **Ahora:**
  - **Fondos:** `DedsecNamePalettes` (terminal oscuro con foco cian) y `DefaltConnectPalettes` (interferencia **naranja de Defalt** sobre oscuro). Sustituyen a `WelcomeMeshPalettes`/`ConnectPalettes`.
  - **Velo oscuro:** de `0.05/0/0.45` a `0.42/0.30/0.62` de negro → luminancia de fondo 0.065 → **0.035** (a la altura del resto de la app).
  - **Etiquetas de terminal decorativas** (monoespaciadas): `> DEDSEC_NETWORK // INIT` (cian) y `> DEFALT // TRACE_ACTIVE` (naranja).
  - **Colores:** botón primario cian `#00E5FF` con texto oscuro `#080B10`; secundario cian translúcido con texto cian; "atrás" y cursor en cian; textos en blanco hueso `#F2F5F7`. Botón desactivado = cian al 14% (por diseño).
- **Hallazgo técnico (importante para el resto):** `MeshGradientBackground` **fuerza un brillo mínimo** a los colores (`hsl[2].coerceIn(0.28f, 0.58f)`). No se puede oscurecer el mesh pasando colores oscuros; hay que **subir el velo** (capa negra). Tenerlo en cuenta en la pantalla de reproducción.
- **Compilación:** `BUILD SUCCESSFUL`. **Validado en el Infinix** (capturas `18-name-final.png`, `19-defalt-final.png`, `20-name-enabled.png`).

### Cambio 2: fondo terminal de toda la app ✅ (commit `f179d510`)
- **Nuevo** `app/.../ui/theme/DefaltBackdrop.kt`: `object DedSec` (colores medidos) y `DrawScope.drawDefaltBackdrop()` → negro `#070B12`, halo azul royal arriba-izq, aliento cian, halo rojo abajo-der, **rejilla cian** (línea marcada cada 5), **scanlines** claras y viñeta. Todo decorativo.
- **Dónde se engancha:** el suelo opaco real estaba en `BitChordApp` (`MainActivity`, ~2531, `Box(...).background(colorScheme.background)`). Ahora ese `Box` usa `drawBehind { if (darkTheme) drawDefaltBackdrop(density) else drawRect(fondo) }`.
- **Lección (importante):** cambiar `paintBackdrop` (el `rememberLayerBackdrop`) **solo** cambia lo que muestrea el cristal; el fondo **visible** es el `Box` raíz. Hay que tocar los dos para que la barra y la pantalla coincidan.

### Cambio 3: paleta Defalt en los fondos dinámicos ✅ (commit `f179d510`)
- `sharedUi/.../player/MeshGradient.kt`: `FallbackColors` y `WelcomeMeshPalettes` dejan el rojo/violeta/naranja de Apple Music por **azul royal + cian** sobre negro.
- **Regla aprendida:** el *mesh* mezcla sus 4 colores → **azul + rojo dan violeta**, que no está en paleta. Por eso las paletas se quedan dentro de su familia (azules con cian; el rojo tiene su propia paleta) y el rojo Defalt vive en el halo del fondo y en los acentos.
- `HomeScreen.kt`: tarjetas de servicio (Biblioteca/descargas) y paleta de `local:downloads` a la identidad. `Common.kt`: `PlayingAccent`. `Theme.kt`: `AccentRed` → `#DC0736`.
- **Las tarjetas verdes/naranjas del "Replay" NO se tocan**: salen de la portada que el usuario ha escuchado (es el *Wrapped*), no de nuestra paleta.

### Cambio 4: iconos ✅ (commit `f179d510`)
- Los 12 estilos "glass" llevaban **el glifo viejo en rojo Apple**. Rehechos con la **máscara Defalt** (azul royal + red blanca) sobre las 3 formas (liquid = squircle, samsung = muy redondeado, pixel = círculo) y las 4 variantes (claro, oscuro, claro translúcido, gris). En claro, la máscara va en **line-art** (tinta azul marino).
- Rehechas también las 4 *background* sólidas, la **monocroma** (red en blanco, para iconos tematizados) y los `ic_launcher(.round).webp`.
- **Generador:** `/tmp/opencode/gen_icons.py` (rasteriza el vector propio `drawable/ic_logo.xml` con `rsvg-convert`; formas por superelipse; numpy + PIL). Nada copiado de Ubisoft.
- **El icono por defecto** ya era Defalt (vector, degradado `#0C1736→#02040A` + máscara).
- Validado en el Infinix: cajón de apps (`120-drawer.png`) y el selector de Ajustes (`121-ajustes-final.png`).

### Cambio 5: barra inferior + mini-reproductor ✅ (commit `3e51ff47`)
- **Nuevo** `app/.../ui/components/DefaltConsole.kt`: `Modifier.defaltRim(shape)` (el **filo cian** de 1dp), `defaltSelectedColor()` (la pestaña encendida), `defaltSelectColor()` (el relleno del indicador), `defaltAccentColor()`, `DefaltEq` (ecualizador de 3 barras) y `DefaltStreamTag` (`AUDIO_STREAM` en monoespaciada). Todos cambian a **azul royal** en tema claro, donde el cian no se lee.
- **Dato clave:** `AppSettings.liquidGlass` viene **apagado por defecto** (`MutableStateFlow(false)`), así que lo que se ve normalmente es **`MiniPlayer` + `FloatingBottomBar`** (haze), no `GlassNavBar`. Hay que vestir las dos parejas.
- **`GlassNavBar`** (con cristal): filo en las tres superficies, carátula enmarcada, pestaña activa en el acento, indicador en el acento, y en la fila ancha el **ecualizador + `AUDIO_STREAM`**.
- **`FloatingBottomBar`**: filo (sustituye el borde blanco del material), indicador y pestaña activa en el acento.
- **`MiniPlayer`**: filo, carátula enmarcada y el **ecualizador delante del artista** mientras suena (en pausa desaparece y la fila vuelve a ser la del artista, sin cambiar de alto).
- **Sin tocar lógica:** lo único nuevo que se lee del reproductor es `isPlaying`, que la barra ya recibía.
- Validado en el Infinix: barra normal + mini (`133-mini-play.png`) y barra con cristal + `AUDIO_STREAM` (`134-glass.png`).
- **Para ver el cristal:** Ajustes → Apariencia → *Liquid Glass*. En el teléfono lo activé un momento para la captura y lo **dejé como estaba** (apagado).

### Cambio 6: tipografía ✅ (commit `a451b73b`)
- **Fuera SF Pro Display**: los 5 `.otf` (11 MB) que traía la app. Es la cara de Apple Music, no se puede redistribuir, y era lo más "Apple" que quedaba. Aprobado por Juan.
- **Dentro dos familias, SIL Open Font License 1.1** (~1,2 MB en total):
  - **Chakra Petch** (`chakra_petch_regular/medium/semibold/bold.ttf`) — titulares, cuerpo y botones. Cuadrada, con esquinas cortadas.
  - **JetBrains Mono** (`jetbrains_mono_regular/medium/semibold/bold.ttf`) — rótulos, datos y todo lo que va en mayúsculas: tiempos, cabeceras de sección, chips, etiquetas técnicas.
- **`Theme.kt`**: `BitChordTypography` se rehace sobre las dos; `withFamily(familia, mono)` deja `labelMedium` y `labelSmall` en la mono. Se **quita el tracking negativo** de SF Pro (en una cara de trazos rectos, los pega).
- **`TabLabelStyle`** (nuevo en `Theme.kt`): las etiquetas de la barra de pestañas van en mono pero a **10sp y sin interletraje**. A 11sp con tracking, "Biblioteca" no entraba en un cuarto de barra y salía cortada. Aplicado en `GlassNavBar` y `FloatingBottomBar`.
- **Repuntados** a las fuentes nuevas: los **6 layouts del widget** de Android (`widget_media_*`, que llevaban `@font/sf_pro_display_*` en XML), **`ReplayPoster.kt`** (el póster que se comparte: Chakra Petch para titulares/cuerpo y **la mono para sus rótulos**, como la interfaz) y el rótulo de terminal del **onboarding**.
- **Estado del arte:** la app **ya no tiene nada de SF Pro ni de Apple por dentro**.
- Validado en el Infinix: `141-biblioteca.png` (etiquetas mono + titulares Chakra), `143-barra.png` (barra con las etiquetas nuevas), `147-ajustes.png` (denso, se ve el "terminal" en `CALIDAD DE AUDIO` y el chip `EN USO`).
- **Nota:** en `145/146` la captura salió oscurecida y "todo monoespaciado"; era el **velo de la hoja de cuenta** sobre el fondo, no un cambio de fuente. Confirmado comparando la misma zona (`cmp-mini.png`).

### Cambio 7: visor de la pantalla de reproducción ✅ (commit `f9f5586c`)
- **Nuevo** `sharedUi/.../player/DefaltPlayer.kt`: `DefaltCyan` (`#00E5FF`) y `DefaltRed` (`#DC0736`), los colores de sistema de la edición, y **`Modifier.defaltHud`** — el **visor**: un hilo cian por el borde (10% de alfa) y un **corchete en cada esquina** (55%, brazos de 26dp). Es "los corchetes de esquina" que pedía la guía.
- **Dónde va:** en la caja raíz de las **dos** ramas del reproductor (`if (landscape)` y el `else` vertical), así que queda por encima de todo **sin ocupar sitio en el reparto** y sin recibir tacto.
- **La opacidad se lee en el dibujo, no al construir el modificador.** Se pasa `dockFade` como función: así el visor se apaga con el reproductor mientras éste se pliega hacia el mini-reproductor **sin recomponer la pantalla en cada fotograma del viaje**. Verificado: al cerrar no queda ni rastro de cian (`153-crop.png`).
- **La separación de arriba respeta la barra de estado** (`stableStatusBarInsets()`, ya usado en el archivo): si no, el reloj del sistema cae dentro del corchete.
- **La barra de progreso, en cian** (`PlayerScrubber` → `ThinSlider(activeColor = DefaltCyan, markerColor = …)`). Lo que queda por delante y **el volumen siguen en blanco**, que aguanta sobre cualquier carátula; la barra que manda es la del progreso.
- Los tiempos (`0:33` / `-2:41`) y el rótulo de la fuente ("Suena desde…") **ya salían en monoespaciada** por el cambio de tipografía, sin tocar nada.
- **Sin cambios de lógica:** el visor no lee del reproductor nada que la pantalla no tuviera a mano.
- Validado en el Infinix: `151-player-hud.png` (en pausa, se ven los cuatro corchetes), `152-player-play.png` y `152-crop.png` (la barra en cian con los tiempos en mono).

### Cambio 8: rejilla sobre el mesh + firma del visor ✅ (commit `bb76eccf`)
- **`Modifier.defaltGrid`**: la misma malla que el fondo de la app (línea cada 36dp, una marcada cada cinco), algo **más presente** aquí (7,5% / 14% frente a 5% / 9,5%) porque este fondo lleva los colores de la portada y se come una línea al 5%.
- **Va antes que la carátula en el árbol**, así que la rejilla sólo asoma por donde se ve el mesh (alrededor y por debajo del vinilo), **nunca por encima**. Se apaga con el cruce al desenfoque a sangre.
- **`DefaltHudSignature`**: `RAT // PLAYER` en monoespaciada, sobre la esquina de abajo a la izquierda, dentro del visor. Sin dato: es la firma, no un instrumento.
  - **Corregido** (`0d6ed9dc`): caía en la misma línea que el nombre del **dispositivo de salida**, que va centrado, y se tocaban (`RAT // PLAYER` x 36–296 contra `soundcore Liberty 5 Pro Max` desde x 248, medido con `uiautomator`). Baja al **pie del visor** (18dp del borde) y arranca **pasado el brazo del corchete** (12dp + `HUD_ARM` + 4dp): más a la izquierda era el propio brazo vertical el que le cruzaba la primera letra. Ver `163-small.png`.
  - **Nota de método:** dos capturas salieron con el texto **duplicado** por toda la pantalla (`159-oscura.png`, `162-firma.png`). Es un artefacto de `screencap` al capturar justo tras abrir el reproductor, no un fallo de la app: la captura repetida sale limpia.
- Las dos leen su opacidad en la **capa gráfica**, no en la composición: se van con el reproductor al plegarse sin recomponer la pantalla en cada fotograma del viaje.
- Validado: `156-player-next.png` / `156-crop.png` (rejilla sobre portada naranja).

### ⚠️ Hallazgo abierto: portadas claras (`154-player-grid.png`)
Con una portada **blanca** (Molatov), el reproductor se **lava entero**: el mesh saca los colores de la carátula, o sea blanco, y el título, el artista, las horas y los glifos del transporte van en blanco. **No es un fallo de la tanda**: es el comportamiento que traía la app —el velo de abajo es negro al 6%→30% a propósito, "sólo lo justo para quitar el texto blanco de una carátula brillante"—, y con una carátula blanca no llega. Arriba la app **sí** se adapta (`topBandScrimAlpha(artTopLuminance)`, 0,16→0,65). Abajo, no.
- **Arreglo propuesto (a decidir con Juan):** que el velo de abajo siga la misma idea que el de arriba (más negro cuanto más clara la carátula), o simplemente subirlo a un valor fijo que siempre aguante. De paso, el reproductor se vuelve **oscuro**, que es más Defalt.
- Con portadas de color (la mayoría) el reproductor se ve bien y no hay nada que arreglar.

### Cambio 9: el velo del reproductor se mide con la carátula ✅ (commit `41782b19`)
- **`rememberArtworkBottomBandLuminance`** (`ArtworkPalette.kt`): la luminancia de la **banda de abajo** de la carátula — la que el fondo del reproductor enseña por debajo de la costura. Sale del **mismo decodificado** que ya usaba la banda de arriba, así que no cuesta otra lectura.
- **`backdropScrimAlpha`**: la lleva a un velo de negro entre el **30%** (carátula oscura: exactamente lo que había) y el **85%** (carátula blanca).
- **`ArtworkMeshBackdrop`**: nuevo parámetro `artworkLuminance`, y el velo pasa de dos colores fijos a **cuatro paradas ponderadas hacia la banda donde viven el título y los créditos**. Un velo que sólo se hunde en el borde de abajo no los alcanza: el título está al 40% de la pantalla. Se resuelve en composición, no por fotograma — cambia cuando cambia la canción.
- **Medido** sobre la captura real de la carátula blanca que fallaba (`154-player-grid.png`), con `/tmp/opencode/check_veil.py`: el contraste del texto blanco pasa de **1,8:1 (ilegible)** a **6,1:1 en el título** y **8,6:1 en el transporte**, por encima del 4,5:1 que pide la norma.
- Con carátulas oscuras el velo se queda **como estaba** (validado visualmente: `160-oscura2.png`).
- Validado también con carátula naranja clara: `157-velo-naranja.png` (el reproductor se hunde abajo, que además es más Defalt).
- **Sin cambios de lógica:** la luminancia sale del decodificado que ya se hacía.

### Cambio 10: los iconos ✅ (commits `4b817bbf`, y la familia de serie a falta de que Juan elija)

**Los 28 propios** (`sharedUi/src/jvmSharedMain/.../ui/icons/BitChordIcons.kt`), los que se ven en la barra de abajo, el reproductor y las cabeceras. Dejan de ser redondeados:
- **`StrokeCap.Square` y `StrokeJoin.Miter` en todo el fichero** (23 y 21 sitios): fuera las puntas de bola y las esquinas curvas. Es de donde sale el 90% del cambio de carácter.
- **Todo círculo pasa a octógono de lados planos** — el cristal de la lupa, el dial de la brújula, la esfera del reloj, la cabeza del pin. A 24dp sigue leyéndose como un círculo, pero es una figura que se puede nombrar (y el asa de la lupa arranca justo en el punto medio de su lado).
- **Todo punto pasa a bloque sólido** (marcadores de la cola y de la lista) y **toda esquina redondeada a chaflán** (la nube de letra y de cita, los cuatro cuadrados de rejilla, la cabeza de la nota musical).
- El cuadro del medidor pasa a **arco con los hombros cortados**; el pivote de las agujas del reloj es el vértice del propio codo (una sola polilínea, no dos trazos).
- **Los tres glifos de la barra** (Explorar, Biblioteca, Buscar) venían de la fuente `cupertino_icons` con cada esquina redondeada por una curva `Q` **cuyo punto de control *es* la esquina**. Reescribir cada `Q` como `L` por ese mismo punto deja **todas las coordenadas que eligió la fuente** y la esquina recta: hecho con un script, 60 curvas.
- **El corazón se queda curvo, a propósito.** Es el botón de me gusta: su forma tiene que leerse como un corazón y no como un escudo. Se cambia si Juan quiere.
- **Sin cambios de lógica:** sólo geometría de vectores.

**Los 328 de serie de Material.** Hallazgo al empezar: el juego propio se usa ~70 veces; **el grueso de la app son iconos de Material en su versión `Rounded`** — 328 usos en 48 ficheros, justo lo contrario de lo que se acababa de hacer. Cambiados de golpe con `sed` (imports y usos: 297 `Icons.Rounded.*` + 31 `Icons.AutoMirrored.Rounded.*`).
- **Dos variantes compiladas y capturadas para que Juan elija:** **Sharp** (tanda 8: mismo peso, esquinas y puntas rectas) y **Outlined** (tanda 9: los mismos dibujos, huecos y de trazo fino).
- **Recomendación: Outlined**, porque deja las dos familias en el mismo idioma (trazo). En la fila de Descargas se ve: con la carpeta sólida original, la carpeta se comía al icono nuestro de descarga; hueca, las dos pesan igual. Ver `comparativa-iconos.png`.
- Comprobado antes de empezar que la librería trae las dos familias completas (2083 iconos `sharp`, los mismos que `rounded`), y que el build resuelve los 328 sin un solo icono suelto.
- **Afecta también a escritorio y TV**, porque `sharedUi` es común. Sólo se ha compilado Android.

### Pendiente de Fase 6
2. ~~Barra inferior + mini-reproductor~~ ✅ **hecho** (Cambio 5).
3. ~~Pantalla de reproducción~~ ✅ **hecho** (Cambios 7, 8 y 9), incluido el velo adaptativo.
4. Pantallas Inicio/Biblioteca/Buscar (encabezados tipo **terminal**: monoespaciada, corchetes, etiquetas de sección).
5. ~~Icono + nombre~~ ✅ **hecho** (Cambio 4).
6. Pulido (glitches, animaciones, detalles Defalt): micro-glitch al cambiar pista.
7. ~~**Tipografía**~~ ✅ **hecho** (Cambio 6).
8. ~~**Iconos**~~ ✅ **hecho** (Cambio 10): los 28 propios, con esquina viva; y los 328 de serie, cambiados de familia (queda **elegir entre Sharp y Outlined**).

---

## Fase 7 — Caza de rastros de marca, label del sistema y textos sueltos ✅ (2026-10-18)

Salió de una revisión en el móvil: al abrir **Descargas** aparecieron dos fallos que **no** estaban en `strings.xml`.

### 7.1 Label del sistema en el sabor *dev* ✅
- `app/src/dev/AndroidManifest.xml` declaraba `android:label="BitChord Dev"` (literal que **gana sobre `app_name`**). Era lo que veían el **lanzador**, el **diálogo de permisos** («¿Permitir que BitChord Dev acceda…?») y **Ajustes de Android**.
- Corregido a **`RAT // PLAYER`**. Validado con captura de la ficha de la app en Android (`60-applabel.png`) y con el propio diálogo de permisos.

### 7.2 Rastros visibles de «BitChord» en el código ✅
| Archivo | Antes | Ahora | Dónde se veía |
|---|---|---|---|
| `SettingsSheet.kt` | `append("bitchord $version")` | `append("RAT $version")` | Colofón de Ajustes |
| `DiscordRPC.kt` | `DEFAULT_BUTTON_2 = "Visit BitChord"` | `"Visit RAT"` | Tarjeta de Discord |
| `LastFM.kt` | `UserAgent = "BitChord (https://…)"` | `"RAT"` | Perfil de Last.fm |
| `ArtistFacts.kt` | `"BitChord/$VERSION"` | `"RAT/$VERSION"` | API externa |
| `SourceFormats.kt` ×3 · `AddonClient.kt` ×2 | errores «… BitChord …» | «… RAT …» | Pantalla Fuentes |
| `Backup.kt` ×2 + nombre de fichero | «BitChord backup» / `bitchord-backup-…` | «RAT backup» / `RAT-backup-…` | Errores y nombre del copión |
| `ReplayShareSheet.kt` | `Pictures/BitChord` | `Pictures/RAT` | Galería |
| `MainViewModel.kt` | `"No downloaded tracks"` (hardcode) | `R.string.no_downloaded_tracks` | Lista Descargas vacía |

### 7.3 Inglés suelto ✅ (lo que salió en la captura)
- «No downloaded tracks» era un **literal hardcodeado** en `MainViewModel.kt`, no un recurso: **nunca** podía traducirse. Ahora es `no_downloaded_tracks` (`values`: "No downloaded tracks"; `values-es`: "No hay canciones descargadas"). Los otros 17 idiomas heredan el inglés (igual que antes).

### 7.4 Auditoría de traducciones (es) ✅
- `values` tiene 1067 claves y `values-es` 1053 → **las 14 que faltan son `translatable="false"`** (nombres de estilos de icono, "YouTube", "PCM Data Rate"). **El español está completo.**
- Nota: quedan **literales en inglés hardcodeados** en la app (diálogos de cuenta, pantalla de Discord, nombres de "Fuentes"…). Son un **hueco del original**, no de la marca; se tratan aparte si Juan quiere.

### 7.5 Pendiente de decidir ⚠️ (no se toca sin permiso)
- **`AppUpdateChecker`** apunta al repo **original** (`kushagrasinghx/BitChord`) y su prefijo de APK es `"BitChord-"`: si hubiera un release nuevo, **ofrecería instalar el BitChord original** encima del nuestro. Es lógica; **pendiente de decisión de Juan**.
- El botón «Visit RAT» de Discord y el enlace «GitHub» de Ajustes **siguen apuntando al repo original** (atribución GPL). Decidir si se dejan o se cambian.
- Sabor *dev* vs *prod*: el label ahora es `RAT // PLAYER` en ambos; si algún día conviven instalados, hará falta un distintivo (p. ej. un sufijo).

### Validación Fase 7
- Compilado (`BUILD SUCCESSFUL`) + `MediaTaggerTest` y `SourceFormatsTest` en verde. Capturas: `60-applabel.png`, `61-onb1.png`, `62-downloads-empty.png`, `65-colofon.png`.

---

## Fase 8 — Inglés suelto (textos hardcodeados) 🔄 (en curso)

**Decisión de Juan:** sí, traducir al español los literales visibles que venían así del original.

- **Antes de empezar, se desactivó el comprobador de actualizaciones** (`AppUpdateChecker`): apuntaba al repo original, así que iba a ofrecer instalar el **BitChord original**. Ahora hay una bandera `ENABLED = false` (reversible; basta repuntar `LATEST_RELEASE_URL` y `UPDATE_APK_PREFIX` a un canal propio para volver a activarlo).
- Los enlaces del autor (GitHub/Developer/Discord y el botón de Discord) **se dejan** a propósito: son atribución GPL y no muestran la palabra "BitChord".

**Cómo se traduce:** los textos rotos están casi todos en la capa de datos (objetos Kotlin **sin `Context`**), así que convertirlos a recursos obligaría a pasar un `Context` por firmas = tocar lógica. Para no hacer eso, se traduce **el literal en su sitio al español** (cambio puramente textual: nombres de recursos, identificadores y estructura siguen intactos). Consecuencia asumida: esos textos concretos quedan en español aunque el idioma de la app sea otro; el resto sigue localizado por `values-es`. Hay que vigilar los literales que se usan como **clave** (p. ej. `SourceKind.label` se guarda en el `config`): se revisan uno a uno.

### Tanda 1 — pantalla «Fuentes» ✅ (commit `da009dec`)
- `SourceKind.kt`: etiquetas y descripciones (`Addon`→**Complemento**, `Custom module`→**Módulo propio**, `Module source`→**Índice de módulos**) y chips (`Lossless`→Sin pérdida, `Lossy`→Con pérdida, `Full catalogue`→Catálogo completo, `High Quality`→Alta calidad).
- `ModuleSource.kt`, `addon/AddonClient.kt`, `addon/SourceFormats.kt`, `AddonSource.kt`: mensajes de rechazo/error visibles («Añadir un complemento», «No respondió nada en esa dirección», «Este complemento solo funciona con una salida sin pérdida…», etc.).
- Dos `USER_AGENT = "BitChord/v…"` (se envían a los servidores de complementos) → `"RAT/v…"`.
- Tests `AddonSourceTest` y `SourceFormatsTest` actualizados a los textos nuevos (3 asserts); `MediaTaggerTest` también en verde.
- **Validado en el Infinix** (`70-fuentes.png`): la fila de YouTube Music ya dice **«Con pérdida · Catálogo completo · Radio»** y la entrada de añadir, **«Añadir un complemento»**.

### Tanda de voz — el tono, retomado ✅
Juan: el primer intento se pasó (cringe) y el segundo se quedó corto («texto de manual»). Punto medio acordado = **hacker sobrio / cañero**: verbos directos y léxico de red, servidores, bits y fiesta; **sin** apodos, glitch, pseudo-terminal, mayúsculas gritadas ni amenazas.
- `sign_in` → **Abrir conexión**; `sign_out` → Cerrar conexión; `signed_in` → Conexión abierta; `not_signed_in` → Sin conexión.
- `sign_in_youtube_music` → **Conectar la fiesta con YT Music** (elegido por Juan).
- `library_sign_in_description` → «Abre tu cuenta de Google y aparecen tus canciones, tus listas y tu historial de YouTube Music.»
- `import_spotify` → **Saquear una lista de Spotify**.
- `no_downloaded_tracks` → **Vacío por ahora. 0 bits descargados.**
- Onboarding: `onboarding_connect_title` → **Enchufa YouTube a la fiesta**; `onboarding_sign_in_google` → **Enchufar la cuenta de Google**; `onboarding_skip_sign_in` → **Entrar sin cuenta**.
- `search_empty` → «Millones de canciones en YouTube Music. Tira del hilo.»; `download_all` → Bajar todo; `widget_nothing_played` → Ni un bit reproducido todavía.
- **Hallazgo importante:** parte de los textos visibles viven en **`sharedUi`** (`composeResources`), **no** en el módulo `:app` — p. ej. el `search_empty` del Buscar. Hay que tocar los dos sitios (y `widget`/`wearos` cuando aplique).
- Validado en el Infinix: `80-onb2.png` (onboarding), `82-descargas.png` (0 bits), `84-buscar.png` (Tira del hilo).

### Tanda 2 — subir el registro y repartirlo ✅ (commit `03e9a322`)
Juan: «súbelo un poco más» y «en general en todo». Se sube un punto y se aplica a **las dos capas** (`:app` y `sharedUi`) y a **los dos idiomas** desde un mismo diccionario.
- **Cuenta/sesión:** `sign_in` → *Abrir línea*; `sign_out` → *Cortar la línea*; `signed_in` → *Estás dentro*; `not_signed_in` → *Sin entrar*; `onboarding_skip_sign_in` → *Colarse sin cuenta*.
- **Descargas:** `download` → *Bajar*; `download_all` → *Bajar todo*; `nothing_downloading` → *Ni una descarga en marcha*.
- **Vacíos y estados:** `history_empty` → *Ni un bit reproducido todavía*; `no_local_audio_found` → *Aquí no hay audio que rascar*; `no_internet_connection` → *Sin red. Sin señal.*; `something_went_wrong` → *Algo se ha torcido*; `unknown_artist` → *Artista sin identificar*; `nothing_to_explore`, `no_youtube_music_results`, `downloaded_playlist_empty`.
- **Buscar/lista:** `recent_searches` → *Lo que has buscado*; `search_library_empty`; `import_spotify` → *Saquear una lista a Spotify*; `personalized_recommendations` → *Que RAT te recomiende*.
- **Inglés suelto cazado:** la pestaña `search_source_addons` decía *Addons* también en español → **Complementos**.
- **Fallo corregido:** los apóstrofos en inglés («You're in») rompen el XML de Android → se evitan (regla ya conocida).
- Validado en el Infinix: `90-onb2.png` (onboarding), `92-biblioteca.png` (Abrir línea / Saquear una lista a Spotify), `93-buscar.png` (Tira del hilo / Complementos).
- **Ojo (pendiente):** los mismos nombres de clave viven en `:app` **y** en `sharedUi`; si se cambia uno sin el otro, la app queda a dos voces según qué composable pinte.

### Tanda 3 — Escuchar juntos, cuenta, reproductor, restos en inglés ✅ (commit `a275d01e`)
Juan: «así vamos bien, sigue con el resto».
- **`sharedUi`, restos en inglés** (estaban así *también* en el fichero español): `Audio output` → *Salida de audio*; `Playing here` → *Suena aquí*; `Audio Pipeline` → *Ruta de audio*; `Casting` → *Enviando*; `Chromecast and Google Cast speakers`; `Decrease/Increase lyrics offset` → *Adelantar/Atrasar la letra*; `Lyrics offset` → *Desfase de la letra*; `Medium Quality`; `Wired headphones`; `This phone`; `%1$s's Phone`; `open_lyrics` (*Letra*).
- **`sharedUi`: 7 claves que no existían en español** y caían a inglés → añadidas: `sign_in_with_google` (*Entrar con Google*), `previously_played`, `search_addons_empty`, `search_addons_hint`, `search_addons_no_results`, `watch_output`, `watch_output_subtitle`.
- **Escuchar juntos:** *Montar una fiesta*, *Meterse en una fiesta*, *Saca un código y pásalo*, *Cuántos cabemos*, *Lo que ha pasado*, *Aquí todavía no suena nada*.
- **Cuenta/scrobbling:** *Entrar en Last.fm*, *Pega tu token de ListenBrainz y a registrar*, `listen_as`.
- **Reproductor:** *Sonando ahora*, *Suena desde*, *Puesto por*; textos de AutoPlay.
- **`AccountChannelDialog.kt`**: literales fijos al español (*Listen as*, *Close*, *Brand channel*, *Your channel*, *default* → *predeterminado*…). También se comprobó que el `label`/`detail`/`verb` del enum de `DiscordScreen` es código muerto: la pantalla tira de `R.string.activity_*`/`verb_*`, ya en español.
- **Ajustes:** `your_data` → *Lo tuyo*; `app_language_description`.
- Validado en el Infinix: `99-cuenta.png` (Ajustes: «Escuchar juntos · Un código y todos sonando lo mismo a la vez»).

### Tanda 4 — inglés incrustado (Escuchar juntos / Replay) ✅ (commit `2773074c`)
- **`data/listentogether/ListenTogether.kt`**: los mensajes de `PartyException` (lo que lee el usuario cuando falla la fiesta) al español, **sin tocar los `code`** (`not_signed_in`, `no_server`, `bad_code`…). Ej.: *Entra para escuchar juntos*, *El código de fiesta son seis letras o números*, *No puedes entrar: te han echado de esta fiesta hace poco*, *No se pudo llegar al servidor de la fiesta*.
- **`ui/replay/ReplayModel.kt`**: la etiqueta `THIS_WEEK` dejaba de ser el literal `"This week"` y pasa a `R.string.replay_this_week`; la coletilla del resumen `" in music."` → *« en música.»* (los otros periodos ya usaban recursos / `Locale`).
- **NO se toca `"Unknown Artist"`**: es un **centinela** que se compara (`WebDavClient`), no solo texto. Traducirlo rompería la lógica. Queda anotado.
- Compila y arranca (`100-inicio.png`); no hay tests que citen esos textos.

### Tandas pendientes (por pantallas)"
- **Tanda 2 — Listen Together:** avisos («A party code is six letters or digits.», «Couldn't reach the party server.», «Set the party server address first.», «Sign in to listen together.», «This account can't be used to jam.», «Target server address is invalid…»).
- **Tanda 3 — Estadísticas:** «All time / This week / This month / This year», y «Unknown Artist».
- **Tanda 4 — Descargas:** `friendly()` («Download failed — check your connection»), «Downloads are set to Wi-Fi only», «Nothing to download», errores de paquete offline.
- **Tanda 5 — Cuenta y Discord (composables):** diálogo de canal de la cuenta («Listen as», «Choose in YouTube Music», «Close»…), pantalla de Discord, `MainViewModel` («N songs»).
- **Tanda 6 — Reloj (Wear):** `WatchInstaller` («pairing refused», «install failed»…).
- **Criterio:** se traducen los textos que **ve el usuario**; los mensajes de `Log`/`TrackLog`, los nombres de códec/formato y los términos técnicos estándar se quedan como están.

---

## Pendientes / próximos pasos

- [x] **Fase 3:** compilar la app sin modificar → ✅ `BUILD SUCCESSFUL`.
- [x] **Fase 4:** mapa completo de archivos de tema/colores/tipografía/iconos/pantallas ✅.
- [x] **Fase 5:** paleta global en `ui/theme/Theme.kt` ✅ aplicada, compilada y validada en el teléfono.
- [x] **Fase 6, cambio 1:** onboarding DedSec/Defalt ✅ aplicado, compilado y validado.
- [ ] **Fase 6:** seguir con barra inferior/mini-reproductor, pantalla de reproducción, pantallas y pulido. Compilar y verificar tras cada cambio pequeño.
- [ ] Fases 7–8: APK final + pruebas en el Infinix, ajustes.
- [ ] Diferenciar `applicationId` y nombre visible ("DEDSEC // DEFALT") — **confirmar con Juan** (regla 13).

## Problemas encontrados

- **Plataforma `android-37` vs `android-37.0`** (RESUELTO): AGP 8.10.1 no reconoce el nombre nuevo con versión menor. Enlace simbólico local `android-37 -> android-37.0`.
- **`:desktopApp` exige Java 21** → se evita con `--configure-on-demand`. No afecta al móvil.
- **JDK del sistema es 27** (demasiado nuevo para Gradle 8.11.1) → se usa `JAVA_HOME` apuntando a JDK 17.

## Registro de cambios (changelog)

- **2026-10-20 (noche, 5)** — **Selector de fondos** en Ajustes → Apariencia → **Fondo**, con cuatro variantes: **DEDSEC** (rejilla + scanlines + marca de agua, el que ya estaba), **Rejilla**, **Terminal** (scanlines y marca, sin rejilla) y **Limpio** (solo el color). El enum `BackdropStyle` vive en `AppSettings.kt`, con su `KEY_BACKDROP = "backdrop_style"` en `bitchord_settings.xml`; `drawDefaltBackdrop` recibe el estilo y apaga rejilla/scanlines/marca por separado (los halos, la base y la viñeta son fijos: son el color, no el ruido). La fila se **apaga con el tema claro**, donde no hay fondo que texturar. Verificado en el Infinix midiendo los píxeles, no a ojo: la rejilla aparece a 108 px (36 dp a densidad 3) solo en Rejilla y DEDSEC, la marca de agua solo en DEDSEC y Terminal, y elige sobrevive a un reinicio de la app. Las cuatro en una lámina: `/tmp/opencode/fondos-4.png`. Commit `c6fd1084`.
- **2026-10-20 (noche, 4)** — **Las notas de Defalt.** Dos huecos: **(1)** siete toques en el emblema de la rata en Inicio (uno cada ≤1,2 s) sueltan una de las dieciséis notas sobre la pantalla, en un panel con corchetes de encuadre, luz roja y la frase en su inglés original; se cierra al tocarla o sola a los 9 s, y vibra al salir. El contador de toques vive en `HomeTitle` (`sharedUi`), la nota en `DefaltIntercept` (`:app`), y el texto en `DefaltQuotes`. **(2)** La nota del día espera al final de Ajustes, encima del colofón de versión, en monoespaciada al 55 % y sin rótulo: sale del **día del año**, así que cambia cada día pero no mientras la app está abierta. Verificado en el Infinix (`262-s.png` la nota del emblema, `265-s.png` el pie de Ajustes). Commit `0ad8ee5e`.
- **2026-10-20 (noche, 3)** — **Marca de agua «DEDSUC» en el fondo.** Nuevo `PixelWordmark.kt`: una tipografía de bloques de 5 × 7 en el propio lienzo (no es una fuente de sistema ni un recurso ajeno), dibujada **en plantilla** —solo el borde de cada letra— y girada −13° a ancho de pantalla sobre la rejilla, cian al 7,5 % y por debajo de las scanlines para que el CRT también la atraviese. La palabra es la que usa Defalt en `DedSec? More like DedSUC!`. Se probó primero maciza al 3,8 %: se leía como una mancha detrás de las filas; la plantilla se lee sin ensuciar. Verificado en Inicio (`253-s.png`) y Ajustes (`254-s.png`). Commit `7e3325d3`.
- **2026-10-20 (noche, 2)** — **Aleatorio: patas iguales.** Juan vio que el tramo recto que entra en cada flecha era más corto que el de la izquierda. Ahora los dos miden **5,4** (la X se mete hacia el centro y cada punta arranca de su propio tramo recto). Verificado en la cabecera de un álbum. Commit `a7d4af7b`.
- **2026-10-20 (noche)** — **Aleatorio simétrico, corazón de píxeles y ⋮ horizontal.** Juan propuso el aleatorio exacto: partir el símbolo por la mitad, quedarse con la izquierda y reflejarla —una X con una pata horizontal en cada punta— y en la mitad derecha poner los picos de las flechas. Hecho tal cual: los dos trazos a 45° se cruzan **sin hueco** (el corte de plantilla se va aquí a propósito, lo pedía la simetría) y las dos patas de la derecha rematan en cuña maciza. **Corazón** y **corazón lleno** pasan a ser un **sprite de 7 × 6** (celdas de 3 unidades): el lleno es el relleno y el vacío es ese mismo relleno **erosionado un píxel y dibujado como hueco** (`EvenOdd`), así el anillo sale continuo y los dos estados son el mismo dibujo; ya no queda ni una curva en toda la familia. Al verlo en el móvil apareció que el botón de al lado del corazón no era el ⋮ vertical sino un **MoreHoriz** de Material: ahora existen las dos variantes en la familia (tres bloques, tumbados o apilados) y el reproductor, el reproductor apaisado y la cabecera de búsqueda usan las nuestras. Truco de implementación: **todas las formas macizas de un icono van en un solo `path`**, porque en el corazón cada celda es un rectángulo y en paths separados se veían costuras de antialias entre celdas. Verificado en el Infinix: corazón vacío y lleno (`242-heart.png`, `243-heart.png`) y menú horizontal. Commit `e3ba9ca6`.
- **2026-10-20 (tarde)** — **Transporte, aleatorio y repetir.** Juan vio la lámina y señaló dos cosas: *Repetir* se leía raro y en *Aleatorio* las puntas parecían separadas del trazo a propósito. Rehechos los dos: **Aleatorio** son dos tramos a 45° que se cruzan —el de abajo se parte donde pasa por detrás, así el cruce se lee como cruce y no como rotura— y las flechas pasan a ser **cuñas macizas pegadas al final del trazo**, no cheurones flotando alrededor de la punta. **Repetir** es un lazo abierto arriba con la flecha **soldada al propio trazo** y el hueco de plantilla detrás, en vez de un cheurón montado encima. Además entran los seis del reproductor: **Play** (cuña con la punta cortada), **Pausa** (dos barras inclinadas, el corte a 45 de la familia), **Atrás/Adelante** (dos cuñas con la punta cortada y el hueco de plantilla entre ellas), **Corazón** (ya era nuestro) y **⋮** (tres bloques, no tres puntos). Los cuatro drawables de `sharedUi/src/commonMain/composeResources/drawable/ic_player_*.xml` se regeneran desde **la misma geometría** que los `ImageVector` (lienzo 960 = 24 × 40), así el botón grande y el resto usan el mismo lápiz; el mini reproductor (`MiniPlayer.kt`, `GlassNavBar.kt`) y el ⋮ de cada fila (`Common.kt`) dejan de usar los de Material. Compilado, instalado y verificado: reproductor (`231-s.png`) y cabecera de álbum con Aleatorio (`233-actions.png`). Commit `63301bb2`.
  - **Nota:** *Repetir* solo se usa en escritorio; en Android no hay botón de repetir, así que ese glifo se comprobó en la lámina.
- **2026-10-20** — **Iconos propios rediseñados de cero (28 glifos).** Juan pidió «otras formas más hacker» manteniendo legible cada función. Se sustituyen los 28 `ImageVector` de `BitChordIcons.kt` por una gramática nueva de tres reglas: **plantilla** (todo contorno cerrado se abre en una esquina con un hueco de ~1 unidad), **faceta** (ningún círculo: octógonos, anillos con muesca, cuñas a 45°) y **píxel** (los puntos pasan a bloques y las líneas paralelas escalonan un píxel). Lápiz 2,2 con `Square`/`Miter`: lo que antes era un redondeo ahora es un corte. Las tres pestañas abandonan los contornos de la fuente `cupertino_icons`: casa facetada con ventana calada y tejado escalonado, brújula con aguja a dos tonos, tres losas escalonadas y lente con una esquina mordida; con ellas se va el helper `fontGlyph`, que ya no tenía usuario. `Heart`/`HeartFilled` se quedan curvos a propósito (un corazón con esquinas es una pica). Los nombres públicos no cambian: ni una llamada tocada. Método: geometría dibujada primero en una lámina SVG (`/tmp/opencode/icons/gen.py` + `emit.py`), revisada a 24 y 40 px antes de portarla a Kotlin. Ajustes tras verlo en el móvil: las comillas del bocadillo se leían «FF» (ahora dos barras escalonadas), el chevron llevaba una raya que sobraba, la traza de *Fotogramas* se emborronaba (de cuatro escalones a dos), la ventana de la casa se pegaba a la pared y el mástil de la nota llevaba un escalón que la convertía en un «7». Verificado en Inicio, Biblioteca, Explorar, reproductor y Ajustes. Commit `647fa454`.
  - **Capturas:** `210-bar.png` (pestañas), `215-bottom.png` (pie del reproductor), `214-rows.png` (chevrons), `225-s.png` (Descargas y «Nueva lista»). *Ojo:* el `screencap` del Infinix devuelve a veces la pantalla anterior mezclada con texto repetido; se repite la captura.
- **2026-10-19** — **Arreglo: una playlist importada de Spotify no ofrecía «Bajar todo».** Lo reportó un amigo de Juan y se reprodujo en el Infinix. La causa no era el importador: la fila de descarga de la hoja de acciones se ofrecía con `takeIf { remote }`, y `remote` era «todo lo que no empieza por `local:`». Ese prefijo cubre cosas que no se parecen en nada — las carpetas del teléfono, un álbum agrupado de una carpeta y **una playlist importada**, que solo guarda la *lista* de canciones: su audio sigue viniendo de fuera, así que quiere la descarga como cualquier otro lanzamiento. Ahora la fila se decide con un `isOnDevicePage()` nuevo (carpetas, agrupaciones y páginas que **son** un registro de descarga) y `remote` se queda solo para Compartir, que sí necesita un id real de YouTube Music. Además `Downloads.pageIdFor()` pasa a ser **idempotente**: el registro de una importada se archiva con la id de su propia página y volver a prefijarlo construía la página de un registro inexistente (la tarjeta de «En el dispositivo» no habría abierto). Test de ida y vuelta en `DownloadSessionTest`. Verificado en el móvil con una playlist importada de prueba (inyectada en las preferencias y **borrada después**; la rotación automática quedó como estaba): la hoja muestra «Bajar todo» (`188-sheet.png`). Commit `b5b63ca3`.
  - **Nota de paso, sin tocar:** la tarjeta de una importada dice «N canciones · En este dispositivo», y su lista no está en el dispositivo. Se cambia si Juan quiere.
- **2026-10-18 (noche, 2)** — **Fase 8 (tanda 1): inglés suelto → español + comprobador de updates apagado.** Decidido con Juan: traducir los literales visibles en inglés y desactivar el comprobador de actualizaciones (apuntaba al repo original y ofrecería instalar el BitChord original; ahora `ENABLED = false`). Tanda 1 = pantalla **Fuentes**: traducidos `SourceKind` (etiquetas, descripciones y chips), `ModuleSource`, `AddonClient`, `SourceFormats` y `AddonSource`, más dos `USER_AGENT "BitChord/v…" → "RAT/v…"`. Como esos textos viven en objetos sin `Context`, se traducen **en su sitio** (cambio textual, sin tocar firmas ni lógica); queda documentado el porqué. Tests `AddonSourceTest`/`SourceFormatsTest` actualizados (3 asserts). Compilado y validado en el Infinix (`70-fuentes.png`). Commit `da009dec`. Quedan las tandas 2–6 (Listen Together, Estadísticas, Descargas, Cuenta/Discord, Reloj).
- **2026-10-18 (noche)** — **Fase 7: caza de rastros de marca.** Detectados en el móvil al abrir Descargas: (1) el sabor *dev* mostraba **`BitChord Dev`** en lanzador, diálogo de permisos y Ajustes de Android (un `android:label` literal en `app/src/dev/AndroidManifest.xml`) → corregido a **`RAT // PLAYER`**; (2) «No downloaded tracks» era un hardcode en `MainViewModel.kt` → ahora `R.string.no_downloaded_tracks` (es: "No hay canciones descargadas"). Además sustituidos por `RAT` todos los textos **visibles** que aún decían BitChord: colofón de Ajustes, botón de Discord, `UserAgent` de Last.fm, `USER_AGENT` de ArtistFacts, 5 errores de Fuentes (`SourceFormats`/`AddonClient`), 2 de backup + nombre del fichero, y la carpeta `Pictures/BitChord` → `Pictures/RAT`. Auditoría de traducciones: español completo (las 14 claves que faltan son `translatable="false"`). Compilado y tests en verde. Capturas `60-applabel.png`, `61-onb1.png`, `62-downloads-empty.png`, `65-colofon.png`. Sin cambios de lógica.
- **2026-10-18 (tarde)** — **Ajuste de voz: se descarta el primer intento.** Juan lo vio en pantalla y lo llamó **"cringe"** (con razón): tenía apodos, pseudo-terminal decorativo, glitch y amenaza en microcopy trivial. Reescrito a **texto limpio y funcional**, con la voz de Defalt **solo en `onboarding_title` y `listen_together_share_text`** (el marco del asalto lo lleva el chrome visual). Se conserva la marca `RAT` en el código visible (`LyricsShareCard`, `ReplayPoster`, `WatchInstaller`, `ListenBrainzManager`, `FlacTagger`+test, `SourceFormats`+test, textos de `sharedUi` y `wearos`). Compilado y tests en verde. Sin cambios de lógica.
- **2026-10-18** — **Voz de Defalt en los textos (Android).** Investigados los diálogos reales de Defalt (*Watch Dogs* / *Bad Blood*) para una voz no inventada (showman/DJ, granilocuente, burlón, firma `~~(8:>`), con el marco del **asalto a un servidor de música ajeno**. **Bloque A:** `BitChord` → `RAT` en los 19 idiomas (432 apariciones) + `bitchord %1$s` de los avisos; 0 rastros. **Bloque B:** 182 cadenas del español reescritas (164 voz + 18 glitch) y traducido el tramo que estaba en inglés. **Bloque C:** 89 cadenas del inglés. Método por diccionario con validación de marcadores (nombres y `%s`/`%d` intactos). Compilado, instalado y validado en el Infinix. Sin cambios de lógica. *(El Bloque B/C de voz se revierte en la entrada siguiente.)*
- **2026-10-18** — Aportadas `DEFALT_REFERENCES/` y **corregida la identidad de Defalt**: azul `#00229E` + rojo `#DC0736` (sin naranja). Emblema trazado a vector (23 vértices). `DEDSEC_DEFALT_DESIGN.md` actualizado. Sin cambios de código todavía.
- **2026-10-17 (fin de sesión)** — Punto de parada registrado. Plan para la próxima sesión: rebranding completo (identidad propia, icono, nombre, `applicationId`) y revisión de licencia. `git status` limpio.
- **2026-10-17** — Fase 6, cambio 1: onboarding rediseñado (DedSec/Defalt) en `ui/onboarding/OnboardingScreen.kt`. Compilado y validado en el Infinix. Sin cambios de lógica. Commit `fd727fd0`.
- **2026-10-17** — Investigación de estética DedSec/Defalt y creación de `DEDSEC_DEFALT_DESIGN.md` (guía de diseño). Sin cambios de código.
- **2026-10-17** — Fase 5, cambio 1 **validado** en el Infinix: paleta DedSec/Defalt visible y coherente (fondo `#080B10`, acentos cian). Capturas en `/tmp/opencode/`.
- **2026-10-17** — Fase 5, cambio 1: paleta oscura DedSec/Defalt en `ui/theme/Theme.kt` (commit `8a84d3fb`). Compilado OK (`BUILD SUCCESSFUL`). Pendiente validación visual en el móvil.
- **2026-10-17** — Completada Fase 4: mapa de la interfaz (solo lectura). Hallazgo clave: la app usa `MaterialTheme.colorScheme` en 48 archivos → la paleta de `ui/theme/Theme.kt` es el mayor punto de palanca. Sin cambios en el código.
- **2026-10-17** — Completada Fase 3: `BUILD SUCCESSFUL` con JDK 17; 4 APKs `devDebug` generadas. NDK 27.0.12077973 descargado por AGP. Apaño del enlace `android-37`. Sin cambios en el código.
- **2026-10-17** — Completada Fase 2: JDK 17 + `android-tools` (Juan) y Android SDK en `~/Android/Sdk` + `local.properties` (agente). Gradle 8.11.1 configuró `:app` correctamente. Sin cambios en el código de la app.
- **2026-10-17** — Creada rama `dedsec-defalt`. Creado `PROGRESS.md`. Completada la Fase 1 (inspección).
