package org.redbyte.animatron.life

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import kotlin.properties.Delegates

typealias MatrixWorld = Array<IntArray>

class WorldLifeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private var screenWidth: Int by Delegates.notNull()
    private var screenHeight: Int by Delegates.notNull()

    init {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val screenSize = Point()
        display.getSize(screenSize)
        screenHeight = screenSize.y
        screenWidth = screenSize.x
    }

    private val paint = Paint()
    private val rect = Rect()
    private val liveColor = Color.RED
    private val deadColor = Color.WHITE
    private var cellSize = screenWidth / 32
    var columnSize = screenWidth / cellSize
    var rowSize = screenHeight / cellSize
    var matrix: MatrixWorld = MatrixWorld(columnSize) { IntArray(rowSize) }


    fun setChangedList(matrix: MatrixWorld) {
        this.matrix = matrix
    }

    fun setCellSize(size: Int) {
        cellSize = size
        columnSize = screenWidth / cellSize
        rowSize = screenHeight / cellSize
    }

    fun getCellSize(): Int = cellSize

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCells(canvas)
    }

    private fun drawCells(canvas: Canvas) {
        matrix.let {
            for (i in it.indices) {
                for (j in it[0].indices) {
                    rect.set(
                        i * cellSize, j * cellSize,
                        (i + 1) * cellSize, (j + 1) * cellSize
                    )
                    paint.color = if (it[i][j] == 1) {
                        liveColor
                    } else {
                        deadColor
                    }
                    canvas.drawRect(rect, paint)
                }
            }
        }
    }

    fun generateWorld(initialPopulation: Int) {
        repeat((0..initialPopulation).count()) {
            val x = (0 until columnSize).random()
            val y = (0 until rowSize).random()
            matrix[x][y] = if (matrix[x][y] == 0) 1 else 0
            setLive(x, y)
        }
    }

    private fun setLive(x: Int, y: Int) {
        matrix.let {
            val xInt = (x / cellSize)
            val yInt = (y / cellSize)
            if (xInt >= it.size || yInt >= it[0].size) return
            invalidate()
        }
    }
}