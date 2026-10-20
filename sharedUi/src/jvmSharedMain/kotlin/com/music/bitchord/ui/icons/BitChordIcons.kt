package com.music.bitchord.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * DEDSEC // DEFALT — the icon family, cut rather than drawn.
 *
 * Three rules run through every glyph, and they are the whole of the style:
 *
 *  - **Stencil.** A closed contour is opened at one corner, one unit wide. The
 *    shape is not a badge any more; it is a mask with a register mark cut out of
 *    it, the way a paint stencil is keyed to its own outline.
 *  - **Facet.** A circle becomes an octagon or a notched ring, a curve becomes
 *    two 45° edges. Nothing in the family is round except [Heart], which is round
 *    because a heart that is not round is a shield.
 *  - **Pixel.** Where the old set had a dot it has a block; where it had a run of
 *    parallel lines one of them steps down a pixel on its way out.
 *
 * The pen is 2.2 with [StrokeCap.Square] and [StrokeJoin.Miter], so ends are cut
 * square and corners come to a point. At 24dp a round cap is a smudge and a
 * fillet is invisible; the family is meant to look *sawn*.
 *
 * Tint is applied by [androidx.compose.material3.Icon]. Every name here is
 * public API of the player and stays where it was.
 */
object BitChordIcons {

    private const val STROKE = 2.2f
    private val stroke = SolidColor(Color.Black)

