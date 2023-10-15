package org.redbyte.animatron.pythagoras

import android.animation.ValueAnimator
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
    private var currentDepth: Int = 0

    private val animator: ValueAnimator

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PythagorasTreeView)
        startColor = typedArray.getColor(R.styleable.PythagorasTreeView_startColor, startColor)
        endColor = typedArray.getColor(R.styleable.PythagorasTreeView_endColor, endColor)
        maxDepth = typedArray.getInt(R.styleable.PythagorasTreeView_maxDepth, maxDepth)
        typedArray.recycle()

        animator = ValueAnimator.ofInt(0, maxDepth)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 3000L
        animator.addUpdateListener {
            currentDepth = it.animatedValue as Int
            invalidate()
        }
        animator.start()
    }

    @Suppress("UnnecessaryVariable")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height * treeHeight
        val startX = width / 2f
        val startY = height
        drawPythagorasTree(canvas, startX, startY, 90.0, currentDepth)
    }

    private fun drawPythagorasTree(
        canvas: Canvas,
        x: Float,
        y: Float,
        angle: Double,
        depth: Int
    ) {
        if (depth == 0) return

        val colorRatio = depth.toFloat() / maxDepth.toFloat()
        val currentColor = blendColors(startColor, endColor, colorRatio)

        val length = (depth * depth * (PI.toFloat() / 2))
        val endX = x + length * cos(Math.toRadians(angle)).toFloat()
        val endY = y - length * sin(Math.toRadians(angle)).toFloat()

        val paint = Paint().apply {
            isAntiAlias = true
            color = currentColor
            strokeWidth = 8f
            style = Paint.Style.STROKE
        }

        canvas.drawLine(x, y, endX, endY, paint)
        drawPythagorasTree(canvas, endX, endY, angle - 45, depth - 1)
        drawPythagorasTree(canvas, endX, endY, angle + 45, depth - 1)
    }

    private fun blendColors(startColor: Int, endColor: Int, ratio: Float): Int {
        val r = (Color.red(startColor) * ratio + Color.red(endColor) * (1 - ratio)).toInt()
        val g = (Color.green(startColor) * ratio + Color.green(endColor) * (1 - ratio)).toInt()
        val b = (Color.blue(startColor) * ratio + Color.blue(endColor) * (1 - ratio)).toInt()
        return Color.rgb(r, g, b)
    }
}
