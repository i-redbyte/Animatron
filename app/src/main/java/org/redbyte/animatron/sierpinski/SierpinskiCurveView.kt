package org.redbyte.animatron.sierpinski

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import org.redbyte.animatron.R
import org.redbyte.animatron.base.extensions.dp

class SierpinskiCurveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var level = 3
    private val paint = Paint()
    private var baseX = 0f
    private var baseY = 0f
    private var distance: Int = 250.dp()
    private lateinit var canvas: Canvas

    //TODO: Make customization through attributes
    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4.dp().toFloat()
        paint.color = Color.BLACK
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SierpinskiCurveView,
            0,
            0
        ).apply {
            try {
                level = getInteger(R.styleable.SierpinskiCurveView_level, 3)
                distance = getDimension(R.styleable.SierpinskiCurveView_distance, 0.0f).toInt()
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas ?: return
        drawSierpinskiCurve()
    }

    fun setCurveLevel(level: Int) {
        this.level = level
    }

    fun setColor(@ColorInt color: Int) {
        paint.color = color
    }

    private fun drawSierpinskiCurve() {
        for (i in level downTo 1) distance /= 2
        //Todo: change to center view?
        //      xx = x + width / curveOrder
        //      yy = y + height / curveOrder

        moveToXY(8.dp(), 8.dp())
        curveFirst(level)
        lineRel(+distance, +distance)
        curveSecond(level)
        lineRel(-distance, +distance)
        curveThird(level)
        lineRel(-distance, -distance)
        curveFourth(level)
        lineRel(+distance, -distance)
    }

    private fun lineRel(offestX: Int, offsetY: Int) {
        canvas.drawLine(baseX, baseY, baseX + offestX, baseY + offsetY, paint)
        baseX += offestX
        baseY += offsetY
    }

    private fun moveToXY(x: Int, y: Int) {
        baseX = x.toFloat()
        baseY = y.toFloat()
    }

    private fun curveFirst(level: Int) {
        if (level > 0) {
            curveFirst(level - 1)
            lineRel(+distance, +distance)
            curveSecond(level - 1)
            lineRel(+2 * distance, 0)
            curveFourth(level - 1)
            lineRel(+distance, -distance)
            curveFirst(level - 1)
        }
    }

    private fun curveSecond(level: Int) {
        if (level > 0) {
            curveSecond(level - 1)
            lineRel(-distance, +distance)
            curveThird(level - 1)
            lineRel(0, +2 * distance)
            curveFirst(level - 1)
            lineRel(+distance, +distance)
            curveSecond(level - 1)
        }
    }

    private fun curveThird(level: Int) {
        if (level > 0) {
            curveThird(level - 1)
            lineRel(-distance, -distance)
            curveFourth(level - 1)
            lineRel(-2 * distance, 0)
            curveSecond(level - 1)
            lineRel(-distance, +distance)
            curveThird(level - 1)
        }
    }

    private fun curveFourth(level: Int) {
        if (level > 0) {
            curveFourth(level - 1)
            lineRel(+distance, -distance)
            curveFirst(level - 1)
            lineRel(0, -2 * distance)
            curveThird(level - 1)
            lineRel(-distance, -distance)
            curveFourth(level - 1)
        }
    }

}
