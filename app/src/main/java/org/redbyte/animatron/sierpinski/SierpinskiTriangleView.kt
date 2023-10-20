package org.redbyte.animatron.sierpinski

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import org.redbyte.animatron.R


class SierpinskiTriangleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var depth = 6
    private val paint = Paint()
    private val color1: Int
    private val color2: Int
    private var useColor1 = true

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SierpinskiTriangleView)
        color1 = typedArray.getColor(R.styleable.SierpinskiTriangleView_color1, Color.BLACK)
        color2 = typedArray.getColor(R.styleable.SierpinskiTriangleView_color2, Color.BLACK)
        depth = typedArray.getColor(R.styleable.SierpinskiTriangleView_depth, depth)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        drawSierpinskiTriangle(canvas, depth, 0f, height, width, -height)
    }

    private fun drawSierpinskiTriangle(canvas: Canvas, depth: Int, x: Float, y: Float, width: Float, height: Float) {
        if (depth == 0) {
            val path = Path()
            path.moveTo(x, y)
            path.lineTo(x + width, y)
            path.lineTo(x + width / 2, y + height)
            path.close()

            // Выбираем цвет в зависимости от переменной useColor1
            paint.color = if (useColor1) color1 else color2
            useColor1 = !useColor1

            canvas.drawPath(path, paint)
        } else {
            val halfWidth = width / 2
            val halfHeight = height / 2
            val midX = x + halfWidth
            val midY = y + halfHeight

            // Рекурсивно отрисовываем четыре меньших треугольника
            drawSierpinskiTriangle(canvas, depth - 1, x, y, halfWidth, halfHeight)
            drawSierpinskiTriangle(canvas, depth - 1, midX, y, halfWidth, halfHeight)
            drawSierpinskiTriangle(canvas, depth - 1, x + halfWidth / 2, midY, halfWidth, halfHeight)
        }
    }
}
