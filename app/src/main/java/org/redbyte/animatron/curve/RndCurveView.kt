package org.redbyte.animatron.curve

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class RndCurveView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val axisPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF000000.toInt()
        strokeWidth = 4f
    }

    private val curvePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFFFF0000.toInt()
        strokeWidth = 4f
    }

    private var drawCurve = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawAxes(canvas)
        if (drawCurve) {
            drawRandomCurve(canvas)
        }
    }

    private fun drawRandomCurve(canvas: Canvas) {
        TODO("Not yet implemented")
    }

    private fun drawAxes(canvas: Canvas) {
        // Оси
        canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), axisPaint)
        canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, axisPaint)
    }


    override fun performClick(): Boolean {
        if (super.performClick()) return true

        drawCurve = !drawCurve
        invalidate()
        return true
    }
}
