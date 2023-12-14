package org.redbyte.animatron.newyear

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.redbyte.animatron.R
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class NewYearTreeView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val treeColor = Color.rgb(34, 139, 34)
    private val trunkColor = Color.rgb(139, 69, 19)
    private val starColor = Color.RED

    private val ballColors = intArrayOf(
        Color.YELLOW, Color.BLUE, Color.RED,
        Color.MAGENTA, Color.CYAN, Color.GREEN,
        Color.rgb(255, 165, 0), Color.rgb(255, 192, 203),
        Color.rgb(0, 128, 128)
    )

    private val ballPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val treePaint = Paint()
    private val trunkPaint = Paint()
    private val starPaint = Paint()
    private var ballsCount = 5

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewYearTreeView)
        treePaint.color = treeColor
        treePaint.style = Paint.Style.FILL
        trunkPaint.color = trunkColor
        trunkPaint.style = Paint.Style.FILL
        starPaint.color = starColor
        starPaint.style = Paint.Style.FILL
        ballPaint.style = Paint.Style.FILL
        ballsCount = typedArray.getInt(R.styleable.NewYearTreeView_ballsCount, ballsCount)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val treeWidth = width.toFloat()
        val treeHeight = height.toFloat()

        drawTree(canvas, treeWidth, treeHeight)
        drawTrunk(canvas, treeWidth, treeHeight)
        drawStar(canvas, treeWidth, treeHeight)

    }

    private fun drawBalls(canvas: Canvas, width: Float, height: Float) {
        val ballRadius = width / 40
        val ballCenters = mutableListOf<Pair<Float, Float>>()

        val random = java.util.Random()
        while (ballCenters.size < ballsCount) {
            val cx = width * random.nextFloat()
            val cy = height * 0.1f + (height * 0.7f) * random.nextFloat()

            if (isInsideTree(cx, cy, width, height)) {
                var valid = true
                for (center in ballCenters) {
                    val dx = center.first - cx
                    val dy = center.second - cy
                    val distance = sqrt((dx * dx + dy * dy).toDouble())
                    if (distance < ballRadius * 2) {
                        valid = false
                        break
                    }
                }

                if (valid) {
                    ballCenters.add(Pair(cx, cy))
                }
            }
        }

        for (center in ballCenters) {
            ballPaint.color = ballColors[ballCenters.indexOf(center) % ballColors.size]
            canvas.drawCircle(center.first, center.second, ballRadius, ballPaint)
        }
    }

    private fun isInsideTree(x: Float, y: Float, width: Float, height: Float): Boolean {
        val path = Path()
        path.moveTo(width / 2, height * 0.1f)
        path.lineTo(width * 0.1f, height * 0.8f)
        path.lineTo(width * 0.9f, height * 0.8f)
        path.close()

        val pathTop = Path()
        pathTop.moveTo(width / 2, height * 0.4f)
        pathTop.lineTo(width * 0.3f, height * 0.6f)
        pathTop.lineTo(width * 0.7f, height * 0.6f)
        pathTop.close()

        val region = Region()
        val rectF = RectF()
        path.computeBounds(rectF, true)
        region.setPath(
            path, Region(
                rectF.left.toInt(),
                rectF.top.toInt(),
                rectF.right.toInt(),
                rectF.bottom.toInt()
            )
        )

        val regionTop = Region()
        val rectFTop = RectF()
        pathTop.computeBounds(rectFTop, true)
        regionTop.setPath(
            pathTop, Region(
                rectFTop.left.toInt(),
                rectFTop.top.toInt(),
                rectFTop.right.toInt(),
                rectFTop.bottom.toInt()
            )
        )

        return region.contains(x.toInt(), y.toInt()) || regionTop.contains(x.toInt(), y.toInt())
    }

    private fun drawTree(canvas: Canvas, width: Float, height: Float) {
        val path = Path()
        path.moveTo(width / 2, height * 0.1f)
        path.lineTo(width * 0.1f, height * 0.8f)
        path.lineTo(width * 0.9f, height * 0.8f)
        path.close()

        canvas.drawPath(path, treePaint)
        val pathTop = Path()
        pathTop.moveTo(width / 2, height * 0.4f)
        pathTop.lineTo(width * 0.3f, height * 0.6f)
        pathTop.lineTo(width * 0.7f, height * 0.6f)
        pathTop.close()

        canvas.drawPath(pathTop, treePaint)
        drawBalls(canvas, width, height)
    }

    private fun drawTrunk(canvas: Canvas, width: Float, height: Float) {
        val trunkWidth = width / 5
        val trunkHeight = height / 8

        val left = (width - trunkWidth) / 2
        val top = height * 0.8f

        canvas.drawRect(left, top, left + trunkWidth, top + trunkHeight, trunkPaint)
    }

    private fun drawStar(canvas: Canvas, width: Float, height: Float) {
        val starPath = Path()
        val innerRadius = width / 15
        val outerRadius = width / 10

        val cx = width / 2
        val cy = height * 0.1f
        val rotation = -90f
        canvas.rotate(rotation, cx, cy)

        for (i in 0 until 5) {
            val angle = Math.toRadians((i * 144).toDouble())
            val x = (cx + innerRadius * cos(angle)).toFloat()
            val y = (cy + innerRadius * sin(angle)).toFloat()
            if (i == 0) {
                starPath.moveTo(x, y)
            } else {
                starPath.lineTo(x, y)
            }

            val nextAngle = Math.toRadians(((i * 144) + 72).toDouble())
            val nextX = (cx + outerRadius * cos(nextAngle)).toFloat()
            val nextY = (cy + outerRadius * sin(nextAngle)).toFloat()
            starPath.lineTo(nextX, nextY)
        }

        starPath.close()

        canvas.drawPath(starPath, starPaint)
    }

}