    /**
     * The play queue: three cut rows, three solid blocks in the gutter.
     */
    val Queue: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_queue",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(8.8f, 6.2f)
                lineTo(13.2f, 6.2f)
                moveTo(8.8f, 12f)
                lineTo(13.2f, 12f)
                moveTo(8.8f, 17.8f)
                lineTo(13.2f, 17.8f)
                moveTo(14.4f, 6.2f)
                lineTo(20.2f, 6.2f)
                moveTo(14.4f, 12f)
                lineTo(20.2f, 12f)
                moveTo(14.4f, 17.8f)
                lineTo(20.2f, 17.8f)
            }

            path(fill = stroke) {
                moveTo(2.3f, 4.9f)
                lineTo(4.9f, 4.9f)
                lineTo(4.9f, 7.5f)
                lineTo(2.3f, 7.5f)
                close()
                moveTo(2.3f, 10.7f)
                lineTo(4.9f, 10.7f)
                lineTo(4.9f, 13.3f)
                lineTo(2.3f, 13.3f)
                close()
                moveTo(2.3f, 16.5f)
                lineTo(4.9f, 16.5f)
                lineTo(4.9f, 19.1f)
                lineTo(2.3f, 19.1f)
                close()
            }
        }.build()
    }

    /**
     * A bubble in facets, opened at the fold, holding two stepped lines.
     */
    val LyricsQuote: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_lyrics_quote",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(6.85f, 3.8f)
                lineTo(18.4f, 3.8f)
                lineTo(20.8f, 6.2f)
                lineTo(20.8f, 14.6f)
                lineTo(18.4f, 17f)
                lineTo(10.6f, 17f)
                lineTo(6.4f, 20.8f)
                lineTo(6.4f, 17f)
                lineTo(5f, 17f)
                lineTo(2.6f, 14.6f)
                lineTo(2.6f, 6.2f)
                lineTo(5.66f, 4.16f)
            }

            path(fill = stroke) {
                moveTo(6.6f, 7.6f)
                lineTo(16.4f, 7.6f)
                lineTo(16.4f, 9.8f)
                lineTo(6.6f, 9.8f)
                close()
                moveTo(6.6f, 11.4f)
                lineTo(13.4f, 11.4f)
                lineTo(13.4f, 13.6f)
                lineTo(8.8f, 13.6f)
                lineTo(8.8f, 12.6f)
                lineTo(6.6f, 12.6f)
                close()
            }
        }.build()
    }

    /**
     * The triangle, with its point cut back and a mitred pen to keep the corners.
     */
    val Play: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_play",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                fill = stroke, stroke = stroke, strokeLineWidth = 1.7f,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(7.2f, 4.9f)
                lineTo(17.6f, 10.9f)
                lineTo(19f, 12f)
                lineTo(17.6f, 13.1f)
                lineTo(7.2f, 19.1f)
                close()
            }
        }.build()
    }

    /**
     * Two bars, leaning: still a pause, but cut on the slant of the family.
     */
    val Pause: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_pause",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(7f, 4.6f)
                lineTo(10.6f, 4.6f)
                lineTo(9.8f, 19.4f)
                lineTo(6.2f, 19.4f)
                close()
                moveTo(13.4f, 4.6f)
                lineTo(17f, 4.6f)
                lineTo(16.2f, 19.4f)
                lineTo(12.6f, 19.4f)
                close()
            }
        }.build()
    }

    /**
     * Two wedges with cut points, parted by the stencil gap.
     */
    val Previous: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_previous",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(19.6f, 5f)
                lineTo(13.4f, 10.6f)
                lineTo(12.4f, 12f)
                lineTo(13.4f, 13.4f)
                lineTo(19.6f, 19f)
                close()
                moveTo(11.4f, 5f)
                lineTo(5.2f, 10.6f)
                lineTo(4.2f, 12f)
                lineTo(5.2f, 13.4f)
                lineTo(11.4f, 19f)
                close()
            }
        }.build()
    }

    /**
     * The same pair, mirrored: the family's fast-forward.
     */
    val Next: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_next",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(4.4f, 5f)
                lineTo(10.6f, 10.6f)
                lineTo(11.6f, 12f)
                lineTo(10.6f, 13.4f)
                lineTo(4.4f, 19f)
                close()
                moveTo(12.6f, 5f)
                lineTo(18.8f, 10.6f)
                lineTo(19.8f, 12f)
                lineTo(18.8f, 13.4f)
                lineTo(12.6f, 19f)
                close()
            }
        }.build()
    }

    /**
     * The tab bar's anchor: a faceted roof with one step, a window punched through,
     *      * and a door cut up out of the floor.
     */
    val Home: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_home",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke, pathFillType = PathFillType.EvenOdd) {
                moveTo(10.4f, 3.4f)
                lineTo(13.2f, 3.4f)
                lineTo(16f, 6.2f)
                lineTo(17.8f, 6.2f)
                lineTo(20.4f, 8.8f)
                lineTo(20.4f, 20.2f)
                lineTo(15.2f, 20.2f)
                lineTo(15.2f, 15.4f)
                lineTo(8.8f, 15.4f)
                lineTo(8.8f, 20.2f)
                lineTo(3.6f, 20.2f)
                lineTo(3.6f, 8.8f)
                close()
                moveTo(14.4f, 12.3f)
                lineTo(17.3f, 12.3f)
                lineTo(17.3f, 14.7f)
                lineTo(14.4f, 14.7f)
                close()
            }
        }.build()
    }

    /**
     * A compass ring, notched at the top, over a two-tone needle.
     */
    val TabExplore: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_tab_explore",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = 2.6f,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(16.18f, 4.81f)
                lineTo(20.04f, 8.67f)
                lineTo(20.04f, 15.33f)
                lineTo(15.33f, 20.04f)
                lineTo(8.67f, 20.04f)
                lineTo(3.96f, 15.33f)
                lineTo(3.96f, 8.67f)
                lineTo(8.67f, 3.96f)
                lineTo(14.13f, 3.96f)
            }

            path(fill = stroke) {
                moveTo(12f, 6.4f)
                lineTo(15.6f, 12f)
                lineTo(12f, 12f)
                close()
                moveTo(12f, 17.6f)
                lineTo(8.4f, 12f)
                lineTo(12f, 12f)
                close()
                moveTo(11f, 11f)
                lineTo(13f, 11f)
                lineTo(13f, 13f)
                lineTo(11f, 13f)
                close()
            }
        }.build()
    }

    /**
     * Three slabs, stepped: a shelf seen from the spine.
     */
    val TabLibrary: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_tab_library",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(8.4f, 3f)
                lineTo(20f, 3f)
                lineTo(20f, 5.9f)
                lineTo(6.4f, 5.9f)
                lineTo(6.4f, 4.4f)
                close()
                moveTo(6.4f, 7.5f)
                lineTo(20f, 7.5f)
                lineTo(20f, 10.4f)
                lineTo(4.6f, 10.4f)
                lineTo(4.6f, 8.9f)
                close()
                moveTo(9.2f, 12f)
                lineTo(19.2f, 12f)
                lineTo(19.2f, 20.6f)
                lineTo(5f, 20.6f)
                lineTo(5f, 13.4f)
                close()
            }
        }.build()
    }

    /**
     * A lens with a corner bitten out of the ring, and a square-cut handle.
     */
    val TabSearch: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_tab_search",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(13.52f, 16.08f)
                lineTo(19.52f, 22.08f)
                lineTo(21.28f, 20.32f)
                lineTo(15.28f, 14.32f)
                close()
            }

            path(fill = stroke, pathFillType = PathFillType.EvenOdd) {
                moveTo(16.25f, 6.84f)
                lineTo(16.25f, 12.36f)
                lineTo(12.36f, 16.25f)
                lineTo(6.84f, 16.25f)
                lineTo(2.95f, 12.36f)
                lineTo(2.95f, 6.84f)
                lineTo(6.84f, 2.95f)
                lineTo(12.36f, 2.95f)
                close()
                moveTo(13.94f, 7.8f)
                lineTo(13.94f, 11.4f)
                lineTo(11.4f, 13.94f)
                lineTo(7.8f, 13.94f)
                lineTo(5.26f, 11.4f)
                lineTo(5.26f, 7.8f)
                lineTo(7.8f, 5.26f)
                lineTo(11.4f, 5.26f)
                close()
                moveTo(14f, 1.9f)
                lineTo(17.2f, 1.9f)
                lineTo(17.2f, 5.1f)
                lineTo(14f, 5.1f)
                close()
            }
        }.build()
    }

    /**
     * The same lens, drawn as one broken line.
     */
    val Search: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_search",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(12.54f, 4.18f)
                lineTo(15.51f, 7.15f)
                lineTo(15.51f, 12.05f)
                lineTo(12.05f, 15.51f)
                lineTo(7.15f, 15.51f)
                lineTo(3.69f, 12.05f)
                lineTo(3.69f, 7.15f)
                lineTo(7.15f, 3.69f)
                lineTo(11.35f, 3.69f)
                moveTo(14.7f, 14.7f)
                lineTo(20.4f, 20.4f)
            }
        }.build()
    }

    /**
     * The compass, at header weight.
     */
    val Explore: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_explore",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(15.99f, 5.02f)
                lineTo(19.76f, 8.79f)
                lineTo(19.76f, 15.21f)
                lineTo(15.21f, 19.76f)
                lineTo(8.79f, 19.76f)
                lineTo(4.24f, 15.21f)
                lineTo(4.24f, 8.79f)
                lineTo(8.79f, 4.24f)
                lineTo(14.11f, 4.24f)
            }

            path(fill = stroke) {
                moveTo(12f, 6.8f)
                lineTo(15.2f, 12f)
                lineTo(12f, 12f)
                close()
                moveTo(12f, 17.2f)
                lineTo(8.8f, 12f)
                lineTo(12f, 12f)
                close()
                moveTo(11.05f, 11.05f)
                lineTo(12.95f, 11.05f)
                lineTo(12.95f, 12.95f)
                lineTo(11.05f, 12.95f)
                close()
            }
        }.build()
    }

    /**
     * Two crossings at 45°, parted at the crossing, ending in chevrons.
     */
    val Shuffle: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_shuffle",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(1.9f, 8.8f)
                lineTo(7.3f, 8.8f)
                lineTo(10.5f, 12f)
                lineTo(13.7f, 15.2f)
                lineTo(19.1f, 15.2f)
                moveTo(1.9f, 15.2f)
                lineTo(7.3f, 15.2f)
                lineTo(10.5f, 12f)
                lineTo(13.7f, 8.8f)
                lineTo(19.1f, 8.8f)
            }

            path(fill = stroke) {
                moveTo(19.1f, 6.7f)
                lineTo(22.1f, 8.8f)
                lineTo(19.1f, 10.9f)
                close()
                moveTo(19.1f, 13.1f)
                lineTo(22.1f, 15.2f)
                lineTo(19.1f, 17.3f)
                close()
            }
        }.build()
    }

    /**
     * A loop with one square corner cut away, and the arrow riding the top edge.
     */
    val Repeat: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_repeat",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(17.2f, 6.4f)
                lineTo(18.8f, 6.4f)
                lineTo(20.6f, 8.2f)
                lineTo(20.6f, 15.8f)
                lineTo(18.8f, 17.6f)
                lineTo(5.6f, 17.6f)
                lineTo(3.8f, 15.8f)
                lineTo(3.8f, 8.2f)
                lineTo(5.6f, 6.4f)
                lineTo(12.2f, 6.4f)
            }

            path(fill = stroke) {
                moveTo(11.8f, 4.1f)
                lineTo(15.6f, 6.4f)
                lineTo(11.8f, 8.7f)
                close()
            }
        }.build()
    }

    /**
     * Two diamonds meeting at a point — the loop without a single curve.
     */
    val Infinity: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_infinity",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(3.2f, 12f)
                lineTo(7.2f, 8f)
                lineTo(11.2f, 12f)
                moveTo(12.8f, 12f)
                lineTo(16.8f, 8f)
                lineTo(20.8f, 12f)
                lineTo(16.8f, 16f)
                lineTo(12.8f, 12f)
                moveTo(11.2f, 12f)
                lineTo(7.2f, 16f)
                lineTo(3.2f, 12f)
            }
        }.build()
    }

    /**
     * A parallelogram head, a square stem, and a double flag of two blocks.
     */
    val MusicNote: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_music_note",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(11.6f, 18.4f)
                lineTo(11.6f, 5.2f)
            }

            path(fill = stroke) {
                moveTo(6.2f, 19.6f)
                lineTo(11.4f, 21f)
                lineTo(11.4f, 17f)
                lineTo(6.2f, 15.6f)
                close()
                moveTo(11.6f, 5.2f)
                lineTo(18.2f, 6.9f)
                lineTo(18.2f, 8.6f)
                lineTo(11.6f, 6.9f)
                close()
                moveTo(11.6f, 7.9f)
                lineTo(17.2f, 9.2f)
                lineTo(17.2f, 10.9f)
                lineTo(11.6f, 9.6f)
                close()
            }
        }.build()
    }

    /**
     * A finder frame around stepped lines: text as a readout.
     */
    val Lyrics: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_lyrics",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(3.4f, 8.2f)
                lineTo(3.4f, 4.2f)
                lineTo(7.4f, 4.2f)
                moveTo(16.6f, 4.2f)
                lineTo(20.6f, 4.2f)
                lineTo(20.6f, 8.2f)
                moveTo(20.6f, 15.8f)
                lineTo(20.6f, 19.8f)
                lineTo(16.6f, 19.8f)
                moveTo(7.4f, 19.8f)
                lineTo(3.4f, 19.8f)
                lineTo(3.4f, 15.8f)
                moveTo(7f, 9f)
                lineTo(9.5f, 9f)
                lineTo(9.5f, 9f)
                lineTo(12f, 9f)
                lineTo(12f, 9f)
                lineTo(14.5f, 9f)
                lineTo(14.5f, 9f)
                lineTo(17f, 9f)
                moveTo(7f, 12.4f)
                lineTo(9.67f, 12.4f)
                lineTo(9.67f, 12.4f)
                lineTo(12.33f, 12.4f)
                lineTo(12.33f, 12.4f)
                lineTo(15f, 12.4f)
                moveTo(7f, 15.8f)
                lineTo(11.8f, 15.8f)
            }
        }.build()
    }

    /**
     * A single chevron, mitred: the smallest shape in the family.
     */
    val ChevronRight: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_chevron_right",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(9f, 4.8f)
                lineTo(16.2f, 12f)
                lineTo(9f, 19.2f)
            }
        }.build()
    }

    /**
     * The like control, empty: a 7-by-6 sprite, and the void is the outline
     *      * eroded one pixel. Blocks, not curves.
     */
    val Heart: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_heart",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke, pathFillType = PathFillType.EvenOdd) {
                moveTo(4.5f, 3f)
                lineTo(10.5f, 3f)
                lineTo(10.5f, 6f)
                lineTo(4.5f, 6f)
                close()
                moveTo(13.5f, 3f)
                lineTo(19.5f, 3f)
                lineTo(19.5f, 6f)
                lineTo(13.5f, 6f)
                close()
                moveTo(1.5f, 6f)
                lineTo(22.5f, 6f)
                lineTo(22.5f, 9f)
                lineTo(1.5f, 9f)
                close()
                moveTo(1.5f, 9f)
                lineTo(22.5f, 9f)
                lineTo(22.5f, 12f)
                lineTo(1.5f, 12f)
                close()
                moveTo(4.5f, 12f)
                lineTo(19.5f, 12f)
                lineTo(19.5f, 15f)
                lineTo(4.5f, 15f)
                close()
                moveTo(7.5f, 15f)
                lineTo(16.5f, 15f)
                lineTo(16.5f, 18f)
                lineTo(7.5f, 18f)
                close()
                moveTo(10.5f, 18f)
                lineTo(13.5f, 18f)
                lineTo(13.5f, 21f)
                lineTo(10.5f, 21f)
                close()
                moveTo(4.5f, 6f)
                lineTo(10.5f, 6f)
                lineTo(10.5f, 9f)
                lineTo(4.5f, 9f)
                close()
                moveTo(13.5f, 6f)
                lineTo(19.5f, 6f)
                lineTo(19.5f, 9f)
                lineTo(13.5f, 9f)
                close()
                moveTo(4.5f, 9f)
                lineTo(19.5f, 9f)
                lineTo(19.5f, 12f)
                lineTo(4.5f, 12f)
                close()
                moveTo(7.5f, 12f)
                lineTo(16.5f, 12f)
                lineTo(16.5f, 15f)
                lineTo(7.5f, 15f)
                close()
                moveTo(10.5f, 15f)
                lineTo(13.5f, 15f)
                lineTo(13.5f, 18f)
                lineTo(10.5f, 18f)
                close()
            }
        }.build()
    }

    /**
     * The same sprite, solid. Liking a track changes the shape, not just the
     *      * tint: white on artwork loses a colour change, not a silhouette.
     */
    val HeartFilled: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_heart_filled",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(4.5f, 3f)
                lineTo(10.5f, 3f)
                lineTo(10.5f, 6f)
                lineTo(4.5f, 6f)
                close()
                moveTo(13.5f, 3f)
                lineTo(19.5f, 3f)
                lineTo(19.5f, 6f)
                lineTo(13.5f, 6f)
                close()
                moveTo(1.5f, 6f)
                lineTo(22.5f, 6f)
                lineTo(22.5f, 9f)
                lineTo(1.5f, 9f)
                close()
                moveTo(1.5f, 9f)
                lineTo(22.5f, 9f)
                lineTo(22.5f, 12f)
                lineTo(1.5f, 12f)
                close()
                moveTo(4.5f, 12f)
                lineTo(19.5f, 12f)
                lineTo(19.5f, 15f)
                lineTo(4.5f, 15f)
                close()
                moveTo(7.5f, 15f)
                lineTo(16.5f, 15f)
                lineTo(16.5f, 18f)
                lineTo(7.5f, 18f)
                close()
                moveTo(10.5f, 18f)
                lineTo(13.5f, 18f)
                lineTo(13.5f, 21f)
                lineTo(10.5f, 21f)
                close()
            }
        }.build()
    }

    /**
     * A cross whose crossbar is parted at the centre.
     */
    val Plus: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_plus",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(12f, 3.6f)
                lineTo(12f, 20.4f)
                moveTo(3.6f, 12f)
                lineTo(10.7f, 12f)
                moveTo(13.3f, 12f)
                lineTo(20.4f, 12f)
            }
        }.build()
    }

    /**
     * The mark, with a block welded to its elbow.
     */
    val Check: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_check",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(4.4f, 12.8f)
                lineTo(9.4f, 17.8f)
                lineTo(19.6f, 7.2f)
            }

            path(fill = stroke) {
                moveTo(8.25f, 16.65f)
                lineTo(10.55f, 16.65f)
                lineTo(10.55f, 18.95f)
                lineTo(8.25f, 18.95f)
                close()
            }
        }.build()
    }

    /**
     * An arrow into a tray that is two brackets, plus two alignment blocks.
     */
    val Download: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_download",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(12f, 3.8f)
                lineTo(12f, 12.4f)
                moveTo(7.6f, 8.2f)
                lineTo(12f, 12.6f)
                lineTo(16.4f, 8.2f)
                moveTo(3.8f, 15.4f)
                lineTo(3.8f, 19.8f)
                lineTo(11f, 19.8f)
                moveTo(13f, 19.8f)
                lineTo(20.2f, 19.8f)
                lineTo(20.2f, 15.4f)
            }

            path(fill = stroke) {
                moveTo(7.6f, 15.1f)
                lineTo(9.2f, 15.1f)
                lineTo(9.2f, 16.7f)
                lineTo(7.6f, 16.7f)
                close()
                moveTo(14.8f, 15.1f)
                lineTo(16.4f, 15.1f)
                lineTo(16.4f, 16.7f)
                lineTo(14.8f, 16.7f)
                close()
            }
        }.build()
    }

    /**
     * An octagonal case with a gap at four o'clock, stepped hands, and a pin.
     */
    val Clock: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_clock",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(14.72f, 20.13f)
                lineTo(8.63f, 20.13f)
                lineTo(3.87f, 15.37f)
                lineTo(3.87f, 8.63f)
                lineTo(8.63f, 3.87f)
                lineTo(15.37f, 3.87f)
                lineTo(20.13f, 8.63f)
                lineTo(20.13f, 15.37f)
                lineTo(15.83f, 19.67f)
                moveTo(12f, 7.2f)
                lineTo(12f, 11f)
                lineTo(13.6f, 12.6f)
                lineTo(16.6f, 12.6f)
            }

            path(fill = stroke) {
                moveTo(10.8f, 10.8f)
                lineTo(13.2f, 10.8f)
                lineTo(13.2f, 13.2f)
                lineTo(10.8f, 13.2f)
                close()
            }
        }.build()
    }

    /**
     * A faceted shield with a square void where the point should be.
     */
    val Pin: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_pin",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(11.58f, 20.11f)
                lineTo(4.8f, 12.2f)
                lineTo(4.8f, 7.4f)
                lineTo(12f, 3.4f)
                lineTo(19.2f, 7.4f)
                lineTo(19.2f, 12.2f)
                lineTo(12.42f, 20.11f)
            }

            path(fill = stroke) {
                moveTo(9.9f, 9.6f)
                lineTo(14.1f, 9.6f)
                lineTo(14.1f, 13.8f)
                lineTo(9.9f, 13.8f)
                close()
            }
        }.build()
    }

    /**
     * A gauge on a stand, stepped needle, blocks at the hub and the tip.
     */
    val Performance: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_performance",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(4.4f, 16.6f)
                lineTo(4.4f, 13.8f)
                lineTo(7.2f, 10.2f)
                lineTo(10.4f, 7.8f)
                lineTo(13.6f, 7.8f)
                lineTo(16.8f, 10.2f)
                lineTo(19.6f, 13.8f)
                lineTo(19.6f, 16.6f)
                moveTo(4.4f, 16.6f)
                lineTo(4.4f, 19.8f)
                lineTo(9.2f, 19.8f)
                moveTo(14.8f, 19.8f)
                lineTo(19.6f, 19.8f)
                lineTo(19.6f, 16.6f)
                moveTo(12f, 16.6f)
                lineTo(16.4f, 10.2f)
            }

            path(fill = stroke) {
                moveTo(10.8f, 15.4f)
                lineTo(13.2f, 15.4f)
                lineTo(13.2f, 17.8f)
                lineTo(10.8f, 17.8f)
                close()
                moveTo(15.5f, 9.3f)
                lineTo(17.3f, 9.3f)
                lineTo(17.3f, 11.1f)
                lineTo(15.5f, 11.1f)
                close()
            }
        }.build()
    }

    /**
     * A finder frame with a stepped trace inside: frames over time.
     */
    val FrameRate: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_frame_rate",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(3.4f, 8f)
                lineTo(3.4f, 4f)
                lineTo(7.4f, 4f)
                moveTo(16.6f, 4f)
                lineTo(20.6f, 4f)
                lineTo(20.6f, 8f)
                moveTo(20.6f, 16f)
                lineTo(20.6f, 20f)
                lineTo(16.6f, 20f)
                moveTo(7.4f, 20f)
                lineTo(3.4f, 20f)
                lineTo(3.4f, 16f)
                moveTo(6.8f, 15.2f)
                lineTo(10f, 15.2f)
                lineTo(10f, 9.4f)
                lineTo(14.2f, 9.4f)
                lineTo(14.2f, 12.8f)
                lineTo(17.2f, 12.8f)
            }
        }.build()
    }

    /**
     * Two lids and a chamfered case, in outline.
     */
    val Library: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_library",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(8.4f, 3.8f)
                lineTo(18.8f, 3.8f)
                lineTo(20.4f, 5.4f)
                lineTo(6.8f, 5.4f)
                lineTo(6.8f, 4.6f)
                moveTo(5f, 8f)
                lineTo(19.6f, 8f)
                lineTo(19.6f, 9.6f)
                lineTo(5f, 9.6f)
                lineTo(5f, 8f)
                moveTo(6.6f, 12.2f)
                lineTo(18f, 12.2f)
                lineTo(19.4f, 13.6f)
                lineTo(19.4f, 19f)
                lineTo(18f, 20.4f)
                lineTo(6f, 20.4f)
                lineTo(4.6f, 19f)
                lineTo(4.6f, 13.6f)
                lineTo(5.58f, 12.62f)
            }
        }.build()
    }

    /**
     * Four squares, each with one corner cut the other way.
     */
    val GridView: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_grid_view",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(3.4f, 3.4f)
                lineTo(8.6f, 3.4f)
                lineTo(8.6f, 8.2f)
                lineTo(8.2f, 8.6f)
                lineTo(3.4f, 8.6f)
                close()
                moveTo(15.4f, 3.4f)
                lineTo(20.6f, 3.4f)
                lineTo(20.6f, 8.6f)
                lineTo(15.8f, 8.6f)
                lineTo(15.4f, 8.2f)
                close()
                moveTo(3.4f, 15.4f)
                lineTo(8.2f, 15.4f)
                lineTo(8.6f, 15.8f)
                lineTo(8.6f, 20.6f)
                lineTo(3.4f, 20.6f)
                close()
                moveTo(15.8f, 15.4f)
                lineTo(20.6f, 15.4f)
                lineTo(20.6f, 20.6f)
                lineTo(15.4f, 20.6f)
                lineTo(15.4f, 15.8f)
                close()
            }
        }.build()
    }

    /**
     * Three rows of stepped rules, each against its block.
     */
    val ListView: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_list_view",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(
                stroke = stroke, strokeLineWidth = STROKE,
                strokeLineCap = StrokeCap.Square, strokeLineJoin = StrokeJoin.Miter,
            ) {
                moveTo(8.6f, 6.2f)
                lineTo(11.5f, 6.2f)
                lineTo(11.5f, 6.2f)
                lineTo(14.4f, 6.2f)
                lineTo(14.4f, 6.2f)
                lineTo(17.3f, 6.2f)
                lineTo(17.3f, 6.2f)
                lineTo(20.2f, 6.2f)
                moveTo(8.6f, 12f)
                lineTo(11.87f, 12f)
                lineTo(11.87f, 12f)
                lineTo(15.13f, 12f)
                lineTo(15.13f, 12f)
                lineTo(18.4f, 12f)
                moveTo(8.6f, 17.8f)
                lineTo(11.27f, 17.8f)
                lineTo(11.27f, 17.8f)
                lineTo(13.93f, 17.8f)
                lineTo(13.93f, 17.8f)
                lineTo(16.6f, 17.8f)
            }

            path(fill = stroke) {
                moveTo(2.7f, 4.9f)
                lineTo(5.3f, 4.9f)
                lineTo(5.3f, 7.5f)
                lineTo(2.7f, 7.5f)
                close()
                moveTo(2.7f, 10.7f)
                lineTo(5.3f, 10.7f)
                lineTo(5.3f, 13.3f)
                lineTo(2.7f, 13.3f)
                close()
                moveTo(2.7f, 16.5f)
                lineTo(5.3f, 16.5f)
                lineTo(5.3f, 19.1f)
                lineTo(2.7f, 19.1f)
                close()
            }
        }.build()
    }

    /**
     * Three blocks, not three dots.
     */
    val MoreVert: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_more_vert",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(10.6f, 4f)
                lineTo(13.4f, 4f)
                lineTo(13.4f, 6.8f)
                lineTo(10.6f, 6.8f)
                close()
                moveTo(10.6f, 10.6f)
                lineTo(13.4f, 10.6f)
                lineTo(13.4f, 13.4f)
                lineTo(10.6f, 13.4f)
                close()
                moveTo(10.6f, 17.2f)
                lineTo(13.4f, 17.2f)
                lineTo(13.4f, 20f)
                lineTo(10.6f, 20f)
                close()
            }
        }.build()
    }

    /**
     * The same three blocks, laid down: the player's menu.
     */
    val MoreHoriz: ImageVector by lazy {
        ImageVector.Builder(
            name = "bc_more_horiz",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f,
        ).apply {
            path(fill = stroke) {
                moveTo(4f, 10.6f)
                lineTo(6.8f, 10.6f)
                lineTo(6.8f, 13.4f)
                lineTo(4f, 13.4f)
                close()
                moveTo(10.6f, 10.6f)
                lineTo(13.4f, 10.6f)
                lineTo(13.4f, 13.4f)
                lineTo(10.6f, 13.4f)
                close()
                moveTo(17.2f, 10.6f)
                lineTo(20f, 10.6f)
                lineTo(20f, 13.4f)
                lineTo(17.2f, 13.4f)
                close()
            }
        }.build()
    }
}
