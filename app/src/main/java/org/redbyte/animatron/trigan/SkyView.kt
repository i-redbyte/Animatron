package org.redbyte.animatron.trigan

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class SkyView : View {
    private val paint = Paint()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 1..600) {
            val x = Random.nextInt(0, width).toFloat()
            val y = Random.nextInt(0, height).toFloat()
            val r = Random.nextInt(1, MAX_RADIUS).toFloat()
            if ((x + y).toInt() and 1 == 0)
                canvas.drawPoint(x, y, paint)
            else
                canvas.drawCircle(x, y, r, paint)
        }
    }

    companion object {
        const val MAX_RADIUS = 5
    }
}