package org.redbyte.animatron.table

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import org.redbyte.animatron.R
import kotlin.math.min

typealias CellClickListener = (Int, Int, String) -> Unit

class RedTableView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var tableData: List<List<String>> = emptyList()
    private var columnHeaders: List<String> = emptyList()
    private var selectColor: Int = Color.BLUE
    private var cellWidth: Float = 0f
    private var cellHeight: Float = 0f
    private var textColor: Int = 0
    private var selectedRow = -1
    private var selectedColumn = -1

    private val paint = Paint()
    private var columnMaxWidths = IntArray(0)

    private var cellClickListener: CellClickListener = { _, _, _ -> }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RedTableView)
        selectColor = typedArray.getColor(
            R.styleable.RedTableView_selectColor, Color.parseColor("#6AD7E5")
        )
        textColor = typedArray.getColor(R.styleable.RedTableView_textColor, Color.BLACK)
        paint.textSize = typedArray.getDimension(
            R.styleable.RedTableView_android_textSize, resources.getDimension(R.dimen.text_size)
        )
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.textAlign = Paint.Align.CENTER
        for (col in columnHeaders.indices) {
            val left = col * cellWidth
            val x = left + cellWidth / 2
            canvas?.drawText(columnHeaders[col], x, 80f, paint)
        }

        for (row in tableData.indices) {
            for (col in 0 until tableData[row].size) {
                val left = col * cellWidth
                val top = (row + 1) * cellHeight
                val right = left + cellWidth
                val bottom = top + cellHeight

                paint.color =
                    if (row == selectedRow && col == selectedColumn) selectColor else Color.WHITE
                canvas?.drawRect(left, top, right, bottom, paint)
                val x = left + cellWidth / 2
                paint.color = textColor
                canvas?.drawText(tableData[row][col], x, top + (cellHeight / 1.5).toInt(), paint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        val numRows = tableData.size
        val numColumns =
            if (columnHeaders.isNotEmpty()) columnHeaders.size else tableData.getOrNull(0)?.size
                ?: 0

        cellWidth = if (numColumns > 0) {
            width.toFloat() / numColumns
        } else {
            width.toFloat()
        }
        val tmpRow =
            if (columnHeaders.isEmpty()) 1 else 0  // +1 для последней строки, когда заголовки отсутствуют
        cellHeight = if (numRows > 0) {
            val headerOffset = if (columnHeaders.isNotEmpty()) 1 else 0
            height.toFloat() / (numRows + headerOffset + tmpRow)
        } else {
            height.toFloat()
        }
        setMeasuredDimension(width, height)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val row = (event.y / cellHeight).toInt() - 1
                val col = min((event.x / cellWidth).toInt(), tableData.first().size - 1)
                if (row >= 0 && row < tableData.size && col >= 0 && col < tableData.first().size) {
                    val value = tableData[row][col]
                    cellClickListener(row, col, value)
                    selectCell(row, col)
                    invalidate()
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun selectCell(row: Int, col: Int) {
        if (selectedRow == row && selectedColumn == col) {
            selectedRow = -1
            selectedColumn = -1
        } else {
            selectedRow = row
            selectedColumn = col
        }
    }

    fun setOnCellClickListener(listener: CellClickListener) {
        cellClickListener = listener
    }

    fun setTableData(data: List<List<String>>) {
        tableData = data
        requestLayout()
        invalidate()
    }

    fun setColumnHeaders(headers: List<String>) {
        columnHeaders = headers
        columnMaxWidths = IntArray(columnHeaders.size)
        requestLayout()
        invalidate()
    }
}
