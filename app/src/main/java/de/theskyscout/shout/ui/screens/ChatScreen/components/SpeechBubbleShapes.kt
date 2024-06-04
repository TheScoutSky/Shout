package de.theskyscout.shout.ui.screens.ChatScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class SpeechBubbleShapeIn(
    private val cornerRadius: Dp,
    private val tailSize: Dp
) : Shape{

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val tailSizePx = with(density) { tailSize.toPx() }
        val path = Path().apply {

            // Draw the bubble
            addRoundRect(
                RoundRect(
                    left = tailSizePx,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    radiusX = cornerRadiusPx,
                    radiusY = cornerRadiusPx
                )
            )

            // Set the tail position
            val y = (size.height - cornerRadiusPx ) - (size.height / 2) * 3/4
            moveTo(tailSizePx, y)

            // Draw the tail
            val x1 = (tailSizePx)
            val y1 = y + 1/3 * cornerRadiusPx
            val x2 = (tailSizePx + 5)
            val y2 = y + cornerRadiusPx
            val x3 = 0f
            val y3 = y + 2 * cornerRadiusPx
            cubicTo(
                x1, y1,
                x2, y2,
                x3, y3
            )
            lineTo(tailSizePx, size.height - cornerRadiusPx)
            close()
        }

        return Outline.Generic(path)
    }
}

class SpeechBubbleShapeOut(
    private val cornerRadius: Dp,
    private val tailSize: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val tailSizePx = with(density) { tailSize.toPx() }
        val path = Path().apply {

            // Draw the bubble
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width - tailSizePx,
                    bottom = size.height,
                    radiusX = cornerRadiusPx,
                    radiusY = cornerRadiusPx
                )
            )

            // Set the tail position
            val y = (size.height - cornerRadiusPx) - (size.height / 2) * 3/4
            moveTo(size.width - tailSizePx, y)

            // Draw the tail
            val x1 = size.width - tailSizePx
            val y1 = y + 1/3 * cornerRadiusPx
            val x2 = size.width - (tailSizePx + 5)
            val y2 = y + cornerRadiusPx
            val x3 = size.width
            val y3 = y + 2 * cornerRadiusPx
            cubicTo(
                x1, y1,
                x2, y2,
                x3, y3
            )
            lineTo(size.width - tailSizePx, size.height - cornerRadiusPx)
            close()
        }

        return Outline.Generic(path)
    }
}

@Preview
@Composable
fun MessageOutPreview() {
   Box (
       modifier = Modifier.fillMaxSize().background(color = Color.White)
   ) {
       SpeechBubble(
           tail = true,
           type = SpeechBubbleType.IN,
           color = Color.DarkGray,
       ) {
           Text(text = "Hallo Welt",
               modifier = Modifier.padding(16.dp))
       }

       SpeechBubble(
           tail = true,
           type = SpeechBubbleType.OUT,
           color = Color.Blue,
           modifier = Modifier.offset(x = 200.dp)
       ) {
           Text(
               text = "Hallo Welt",
               modifier = Modifier.padding(16.dp)
           )
       }
   }
}

@Composable
fun SpeechBubble(
    tail: Boolean = false,
    type: SpeechBubbleType = SpeechBubbleType.OUT,
    cornerRadius: Dp = 10.dp,
    tailSize: Dp = 10.dp,
    color: Color = Color.White,
    modifier: Modifier = Modifier,
    composable: @Composable (BoxScope.() -> Unit),
) {
    var shape = when(type) {
        SpeechBubbleType.IN -> SpeechBubbleShapeIn(cornerRadius, tailSize)
        SpeechBubbleType.OUT -> SpeechBubbleShapeOut(cornerRadius, tailSize)
    }
    if (!tail) shape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = modifier
            .clip(shape)
            .background(color = color)
    ) {
        Box(
            modifier = Modifier.offset(
                x = if (type == SpeechBubbleType.OUT) (-(tailSize/2).value).dp
                else if(!tail) 0.dp
                else (tailSize/2).value.dp)
        ) {
            composable()
        }
    }
}

enum class SpeechBubbleType {
    IN,
    OUT
}