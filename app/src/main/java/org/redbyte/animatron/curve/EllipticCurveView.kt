package org.redbyte.animatron.curve

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.sqrt

class EllipticCurveView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val axisPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF000000.toInt()
        strokeWidth = 4f
    }

    private val curvePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF00E100.toInt()
        strokeWidth = 4f
        style = Paint.Style.STROKE
    }

    private var a = 0.0
    private var b = 7.0

    private var xMin = -10.0
    private var xMax = 10.0
    private var step = 0.05

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawAxes(canvas)
        drawCurve(canvas)
    }

    private fun drawCurve(canvas: Canvas) {
        val pathPositive = android.graphics.Path()
        val pathNegative = android.graphics.Path()

        val scaleX = width / (xMax - xMin).toFloat()
        val scaleY = height / (xMax - xMin).toFloat()
        val midX = width / 2f
        val midY = height / 2f

        var firstPositive = true
        var firstNegative = true

        var x = xMin
        while (x <= xMax) {
            val discriminant = x * x * x + a * x + b
            if (discriminant >= 0) {
                val y = sqrt(discriminant)
                val screenX = midX + (x * scaleX).toFloat()
                val screenYPositive = midY - (y * scaleY).toFloat()
                val screenYNegative = midY + (y * scaleY).toFloat()

                if (firstPositive) {
                    pathPositive.moveTo(screenX, screenYPositive)
                    firstPositive = false
                } else {
                    pathPositive.lineTo(screenX, screenYPositive)
                }

                if (firstNegative) {
                    pathNegative.moveTo(screenX, screenYNegative)
                    firstNegative = false
                } else {
                    pathNegative.lineTo(screenX, screenYNegative)
                }
            } else {
                firstPositive = true
                firstNegative = true
            }
            x += step
        }

        canvas.drawPath(pathPositive, curvePaint)
        canvas.drawPath(pathNegative, curvePaint)
    }

    private fun drawAxes(canvas: Canvas) {
        val endX = width.toFloat()
        val endY = height.toFloat()
        val startX = 0f
        val startY = 0f
        val arrowLength = 30f
        val arrowWidth = 10f
        canvas.drawLine(startX, height / 2f, endX, height / 2f, axisPaint)
        canvas.drawLine(width / 2f, startY, width / 2f, endY, axisPaint)
        canvas.drawLine(endX, height / 2f, endX - arrowLength, height / 2f - arrowWidth, axisPaint)
        canvas.drawLine(endX, height / 2f, endX - arrowLength, height / 2f + arrowWidth, axisPaint)
        canvas.drawLine(width / 2f, startY, width / 2f - arrowWidth, startY + arrowLength, axisPaint)
        canvas.drawLine(width / 2f, startY, width / 2f + arrowWidth, startY + arrowLength, axisPaint)
    }

    fun setCurveParameters(a: Double, b: Double, xMin: Double, xMax: Double, step: Double) {
        this.a = a
        this.b = b
        this.xMin = xMin
        this.xMax = xMax
        this.step = step
        invalidate()
    }
}
