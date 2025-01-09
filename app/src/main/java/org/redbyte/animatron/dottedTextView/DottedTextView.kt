package org.redbyte.animatron.dottedTextView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.redbyte.animatron.R

class DottedTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 40f
    }

    private val dotPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    private var leftText: String = ""
    private var rightText: String = ""

    private var lineHeight = 0f
    private var totalHeight = 0
    private var baseLineOffset = 0f
    private var shouldMoveRightTextToNewLine = false

    init {
        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.DottedTextView, defStyleAttr, 0)
            leftText = typedArray.getString(R.styleable.DottedTextView_leftText) ?: ""
            rightText = typedArray.getString(R.styleable.DottedTextView_rightText) ?: ""
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)

        totalHeight = calculateHeight(width)
        setMeasuredDimension(width, totalHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val leftTextWidth = paint.measureText(leftText)
        val rightTextWidth = paint.measureText(rightText)
        lineHeight = paint.textSize * 1.5f

        val fontMetrics = paint.fontMetrics
        baseLineOffset = lineHeight - fontMetrics.descent

        if (!shouldMoveRightTextToNewLine) {
            canvas.drawText(leftText, 0f, baseLineOffset, paint)

            val startX = leftTextWidth + 10
            val endX = width - rightTextWidth - 10
            var x = startX
            while (x < endX) {
                canvas.drawCircle(x, baseLineOffset, 3f, dotPaint)
                x += 20
            }

            canvas.drawText(rightText, width - rightTextWidth, baseLineOffset, paint)
        } else {
            canvas.drawText(leftText, 0f, baseLineOffset, paint)

            val startX = leftTextWidth + 10
            val endX = width.toFloat()
            var x = startX
            while (x < endX) {
                canvas.drawCircle(x, baseLineOffset, 3f, dotPaint)
                x += 20
            }

            canvas.drawText(rightText, 0f, baseLineOffset + lineHeight, paint)
        }
    }

    private fun calculateHeight(viewWidth: Int): Int {
        val leftTextWidth = paint.measureText(leftText)
        val rightTextWidth = paint.measureText(rightText)

        shouldMoveRightTextToNewLine = viewWidth - leftTextWidth - 20 < rightTextWidth

        return if (shouldMoveRightTextToNewLine) {
            (paint.textSize * 1.5f * 2).toInt() // Две строки
        } else {
            (paint.textSize * 1.5f).toInt() // Одна строка
        }
    }
}
