import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun PascalTriangleView(
    triangleHeight: Int = 5,
    itemTextColor: Color = Color.White,
    itemColor: Color = Color.Black,
    itemClick: (Int) -> Unit = {}
) {
    var lastTouchedRow by remember { mutableIntStateOf(-1) }
    var lastTouchedColumn by remember { mutableIntStateOf(-1) }
    val rotationDegrees = remember { Animatable(0f) }
    val triangleValues = remember { generatePascalsTriangle(triangleHeight) }

    LaunchedEffect(lastTouchedRow, lastTouchedColumn) {
        rotationDegrees.animateTo(
            targetValue = 270f,
            animationSpec = TweenSpec(durationMillis = 1000)
        )
        rotationDegrees.snapTo(0f)
    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                val cellSize = size.width.toFloat() / (triangleHeight + 1)
                triangleValues.forEachIndexed { rowIndex, row ->
                    val y = rowIndex * cellSize + cellSize / 2
                    row.forEachIndexed { columnIndex, value ->
                        val x = size.width / 2 + (columnIndex - rowIndex / 2f) * cellSize
                        if (offset in cellBounds(x, y, cellSize)) {
                            lastTouchedRow = rowIndex
                            lastTouchedColumn = columnIndex
                            itemClick(value)
                        }
                    }
                }
            }
        }) {

        val cellSize = size.width / (triangleHeight + 1)
        triangleValues.forEachIndexed { rowIndex, row ->
            val y = rowIndex * cellSize + cellSize / 2
            row.forEachIndexed { columnIndex, value ->
                val x = size.width / 2 + (columnIndex - rowIndex / 2f) * cellSize

                if (rowIndex == lastTouchedRow && columnIndex == lastTouchedColumn) {
                    rotate(rotationDegrees.value, pivot = Offset(x, y)) {
                        drawCircleAndText(
                            x, y, cellSize / 2, value.toString(),
                            itemColor, itemTextColor
                        )
                    }
                } else {
                    drawCircleAndText(
                        x, y, cellSize / 2, value.toString(),
                        itemColor, itemTextColor
                    )
                }
            }
        }
    }
}

private fun generatePascalsTriangle(height: Int): List<List<Int>> {
    return List(height) { rowIndex ->
        List(rowIndex + 1) { columnIndex ->
            if (columnIndex == 0 || columnIndex == rowIndex) 1
            else generatePascalsTriangle(height - 1)[rowIndex - 1][columnIndex - 1] +
                    generatePascalsTriangle(height - 1)[rowIndex - 1][columnIndex]
        }
    }
}

private fun cellBounds(x: Float, y: Float, cellSize: Float): Rect {
    return Rect(
        left = x - cellSize / 2,
        top = y - cellSize / 2,
        right = x + cellSize / 2,
        bottom = y + cellSize / 2
    )
}

private fun DrawScope.drawCircleAndText(
    x: Float, y: Float, radius: Float, text: String,
    circleColor: Color, textColor: Color
) {
    drawCircle(
        color = circleColor,
        radius = radius,
        center = Offset(x, y)
    )
    drawContext.canvas.nativeCanvas.drawText(
        text,
        x,
        y + radius / 2,
        Paint().apply {
            color = textColor.toArgb()
            textSize = radius
            textAlign = Paint.Align.CENTER
        }
    )
}
