package org.redbyte.animatron.compost.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import org.redbyte.animatron.compost.ui.theme.Purple200

@Composable
fun AnimationArc(color: Color = Purple200) {
    val transition = rememberInfiniteTransition()
    val animatedFloat by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Restart)
    )
    val stroke = Stroke(25f)
    Canvas(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp)
    ) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val insideRadius = radius - stroke.width
        val topLeftOffset = Offset(10f, 10f)
        val size = Size(insideRadius * 2, insideRadius * 2)
        var rotationAngle = animatedFloat - 50f

        drawArc(
            color = color,
            startAngle = rotationAngle,
            sweepAngle = 150f,
            topLeft = topLeftOffset,
            size = size,
            useCenter = false,
            style = stroke,
        )
        rotationAngle += 40
    }
}
