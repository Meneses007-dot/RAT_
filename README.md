<div align="center">

<br/>

# RAT_

### DEDSEC // DEFALT — Custom Edition

Reproductor de música con una identidad visual inspirada en *Watch Dogs*.
Fork no oficial de [BitChord](https://github.com/kushagrasinghx/BitChord).

<br/>

[![License](https://img.shields.io/badge/license-GPLv3-blue?style=for-the-badge)](LICENSE)
[![Upstream](https://img.shields.io/badge/upstream-BitChord-0d1117?style=for-the-badge)](https://github.com/kushagrasinghx/BitChord)
[![Platform](https://img.shields.io/badge/android-8.0%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)](#compilar)

</div>

> [!IMPORTANT]
> **Este repositorio es un fork personal, no oficial, de BitChord.**
> El proyecto original es de [Kushagra Singh (@kushagrasinghx)](https://github.com/kushagrasinghx/BitChord) y todo el crédito del motor de reproducción, la arquitectura y la enorme mayoría del código es suyo. Acá solo se agrega una capa de personalización visual y de textos.
>
> RAT_ **no** está afiliado a BitChord, a YouTube/Google ni a Ubisoft. *Watch Dogs* y sus marcas pertenecen a Ubisoft; este es un proyecto de fan, sin recursos oficiales del juego.

---

## Qué es

RAT_ es BitChord con una capa de *skins* encima: mismo reproductor, misma lógica de red y de audio, otra estética. La idea es que la app se sienta como un reproductor clandestino dentro del universo de *Watch Dogs 2* —terminal DedSec, acentos Defalt, glitches y scanlines— sin romper ninguna función existente.

| | |
|---|---|
| **Base** | [BitChord](https://github.com/kushagrasinghx/BitChord) (Kotlin Multiplatform: Android, escritorio, WearOS, Android TV y web) |
| **App ID (flavor `dev`)** | `com.dev.ratplayer` — convive instalada junto a la original |
| **Nombre visible** | `RAT_` |
| **Licencia** | GPLv3 (heredada del upstream) |
| **Estado** | Uso personal, en desarrollo |

## Qué cambia respecto a BitChord

- **Identidad visual DedSec // Defalt**: paleta oscura medida sobre referencias reales, fondos tipo terminal, rejillas, marcas de agua y texturas.
- **Emblema y wordmark vectoriales**, trazados desde cero (no se usan assets oficiales del juego).
- **Tipografía, iconografía, barra inferior y mini-reproductor** rediseñados.
- **Pantalla de reproducción** con visor propio, velo medido según la carátula y firma del visor.
- **Onboarding y textos** con la voz de Defalt (español e inglés, más las traducciones heredadas).
- **Flavor `dev` renombrado a `RAT_`** para poder tener las dos builds instaladas a la vez.

El detalle sesión por sesión está en [PROGRESS.md](PROGRESS.md), y la guía de estética en [DEDSEC_DEFALT_DESIGN.md](DEDSEC_DEFALT_DESIGN.md). Las imágenes de referencia están en [`DEFALT_REFERENCES/`](DEFALT_REFERENCES).

> El catálogo de funciones (búsqueda, Hi-Res, crossfade, automix, descargas offline, letras sincronizadas, Spotify, Discord RPC, scrobbling, etc.) es **el de BitChord tal cual**: no se modificó el comportamiento. Ver la lista completa en el [README del upstream](https://github.com/kushagrasinghx/BitChord#features).

## Compilar

Requisitos: JDK 17, Android SDK con `compileSdk 37` (`minSdk 26`) y las variables de entorno habituales de Android.

```bash
# APK de la edición RAT_ (flavor dev, se instala como com.dev.ratplayer)
./gradlew :app:assembleDevDebug

# APK de la build original (flavor prod, mismo appId que BitChord)
./gradlew :app:assembleProdDebug
```

El APK queda en `app/build/outputs/apk/dev/debug/`. Para firmar una build de release hace falta tu propio `keystore.properties` (ver `keystore.properties.example`); sin eso, el release sale sin firmar.

App de escritorio (Linux/macOS/Windows) con Java 21:

```bash
./gradlew :desktopApp:run
```

Más detalles en [DESKTOP.md](DESKTOP.md).

## Créditos

- **[BitChord](https://github.com/kushagrasinghx/BitChord)** — Kushagra Singh y [sus contribuidores](https://github.com/kushagrasinghx/BitChord/graphs/contributors). Sin su trabajo este fork no existiría.
- Animación de letras estilo Apple: [binimum/am-lyrics](https://github.com/binimum/am-lyrics).
- Proveedores de letras y demás librerías de terceros: los mismos que documenta el upstream.
- Dirección de arte DedSec // Defalt: inspirada en el trabajo de Ubisoft para *Watch Dogs* (2014) y *Watch Dogs 2* (2016). Proyecto de fan, sin afiliación.

Este repositorio **no** es el lugar para donar al proyecto original. Si querés apoyar a BitChord, hacelo en su [página de releases](https://github.com/kushagrasinghx/BitChord).

## Licencia y aviso legal

Distribuido bajo **GNU General Public License v3.0**. Ver [LICENSE](LICENSE).

Al ser un derivado de BitChord, se mantiene la licencia GPLv3: si distribuís una build de este código, tenés que poner a disposición el código fuente correspondiente bajo la misma licencia.

- **Sin alojamiento de contenido:** ni BitChord ni este fork alojan, suben o almacenan música. La app es una interfaz hacia APIs públicas o autenticadas por el usuario.
- **Sin afiliación:** RAT_ no está asociado a Google LLC, YouTube Music, BitChord, Ubisoft ni a sus empresas matrices.
- **Uso personal:** sos responsable de que tu uso cumpla las leyes de copyright de tu país y los términos de servicio de las plataformas que uses.
