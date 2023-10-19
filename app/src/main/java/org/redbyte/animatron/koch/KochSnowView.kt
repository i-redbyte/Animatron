package org.redbyte.animatron.koch

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.redbyte.animatron.R
import kotlin.math.sqrt
import android.animation.ValueAnimator


class KochSnowView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var level: Int = 3
    private var startColor: Int = 0
    private var endColor: Int = 0

    private val paint = Paint().apply {
        strokeWidth = 16f
    }

    private val animator: ValueAnimator

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.KochSnowView)
        startColor = typedArray.getColor(R.styleable.KochSnowView_startColor, 0)
        endColor = typedArray.getColor(R.styleable.KochSnowView_endColor, 0)
        level = typedArray.getColor(R.styleable.KochSnowView_level, level)
        animator = ValueAnimator
            .ofInt(0, level)
            .apply {
                duration = 1100 // Set the duration of the animation (in milliseconds)
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
                addUpdateListener { animation ->
                    level = animation.animatedValue as Int
                    invalidate()
                }
            }
        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()

        // Определяем точки для начального треугольника
        val p1 = Point((0.5f * width).toInt(), 0)
        val p2 = Point(0, (0.866f * height).toInt())
        val p3 = Point(width.toInt(), (0.866f * height).toInt())

        // Рисуем снежинку Коха
        drawKochSnowflake(canvas, p1, p2, level, 0f)
        drawKochSnowflake(canvas, p2, p3, level, 120f)
        drawKochSnowflake(canvas, p3, p1, level, 240f)
    }

    private fun drawKochSnowflake(
        canvas: Canvas,
        p1: Point,
        p2: Point,
        level: Int,
        rotation: Float
    ) {
        if (level == 0) {
            // Рисуем отрезок между двумя точками с градиентом
            val gradient = LinearGradient(
                p1.x.toFloat(),
                p1.y.toFloat(),
                p2.x.toFloat(),
                p2.y.toFloat(),
                startColor,
                endColor,
                Shader.TileMode.CLAMP
            )
            paint.shader = gradient
            canvas.drawLine(p1.x.toFloat(), p1.y.toFloat(), p2.x.toFloat(), p2.y.toFloat(), paint)
            paint.shader = null
        } else {
            // Разделяем отрезок на четыре части
            val p3 = Point(p1.x + (p2.x - p1.x) / 3, p1.y + (p2.y - p1.y) / 3)
            val p4 = Point(p1.x + 2 * (p2.x - p1.x) / 3, p1.y + 2 * (p2.y - p1.y) / 3)

            // Находим серединную точку между p3 и p4
            val v = Point(p4.x - p3.x, p4.y - p3.y)
            val p5 = Point(
                (p3.x + v.x / 2 - v.y * sqrt(3.0) / 2).toInt(),
                (p3.y + v.x * sqrt(3.0) / 2 + v.y / 2).toInt()
            )

            // Рекурсивно рисуем снежинку для каждой части с градиентом
            drawKochSnowflake(canvas, p1, p3, level - 1, rotation)
            drawKochSnowflake(canvas, p3, p5, level - 1, rotation)
            drawKochSnowflake(canvas, p5, p4, level - 1, rotation)
            drawKochSnowflake(canvas, p4, p2, level - 1, rotation)
        }
    }
}
