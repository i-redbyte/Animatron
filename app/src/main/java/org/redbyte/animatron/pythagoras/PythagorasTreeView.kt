package org.redbyte.animatron.pythagoras

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.redbyte.animatron.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class PythagorasTreeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var startColor: Int = Color.GREEN
    private var endColor: Int = Color.BLUE
    private var maxDepth: Int = 9
    private val treeHeight: Float = 0.7f

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PythagorasTreeView)
        startColor = typedArray.getColor(R.styleable.PythagorasTreeView_startColor, startColor)
        endColor = typedArray.getColor(R.styleable.PythagorasTreeView_endColor, endColor)
        maxDepth = typedArray.getInt(R.styleable.PythagorasTreeView_maxDepth, maxDepth)
        typedArray.recycle()
    }

    @Suppress("UnnecessaryVariable")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height * treeHeight
        val startX = width / 2f
        val startY = height
        drawPythagorasTree(canvas, startX, startY, 90.0, maxDepth)
    }

    private fun drawPythagorasTree(
        canvas: Canvas,
        x: Float,
        y: Float,
        angle: Double,
        depth: Int
    ) {
        if (depth == 0) return

        val length = (depth * depth *(PI.toFloat()/2))
        val endX = x + length * cos(Math.toRadians(angle)).toFloat()
        val endY = y - length * sin(Math.toRadians(angle)).toFloat()
        val gradient = LinearGradient(x, y, endX, endY, startColor, endColor, Shader.TileMode.CLAMP)
        val paint = Paint().apply {
            isAntiAlias = true
            shader = gradient
            strokeWidth = 8f
            style = Paint.Style.STROKE
        }
        canvas.drawLine(x, y, endX, endY, paint)
        drawPythagorasTree(canvas, endX, endY, angle - 45, depth - 1)
        drawPythagorasTree(canvas, endX, endY, angle + 45, depth - 1)
    }
}
