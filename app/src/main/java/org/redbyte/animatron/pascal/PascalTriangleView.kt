package org.redbyte.animatron.pascal

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import org.redbyte.animatron.R

class PascalTriangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private var triangleHeight: Int = 0
    private var itemTextColor: Int = 0
    private var itemColor: Int = 0
    private val triangleValues: MutableList<List<Int>> = mutableListOf()
    private var lastTouchedRow: Int = -1
    private var lastTouchedColumn: Int = -1
    private var rotationAnimator: ObjectAnimator? = null
    private var privateSelectedRotationDegrees: Float = 360f

    var rotationDegrees: Float
        get() = privateSelectedRotationDegrees
        set(value) {
            privateSelectedRotationDegrees = value
            invalidate()
        }

    var itemClick: (Int) -> Unit = {}

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PascalTriangleView)
        triangleHeight = typedArray.getInt(R.styleable.PascalTriangleView_triangleHeight, 5)
        itemTextColor = typedArray.getInt(R.styleable.PascalTriangleView_itemTextColor, Color.WHITE)
        itemColor = typedArray.getInt(R.styleable.PascalTriangleView_itemColor, Color.BLACK)
        typedArray.recycle()

        generatePascalsTriangle()
    }

    private fun generatePascalsTriangle() {
        triangleValues.clear()
        for (i in 0 until triangleHeight) {
            val row = mutableListOf<Int>()
            for (j in 0..i) {
                val value = if (j == 0 || j == i) 1 else {
                    triangleValues[i - 1][j - 1] + triangleValues[i - 1][j]
                }
                row.add(value)
            }
            triangleValues.add(row)
        }
        triangleValues.reverse()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val cellSize = width.toFloat() / (triangleHeight + 1)
                for (i in triangleValues.size - 1 downTo 0) {
                    val y = (triangleHeight - i) * cellSize
                    for (j in 0 until triangleValues[i].size) {
                        val startX = width / cellSize + (cellSize * i * 0.5f)
                        val x = (startX + (j + 0.5f) * cellSize)
                        if (event.x >= x - cellSize / 2 && event.x <= x + cellSize / 2
                            && event.y >= y - cellSize / 2 && event.y <= y + cellSize / 2
                        ) {
                            lastTouchedRow = i
                            lastTouchedColumn = j
                            animateSelectedItem()
                            itemClick(triangleValues[i][j])
                            invalidate()
                            return true
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawPascalTriangle(this)
            drawSelectedItemWithRotation(this)
        }
    }

    private fun animateSelectedItem() {
        rotationAnimator?.cancel()
        rotationAnimator = ObjectAnimator.ofFloat(
            this,
            "rotationDegrees",
            0f,
            rotationDegrees
        )
        rotationAnimator?.duration = 777
        rotationAnimator?.interpolator = DecelerateInterpolator()
        rotationAnimator?.start()
    }

    private fun drawSelectedItemWithRotation(canvas: Canvas) {
        if (lastTouchedRow >= 0 && lastTouchedColumn >= 0) {
            val cellSize = width.toFloat() / (triangleHeight + 1)
            val y = (triangleHeight - lastTouchedRow) * cellSize
            val startX = width / cellSize + (cellSize * lastTouchedRow * 0.5f)
            val x = (startX + (lastTouchedColumn + 0.5f) * cellSize)

            canvas.save()
            canvas.rotate(rotationDegrees, x, y)
            drawCircleAndText(
                canvas,
                x,
                y,
                cellSize / 2,
                triangleValues[lastTouchedRow][lastTouchedColumn].toString()
            )
            canvas.restore()
        }
    }

    private fun drawPascalTriangle(canvas: Canvas) {
        val cellSize = width.toFloat() / (triangleHeight + 1)
        for (i in triangleValues.size - 1 downTo 0) {
            val y = (triangleHeight - i) * cellSize
            for (j in 0 until triangleValues[i].size) {
                val startX = width / cellSize + (cellSize * i * 0.5f)
                val x = (startX + (j + 0.5f) * cellSize)
                val text = triangleValues[i][j].toString()
                drawCircleAndText(canvas, x, y, cellSize / 2, text)
            }
        }
    }

    private fun drawCircleAndText(
        canvas: Canvas,
        x: Float,
        y: Float,
        radius: Float,
        text: String
    ) {
        paint.color = itemColor
        canvas.drawCircle(x, y, radius, paint)
        paint.color = itemTextColor
        paint.textSize = radius
        paint.textAlign = Paint.Align.CENTER
        val yPos = y + (paint.textSize) / 2
        canvas.drawText(text, x, yPos, paint)
    }
}
